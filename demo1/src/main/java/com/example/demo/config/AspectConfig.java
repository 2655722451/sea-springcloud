package com.example.demo.config;

import com.example.demo.interfaces.CheckIsNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @Classname Aspect
 * @Description TODO 切面类
 * @Date 2021/2/6 14:52
 * @Created by Administrator
 */
@Aspect
@Component
public class AspectConfig {

    /**
     * Pointcut 切入点
     * 匹配cn.controller包下面的所有方法
     */
    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void checkIsNull(){}

    /**
     * 环绕通知
     */
    @Around(value = "checkIsNull()")
    public Object arround(ProceedingJoinPoint pjp){
        //方法环绕开始.....
        try {
            String message = before(pjp);
            if("".equals(message)){
                Object o =  pjp.proceed();
                //方法环绕结束，结果是 + o
                return o;
            }else{
                return message;
            }
        } catch (Throwable e) {
            System.out.println(pjp.getSignature() + " 出现异常： " + e.getMessage());
            return "系统异常：" + e.getMessage();
        }
    }

    /**
     * 方法执行前
     */
    //@Before(value = "checkIsNull()")
    public String before(JoinPoint joinPoint) throws IllegalAccessException {
        //Before：方法执行开始...
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        //注解设置的错误消息
        String message = "";

        Object[] orgs = joinPoint.getArgs();
        //参数集合
        for(int i = 0; i < orgs.length; i++){
            Class clazz = orgs[i].getClass();
            Field[] fields = clazz.getDeclaredFields();
            //遍历所有属性
            for(int j = 0; j < fields.length; j++){
                fields[j].setAccessible(true);//可以访问私有属性
                Object obj = fields[j].get(orgs[i]);//获取属性的值
                CheckIsNull checkIsNull = fields[j].getAnnotation(CheckIsNull.class);
                if(checkIsNull != null){
                    //注解配置，是否可以为空
                    if(checkIsNull.isnull() == false){
                        //效验参数实体类，是否为空  不可等于 null 并且不可 等于 0
                        if(obj == null || "0".equals(obj + "")){
                            message = checkIsNull.message();
                        }
                    }
                }
            }
        }

        return message;
        // 记录下请求内容
        /*System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));*/
    }

    /**
     * 方法执行结束，不管是抛出异常或者正常退出都会执行
     */
    //@After(value = "checkIsNull()")
    public void after(JoinPoint joinPoint){
        System.out.println("4、After：方法最后执行.....");
    }

    /**
     * 方法执行结束，增强处理
     */
    /*@AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        logger.info("5、AfterReturning：方法的返回值 : " + ret);
    }*/

    /**
     * 后置异常通知
     */
    /*@AfterThrowing(value = "checkIsNull()")
    public void throwss(JoinPoint joinPoint){
        logger.error("AfterThrowing：方法异常时执行.....");
    }*/

    public static void main(String[] args) {
        Object obj = null;

        if(obj == null || "0".equals(obj + "")){

        }
    }
}
