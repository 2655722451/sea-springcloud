package com.example.demo.test;

import com.example.demo.vo.Java8StreamStudentVo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname Java8StreamTest
 * @Description TODO
 * @Date 2021/3/1 9:59
 * @Created by Administrator
 */
public class Java8StreamTest {
    /**
     * 1 输出每个人的名字及其平均成绩
     * 2 以平均成绩的逆序排序
     * 3 要有良好的代码注释
     * 4 体现出Java面向对象的特性和代码复用性
     *
     * 输出结果（记得原题好像都是整数计算结果也是整数，因为要求平均成绩，根据实用性定义了double类型）
     * 李四 95.5
     * 王五 88.0
     * 张三 86.0
     */
    List<String> strList = Arrays.asList("1,张三,85","2,李四,91","3,王五,88","4,张三,86","5,李四,100","6,张三,87");

    public static void main(String[] args) {
        new Java8StreamTest().lala();
    }

    public void lala(){
        List<Java8StreamStudentVo> list = new ArrayList<>();
        strList
            .stream()
            .map(s -> {
                return this.stringToStudentVo(s);
            })
            //.collect(Collectors.groupingBy(Java8StreamStudentVo::getName)) 重点下午看看
            .collect(Collectors.groupingBy(s -> s.getName()))
            .forEach((k, v) -> {
                list.add(this.scoreAverage(v));
            });

        list.stream()
                .sorted(Comparator.comparing(Java8StreamStudentVo::getScore).reversed())
                .collect(Collectors.toList())
                .forEach(s -> {
                    System.out.println(s.toString());
                });

    }

    public Java8StreamStudentVo stringToStudentVo(String str){
        String[] strs = str.split(",");
        Java8StreamStudentVo java8StreamStudentVo = new Java8StreamStudentVo();
        java8StreamStudentVo.setId(Integer.valueOf(strs[0]));
        java8StreamStudentVo.setName(strs[1]);
        java8StreamStudentVo.setScore(Double.valueOf(strs[2]));

        return java8StreamStudentVo;
    }

    public Java8StreamStudentVo scoreAverage(List<Java8StreamStudentVo> list){
        Java8StreamStudentVo java8StreamStudentVo = new Java8StreamStudentVo();
        java8StreamStudentVo.setId(list.get(0).getId());
        java8StreamStudentVo.setName(list.get(0).getName());

        OptionalDouble s = list.stream().mapToDouble(Java8StreamStudentVo::getScore).average();
        if(s.isPresent()){
            java8StreamStudentVo.setScore(s.getAsDouble());
        }else{
            java8StreamStudentVo.setScore(0.0);
        }
        return java8StreamStudentVo;
    }
}
