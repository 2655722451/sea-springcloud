package com.example.demo.config;

import com.example.demo.interfaces.AddLogs;
import com.example.demo.repository.TblLogRepository;
import com.example.demo.vo.LogAspectVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * @Classname LogAspectConfig
 * @Description TODO
 * @Date 2021/2/23 13:57
 * @Created by Administrator
 */
@Aspect
@Component
public class LogAspectConfig {

    @Autowired
    private TblLogRepository tblLogRepository;
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Pointcut 切入点
     * 匹配cn.controller包下面的 参数中带有 @AddLogs 注解的方法
     */
    @Pointcut("execution(public * com.example.demo.controller.*.*(@com.example.demo.interfaces.AddLogs (*), ..))")
    public void addLogs(){}

    /**
     * 环绕通知
     * 添加 实体增删改日志
     */
    @Around(value = "addLogs()")
    public Object addLogsArround(ProceedingJoinPoint pjp) throws Throwable {
        //方法之前，获取 数据
        LogAspectVo logAspectVo = before(pjp);
        if(logAspectVo != null){
            pjp.proceed();//执行方法
            //方法结束，对比数据添加日志
        }

        return null;
    }

    /**
     * 方法之前
     * @param joinPoint
     * @return
     * @throws IllegalAccessException
     */
    public LogAspectVo before(JoinPoint joinPoint) {
        LogAspectVo logAspectVo = null;

        Object[] params = joinPoint.getArgs();
        if(params.length == 0){
            return null;
        }

        //获取方法，此处可将 signature 强转为 MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //参数注解，1维是参数，2维是注解
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Object param = params[i];
            Annotation[] paramAnn = annotations[i];
            //参数为空，直接下一个参数
            if(param == null || paramAnn.length == 0){
                continue;
            }
            for (Annotation annotation : paramAnn) {
                //这里判断当前注解是否为Test.class
                if(annotation.annotationType().equals(AddLogs.class)){
                    //校验该参数，验证一次退出该注解
                    //TODO 校验参数
                    Table table = params[i].getClass().getAnnotation(Table.class);
                    logAspectVo = new LogAspectVo(((AddLogs) annotation).statelog(),
                            params[i].getClass().getDeclaredFields(),
                            params[i], table.name());

                    break;
                }
            }
        }

        return logAspectVo;
    }

    public void after(LogAspectVo logAspectVo) throws IllegalAccessException {
        Field[] fields = logAspectVo.getFields();
        String id = null;

        Optional<Field> optionalField = Arrays.stream(fields).filter(s -> "id".equals(s.getName())).findFirst();
        if(optionalField.isPresent()){
            Field field = optionalField.get();

        }
    }

}
