package net.zaolunzi.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 判断当前用户是否未登录（包括：未认证 或 未记住，即“访客”身份）
 *
 * @Author: SelectBook
 * @Date: 2022/5/22 17:48
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Guest {
}
