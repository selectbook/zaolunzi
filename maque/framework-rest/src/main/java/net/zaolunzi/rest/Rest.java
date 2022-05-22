package net.zaolunzi.rest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: SelectBook
 * @Date: 2022/5/22 16:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rest {
    /**
     * 服务名
     */
    String value() default "";
}
