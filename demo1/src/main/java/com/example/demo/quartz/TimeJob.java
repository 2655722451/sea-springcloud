package com.example.demo.quartz;

import lombok.Data;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Classname TimeJob
 * @Description TODO
 * @Date 2021/3/13 14:30
 * @Created by Administrator
 */
@Data
public class TimeJob extends QuartzJobBean {

    private String name;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("execute timeJob at " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")) +
                ": hello " + this.name);
    }

}
