package com.example.demo.vo;

import java.lang.reflect.Field;

/**
 * @Classname LogAspectVo
 * @Description TODO
 * @Date 2021/2/23 14:25
 * @Created by Administrator
 */
public class LogAspectVo {
    private int stateLog;//日志状态
    private Field[] fields;//参数对象字段数组
    private Object paramObject;//参数对象
    private String tablename;//表名

    public LogAspectVo(int stateLog, Field[] fields, Object paramObject, String tablename){
        this.stateLog = stateLog;
        this.fields = fields;
        this.paramObject = paramObject;
        this.tablename = tablename;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Object getParamObject() {
        return paramObject;
    }

    public void setParamObject(Object paramObject) {
        this.paramObject = paramObject;
    }

    public int getStateLog() {
        return stateLog;
    }

    public void setStateLog(int stateLog) {
        this.stateLog = stateLog;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

}
