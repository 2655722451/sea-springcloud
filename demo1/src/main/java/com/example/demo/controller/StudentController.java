package com.example.demo.controller;

import com.example.demo.entity.TblDemo;
import com.example.demo.interfaces.AddLogs;
import com.example.demo.repository.TblDemoRepository;
import com.example.demo.util.ResultMap;
import com.example.demo.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

//------------------------------------------接口 测试---------------------------------------------------

    @Autowired
    private TblDemoRepository tblDemoRepository;

    /**
     * 实体拦截方法
     * @param studentVo 实体
     * @return
     */
    @GetMapping("/studentdemo")
    @ResponseBody
    public ResultMap studentdemo(StudentVo studentVo) {
        String str = studentVo.toString();

        return new ResultMap(200, "", str);
    }

    /**
     * 集合拦截方法 （未特殊处理过，所以不好使）
     * @param studentVo 集合
     * @return
     */
    @PostMapping("/studentdemo2")
    @ResponseBody
    public ResultMap studentdemo2(@RequestBody List<StudentVo> studentVo) {
        String str = studentVo.toString();

        return new ResultMap(200, "", str);
    }

    /**
     * 异常拦截测试类
     * @return
     */
    @GetMapping("/errorException")
    @ResponseBody
    public ResultMap errorException() {
        String str = "赵小帅";
        Integer integer = Integer.valueOf(str);

        return new ResultMap(200, "", integer + "");
    }

    /**
     * 单纯测试，啥也不是
     * @param lala
     * @return
     */
    @GetMapping("/demo1")
    @ResponseBody
    public ResultMap demo1(String lala) {
        return new ResultMap(200, "", lala);
    }

//////////////////////////////////////////测试日志////////////////////////////////////////////////////////////////

    /**
     * 添加 数据
     * @param tblDemo
     * @return
     */
    @GetMapping("/demo/add")
    @ResponseBody
    public ResultMap addDemo(@AddLogs(statelog = 1) TblDemo tblDemo){
        TblDemo tblDemo1 = tblDemoRepository.save(tblDemo);
        return new ResultMap(200, "", tblDemo1.getId());
    }

    /**
     * 修改
     * @param tblDemo
     * @return
     */
    @GetMapping("/demo/update")
    @ResponseBody
    public ResultMap updateDemo(@AddLogs(statelog = 2) TblDemo tblDemo){
        TblDemo tblDemo1 = tblDemoRepository.save(tblDemo);
        return new ResultMap(200, "", tblDemo1.getId());
    }

    /**
     * 删除
     * @param tblDemo
     * @return
     */
    @GetMapping("/demo/remove")
    @ResponseBody
    public ResultMap removeDemo(@AddLogs(statelog = 3) TblDemo tblDemo){
        tblDemoRepository.deleteById(tblDemo.getId());
        return new ResultMap(200, "", "");
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/demo/query")
    @ResponseBody
    public ResultMap removeDemo(){
        List<TblDemo> list = tblDemoRepository.findAll();
        return new ResultMap(200, "", list);
    }


}
