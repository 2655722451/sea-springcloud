package com.example.demo.controller;

import com.example.demo.vo.StudentVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Classname StudentController
 * @Description TODO
 * @Date 2021/2/6 11:09
 * @Created by Administrator
 */
@Controller
public class StudentController {

    private String test = "qwer";

    public void lala(){
        System.out.println(test);
    }

    @GetMapping("/studentdemo")
    @ResponseBody
    public String studentdemo(StudentVo studentVo) {
        String str = studentVo.toString();

        return str;
    }

    @PostMapping("/studentdemo2")
    @ResponseBody
    public String studentdemo2(@RequestBody List<StudentVo> studentVo) {
        String str = studentVo.toString();

        return str;
    }
}
