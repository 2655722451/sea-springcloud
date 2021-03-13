package com.example.demo.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname QuartzController
 * @Description TODO
 * @Date 2021/3/13 14:44
 * @Created by Administrator
 */
@RestController
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    /**
     * 新增
     * @param job
     * @param name
     * @param cron
     * @return
     * @throws SchedulerException
     */
    @GetMapping("/add")
    public String addTimeJob(String job, String name, String cron) throws SchedulerException {
        Map<String, Object> param = new HashMap<>(2);
        param.put("name", name);
        QuartzUtils.createJob(scheduler, TimeJob.class, job, "def", cron, param);
        return "OK";
    }

    /**
     * 暂停
     * @param job
     * @return
     * @throws SchedulerException
     */
    @GetMapping("/pause")
    public String pauseJob(String job) throws SchedulerException {
        QuartzUtils.pauseJob(scheduler, job, "def");
        return "OK";
    }

    /**
     * 恢复
     * @param job
     * @return
     * @throws SchedulerException
     */
    @GetMapping("/resume")
    public String resumeJob(String job) throws SchedulerException {
        QuartzUtils.resumeJob(scheduler, job, "def");
        return "OK";
    }

    /**
     * 修改
     * @param job
     * @param cron
     * @return
     * @throws SchedulerException
     */
    @GetMapping("/update")
    public String updateJob(String job, String cron) throws SchedulerException {
        QuartzUtils.refreshJob(scheduler, job, "def", cron);
        return "OK";
    }

    /**
     * 删除
     * @param job
     * @return
     * @throws SchedulerException
     */
    @GetMapping("/delete")
    public String deleteJob(String job) throws SchedulerException {
        QuartzUtils.deleteJob(scheduler, job, "def");
        return "OK";
    }

}
