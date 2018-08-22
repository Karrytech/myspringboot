package com.ccm.bi.task.task;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzJobConfig {
    @Bean
    public MethodInvokingJobDetailFactoryBean job4Quartz1Job(Task4Quartz1 task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false);// 是否并发执行
        jobDetail.setTargetObject(task);
        jobDetail.setTargetMethod("timeTask");
        return jobDetail;
    }

    @Bean
    public CronTriggerFactoryBean job4Quartz1Trigger(@Qualifier("job4Quartz1Job") MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setCronExpression("20 0/40 * * * ?");
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setName("who im I");// trigger的name
        return trigger;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean job4Quartz1task1Job(Task4Quartz1 task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

        jobDetail.setConcurrent(false);// 是否并发执行
        jobDetail.setTargetObject(task);
        jobDetail.setTargetMethod("timeTask1");

        return jobDetail;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger2(@Qualifier("job4Quartz1task1Job") JobDetail jobDetail){
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        trigger.setRepeatInterval(3600000);
        trigger.setName("同问");
//        trigger.setRepeatCount(4);
        return trigger;
    }
}
