package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//让 springboot 扫描异常拦截类
@SpringBootApplication(scanBasePackages = {"com.example.demo"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /* WebSocket部署在Spring容器中 */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /* Quartz 默认启动*/
    /*@Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(TimeJob.class)
                .withIdentity("timeJob")
                .usingJobData("name", "Quartz")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        // 如果需要使用 cronExpression 表达式，则使用 CronScheduleBuilder 进行创建
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob(sampleJobDetail())
                .withIdentity("timeTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }*/
}
