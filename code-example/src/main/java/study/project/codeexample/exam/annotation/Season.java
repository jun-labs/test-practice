package study.project.codeexample.exam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Environment
public @interface Season {

    Weather weather() default Weather.SPRING;

    enum Weather {
        SPRING,
        SUMMER,
        FALL,
        WINTER
    }
}
