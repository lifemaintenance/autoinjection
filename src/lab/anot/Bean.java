package lab.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * аннотация, обозначающая классы,
 * которые будут использованы
 * в качестве дальнейших конструкторов
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
	/**
	 * чем выше уровень, тем ниже в иерархии на запись находится би
	 * в случае если найдутся 2 бина с одинакоым уровнем, наследующие один интерфейс,
	 * то будет использован случайный бин
	 * @return уровень в иерархии бина
	 */
	int level() default 0;
}
