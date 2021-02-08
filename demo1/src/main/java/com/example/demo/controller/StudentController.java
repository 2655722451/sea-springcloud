package com.example.demo.controller;

import com.example.demo.config.MyException;
import com.example.demo.vo.StudentVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname StudentController
 * @Description TODO aop + java反射 + 自定义注解
 *      所有 controller 的实体参数都会被 aop 拦截住，根据实体类中自定义的注解配置，自动拦截
 *      实体类中设置不可为空的属性，返回提示给前台 （集合内的实体没有做过处理，详情见 config.AspectConfig 文件）
 * @Date 2021/2/6 11:09
 * @Created by Administrator
 */
@Controller
public class StudentController {

    //test 测试属性、方法
    private String test = "qwer";
    public void lala(){
        System.out.println(test);
    }

    /**
     * 实体拦截方法
     * @param studentVo 实体
     * @return
     */
    @GetMapping("/studentdemo")
    @ResponseBody
    public String studentdemo(StudentVo studentVo) {
        String str = studentVo.toString();

        return str;
    }

    /**
     * 集合拦截方法 （未特殊处理过，所以不好使）
     * @param studentVo 集合
     * @return
     */
    @PostMapping("/studentdemo2")
    @ResponseBody
    public String studentdemo2(@RequestBody List<StudentVo> studentVo) {
        String str = studentVo.toString();

        return str;
    }

    /**
     * 异常拦截测试类
     * @return
     */
    @GetMapping("/error")
    @ResponseBody
    public String error() throws Exception{
        String str = "赵小帅";
        throw new MyException("101", "Sam 错误");

        //return "xxxx";
    }

    @RequestMapping("/home")
    public String home() throws Exception {

//        throw new Exception("Sam 错误");
        throw new MyException("101", "Sam 错误");

    }
}
