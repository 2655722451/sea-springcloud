package com.example.demo.test;

import com.example.demo.controller.StudentController;
import com.example.demo.interfaces.CheckIsNull;
import com.example.demo.vo.StudentVo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/2/7 10:03
 * @Created by Administrator
 */
public class Test {

    public static void main(String[] args) throws Exception {
        //new Test().ziduan();
        //new Test().fangfa();
        //new Test().zhujie();
    }

    //注解
    public void zhujie(){
        StudentVo studentVo = new StudentVo();
        Class clazz = studentVo.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            CheckIsNull checkIsNulls = fields[i].getAnnotation(CheckIsNull.class);//获取字段上注解
            if(checkIsNulls != null){
                System.out.println("isnull:" + checkIsNulls.isnull());
                System.out.println("message:" + checkIsNulls.message());
            }
        }
    }

    //方法
    public void fangfa() throws Exception {
        StudentVo studentVo = new StudentVo();
        Class clazz = studentVo.getClass();
        Method[] methods = clazz.getDeclaredMethods();//获取方法集合
        for(int i = 0; i < methods.length; i++){
            System.out.println("获取方法名称:" + methods[i].getName());//获取方法名称
            System.out.println("获取修饰符:" + Modifier.toString(methods[i].getModifiers()));//获取修饰符
            System.out.println("获取返回值:" + methods[i].getReturnType().getSimpleName());//获取返回值类型

            Class[] types = methods[i].getParameterTypes();//获取参数类型
            Arrays.stream(types).forEach(s -> System.out.println(s.getName()));

            if("setName".equals(methods[i].getName())){
                System.out.println("--------------------------------------------------------");

                System.out.println("name:" + studentVo.getName());
                Method method = clazz.getMethod(methods[i].getName(), String.class);//创建方法对象 (参数：方法名称，参数类型...)
                Object obj = method.invoke(studentVo, "fsd");//调用方法(参数：实例化对象，参数...)
                System.out.println("obj:" + obj);

                Method method2 = clazz.getMethod("getName");//创建方法对象 (参数：方法名称，参数类型...)
                Object obj2 = method2.invoke(studentVo);//调用方法(参数：实例化对象，参数...)
                System.out.println("obj2:" + obj2);

                System.out.println("name:" + studentVo.getName());

                System.out.println("--------------------------------------------------------");
            }

        }
    }

    //字段
    public void ziduan() throws IllegalAccessException {
        StudentController studentController = new StudentController();
        Class clazz = studentController.getClass();
        Field[] fields = clazz.getDeclaredFields();//获取类的所有声明字段
        for(int i = 0; i < fields.length; i++){
            fields[i].setAccessible(true);//私有方法允许访问
            Object obj = fields[i].get(studentController);//取值（注参数类型：取值对象，不是class）
            System.out.println(fields[i].getName() + ":" + obj);

            studentController.lala();
            fields[i].set(studentController, "444");//复制 (注参数类型：对象，要改变的值)
            studentController.lala();
        }
    }

}
