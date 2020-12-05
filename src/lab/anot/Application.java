package lab.anot;

import lab.Main;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * авто инжект
 * определяем поля которые надо заинжектить
 * ищем все классы в проекте
 * парсим классы по уровням
 * инициализируем поля через сеттеры
 */
public class Application {
	public static Object inject(Object obj) {
		try {
			List<Method> toInject = new ArrayList<>();
			Map<Integer, List<Class<?>>> levels = new HashMap<>();
			int maxLevel = 0;
			Arrays.asList(obj.getClass().getMethods()).forEach(method -> {
				if(method.isAnnotationPresent(AutoInjectable.class)) {
					toInject.add(method);
				}
			});
			Class<?> mainClass = Main.class;
			String packageName = mainClass.getPackage().getName();
			String path = packageName.replace(".", "/");

			Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
			Iterable<URL> urls = resources::asIterator;
			List<File> dirs = new ArrayList<>();

			for(URL url : urls) {
				dirs.add(new File(url.toURI().getPath()));
			}


			List<Class<?>> classes = dirs.stream().flatMap((File d) -> findClasses(d, packageName).stream()).collect(Collectors.toList());
			for(Class<?> c : classes) {
				if(c.isAnnotationPresent(Bean.class)) {
					int l = c.getAnnotation(Bean.class).level();
					l = Math.max(l, 0);
					List<Class<?>> classLevel = levels.get(l);

					if(classLevel == null) {
						classLevel = new ArrayList<>();
						classLevel.add(c);
						levels.put(l, classLevel);
					}
					else {
						classLevel.add(c);
					}
					maxLevel = Math.max(maxLevel, l);
				}
			}

			for(Method m : toInject) {
				boolean flag = true;
				for(int i = 0; i <= maxLevel && flag; ++i) {
					List<Class<?>> c = levels.get(i);
					if(c != null && !c.isEmpty()){
						for(Class<?> clazz : c) {
							if(m.getParameterCount() == 1 && Arrays.asList(clazz.getInterfaces()).contains(m.getParameterTypes()[0]) || m.getParameterTypes()[0].equals(clazz)){
								m.invoke(obj, clazz.getConstructor().newInstance());
								flag = false;
								break;
							}
						}
					}
				}
			}
			return obj;
		}
		catch(Exception ignored) {
			return null;
		}
	}

	private static List<Class<?>> findClasses(File directory, String packageName) {
		if (!directory.exists()) {
			return Collections.emptyList();
		}

		List<Class<?>> classes = new ArrayList<>();
		File[] files = directory.listFiles();
		if (files == null) {
			return Collections.emptyList();
		}

		for (File file : files) {
			if (file.isDirectory()) {
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			}
			else if (file.getName().endsWith(".class")) {
				try {
					classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
				}
				catch (ClassNotFoundException ignored) {}
			}
		}
		return classes;
	}
}
