package com.example.demo.test;

import java.util.Optional;

/**
 * @Classname OptionalTest
 * @Description TODO Optional
 * @Date 2021/3/12 10:37
 * @Created by Administrator
 */
public class OptionalTest {
    public static void main(String[] args) {
        String str = null;
        Optional.ofNullable(str).ifPresentOrElse(s -> {
            System.out.println(s);
        }, () -> {
            System.out.println("no");
        });
    }
}
