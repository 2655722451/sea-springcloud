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
    private Object[] objects;//切面前查询

    public LogAspectVo(int stateLog, Field[] fields, Object paramObject, String tablename){
        this.stateLog = stateLog;
        this.fields = fields;
        this.paramObject = paramObject;
        this.tablename = tablename;
    }

    public String getTablename() {
        return tablename;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
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

    public String getStateLogName(int stateLog){
        if(1 == stateLog){
            return "新增";
        }else if(2 == stateLog){
            return "修改";
        }else if(3 == stateLog){
            return "删除";
        }
        return "";
    }

}
