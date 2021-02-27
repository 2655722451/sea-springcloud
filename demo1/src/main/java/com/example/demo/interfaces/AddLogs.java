package com.example.demo.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname AddLogs
 * @Description TODO
 * @Date 2021/2/23 8:32
 * @Created by Administrator
 */
//定义注解生效范围
@Target({ElementType.PARAMETER})
//表示注解在什么地方生效
//runtime>class>sources
@Retention(RetentionPolicy.RUNTIME)
public @interface AddLogs {
    public int statelog(); // 状态 1、添加 2、修改 3、删除
    public String message() default "";
}
