package com.example.demo.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname CheckIsNull
 * @Description TODO
 * @Date 2021/2/6 11:23
 * @Created by Administrator
 */
//定义注解生效范围
@Target({ElementType.FIELD})
//表示注解在什么地方生效
//runtime>class>sources
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIsNull {
    public boolean isnull() default true;
    public String message() default "";
}
