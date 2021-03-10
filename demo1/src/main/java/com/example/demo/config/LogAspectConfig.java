package com.example.demo.config;

import com.example.demo.entity.TblLog;
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
import javax.persistence.Query;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
            this.after(logAspectVo);//方法结束，对比数据添加日志
        }

        return null;
    }

    /**
     * 方法之前
     * @param joinPoint
     * @return
     * @throws IllegalAccessException
     */
    public LogAspectVo before(JoinPoint joinPoint) throws IllegalAccessException {
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

                    Field[] fields = params[i].getClass().getDeclaredFields();
                    for (int jk = 0; jk < fields.length; jk++){
                        try {
                            fields[jk].setAccessible(true);
                            System.out.println(fields[jk].get(params[i]));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }

            Integer id = null;
            Field[] paramFields = param.getClass().getDeclaredFields();
            for(int k = 0; k < paramFields.length; k++){
                paramFields[k].setAccessible(true);
                if(paramFields[k].getName().equals("id")){
                    id = Integer.valueOf(paramFields[k].get(param) + "");
                }
            }
            if(id != null){
                String sqlStr = "select * from " + logAspectVo.getTablename() + " where id=" + id;

                Query query = entityManager.createNativeQuery(sqlStr);
                List list = query.getResultList();
                Object[] cells = (Object[]) list.get(0);
                logAspectVo.setObjects(cells);
            }
        }

        return logAspectVo;
    }

    public void after(LogAspectVo logAspectVo) throws IllegalAccessException {
        Field[] fields = logAspectVo.getFields();
        String id = null;

        Optional<Field> optionalField = Arrays.stream(fields).filter(s -> "id".equals(s.getName())).findFirst();
        if(optionalField.isPresent() && logAspectVo.getObjects() != null){
            Field field = optionalField.get();
            field.setAccessible(true);//可以访问私有属性
            String sqlStr = "select * from " + logAspectVo.getTablename() + " where id=" + field.get(logAspectVo.getParamObject());

            Query query = entityManager.createNativeQuery(sqlStr);
            List list = query.getResultList();
            Object[] cells = (Object[]) list.get(0);
            for(int i = 0; i < logAspectVo.getObjects().length; i++){
                fields[i].setAccessible(true);

                if(!(cells[i] + "").equals(logAspectVo.getObjects()[i] + "")){

                    TblLog tblLog = new TblLog();
                    tblLog.setFlogdatetime(df.format(new Date()));
                    tblLog.setFloguserid("");
                    tblLog.setFlogusername("");
                    tblLog.setFlogtypeid(logAspectVo.getStateLog());
                    tblLog.setFlogtypename(logAspectVo.getStateLogName(tblLog.getFlogtypeid()));
                    tblLog.setFtable(logAspectVo.getTablename());
                    tblLog.setFrecordid(field.get(logAspectVo.getParamObject()) + "");
                    tblLog.setFstate("1");
                    if(tblLog.getFlogtypeid() == 1){
                        tblLog.setFlogcontent("新增数据 id = " + field.get(logAspectVo.getParamObject()));
                    }else if(tblLog.getFlogtypeid() == 2){
                        tblLog.setFlogcontent(fields[i].getName() + "字段，由" + logAspectVo.getObjects()[i] +
                                "改成" + cells[i]);
                    }

                    tblLogRepository.save(tblLog);
                }
            }

        }
    }

}
