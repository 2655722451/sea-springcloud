package com.example.demo.config;

import com.example.demo.util.ResultMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname MyControllerAdvice
 * @Description TODO controller 异常拦截类
 * @Date 2021/2/8 8:43
 * @Created by Administrator
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMap errorHandler(Exception ex) {
        System.out.println("全局异常捕捉 Exception" + ex.getMessage());

        return new ResultMap(500, ex.getMessage(), null);
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResultMap myErrorHandler(MyException ex) {
        System.out.println("全局异常捕捉 MyException" + ex.getMessage());

        return new ResultMap(500, ex.getMessage(), null);
    }

}
