package indi.jackie.ik.annotation;

import java.lang.annotation.*;

/**
 * @author jackie chen
 * @create 2017/01/19
 * @description 指定data source group
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Dao {
    String value() default "";
}