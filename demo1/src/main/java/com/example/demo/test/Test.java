package com.example.demo.test;

import com.example.demo.controller.StudentController;
import com.example.demo.interfaces.CheckIsNull;
import com.example.demo.vo.StudentVo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

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
        new Test().catalog();


    }

    /**
     * 抓目录
     * @throws IOException
     */
    public void catalog() throws IOException {
        String url = "https://www.luoqiuxzw.com/book/1049/";
        Connection connect = Jsoup.connect(url);

        Document document = connect.get();
        Element listElement = document.getElementById("list");
        Elements aElements = listElement.getElementsByTag("a");

        String str = "";
        System.err.println("目录：");
        for(int i = 0; i < aElements.size(); i++){
            System.err.println(i + "  章节:" + aElements.get(i).html());
        }
        System.out.println("输入数字：");
        Scanner input = new Scanner(System.in);
        str = input.next();

        int whileI = Integer.valueOf(str);
        this.readerText(aElements.get(whileI).attr("href"));
    }

    public void readerText(String url) throws IOException {
        Test test = new Test();
        while (true){
            Elements elements = test.jsoup(url, true);

            if(elements.size() > 0){
                Elements aelements = elements.get(0).select("a");
                String str = "";
                for(int i = 0; i < aelements.size(); i++){
                    System.out.println(i + "、" + aelements.get(i).html());
                }
                System.out.println();
                System.out.println();
                System.err.println("先右键手动清空控制台（Clear All），再输入数字");
                System.out.println("输入数字：");
                Scanner input = new Scanner(System.in);
                str = input.next();
                url = aelements.get(Integer.valueOf(str)).attr("href");

            }else{
                System.out.println("没了！！！或页面异常 url：" + url);
            }
        }
    }

    public Elements jsoup(String url, boolean bool) throws IOException {
        //代理
        Random r = new Random();
        int n = r.nextInt(255);
        String ipStr = "10.128.110." + n;
        System.err.println("代理ip：" + ipStr);
        System.setProperty("http.maxRedirects", "1");
        System.getProperties().setProperty("proxySet", "true");
        System.getProperties().put("https.proxyHost", ipStr);//代理ip
        System.getProperties().put("https.proxyPort", "3182");//代理端口

        //页面header 中 User-Agent
        String agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.3";

        String urlPath = "https://www.luoqiuxzw.com";
        List<Map<String, String>> list = new ArrayList<>();
        Connection connect = Jsoup.connect(urlPath + url);

        Document document = connect
                .userAgent(agent)
                .ignoreHttpErrors(true)//这个很重要 否则会报HTTP error fetching URL. Status=404
                .get();
        Element contentElement = document.getElementById("content");

        System.err.println("html地址：" + urlPath + url);
        System.err.println("章节名称：" + this.getTitleName(document));
        System.out.println();
        System.out.println();
        System.out.println("小说内容：");
        Elements pElement = contentElement.getElementsByClass("content_detail");
        if(bool){
            pElement.forEach(s -> System.out.println(s.html()));
        }

        Elements bottem2Elements = document.getElementsByClass("bottem2");
        return bottem2Elements;
    }

    public String getTitleName(Document document){
        Element mainElement = document.getElementById("main");
        Elements h1Elements = mainElement.getElementsByTag("h1");
        if(h1Elements.size() > 0){
            return h1Elements.get(0).html();
        }
        return "";
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
