package net.zaolunzi.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 判断当前用户是否拥有某种权限
 *
 * @Author: SelectBook
 * @Date: 2022/5/22 17:48
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPermissions {

    /**
     * 权限字符串
     */
    String value();
}
