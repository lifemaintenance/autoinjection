package lab.anot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * аннотация, указывающая на автозаполненное поле
 * устанавливается на сеттер, в сеттере должен быть 1 параметр
 * иначе поле не проинициализируется
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {

}
