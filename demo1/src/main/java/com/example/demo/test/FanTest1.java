package com.example.demo.test;

/**
 * @Classname FanTest1
 * @Description TODO 泛型，类必须声明<T>
 * @Date 2021/3/12 16:42
 * @Created by Administrator
 */
public class FanTest1<T> {
    public T fanxing;

    public T lala1(T canshu1, String canshu2){
        System.out.println(canshu1 + canshu2);
        return canshu1;
    }

}
