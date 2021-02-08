package com.example.demo.config;

/**
 * @Classname MyException
 * @Description TODO 自定义异常类
 * @Date 2021/2/8 8:51
 * @Created by Administrator
 */
public class MyException extends RuntimeException {

    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
