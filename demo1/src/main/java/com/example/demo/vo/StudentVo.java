package com.example.demo.vo;

import com.example.demo.interfaces.CheckIsNull;

/**
 * @Classname StudentVo
 * @Description TODO
 * @Date 2021/2/6 11:08
 * @Created by Administrator
 */
public class StudentVo {
    @CheckIsNull(isnull = false, message = "id不可为空")
    private int id;
    @CheckIsNull(isnull = false, message = "name不可为空")
    private String name;
    @CheckIsNull(isnull = false, message = "age不可为空")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
