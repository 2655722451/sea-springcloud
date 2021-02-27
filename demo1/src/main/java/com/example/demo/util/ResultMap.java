package com.example.demo.util;

/**
 * @Classname ResultMap
 * @Description TODO
 * @Date 2021/2/18 15:05
 * @Created by Administrator
 */
public class ResultMap {
    private int code;
    private String message;
    private Object data;

    public ResultMap(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMap{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
