package com.example.demo.entity;

import javax.persistence.*;

/**
 * @Classname Tbl_Demo
 * @Description TODO
 * @Date 2021/2/19 11:05
 * @Created by Administrator
 */
@Entity
@Table(name="tbl_demo")
public class TblDemo {

    private int id;
    private String username;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
