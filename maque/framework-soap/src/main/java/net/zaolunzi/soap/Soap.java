package net.zaolunzi.soap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SOAP 服务注解
 *
 * @Author: SelectBook
 * @Date: 2022/5/22 16:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Soap {

    /**
     * 服务名
     */
    String value() default "";
}
