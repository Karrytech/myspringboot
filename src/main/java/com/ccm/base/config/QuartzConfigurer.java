package com.ccm.base.config;

import org.quartz.Trigger;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@EnableScheduling
@Configuration
public class QuartzConfigurer {

    @Bean(name = "quartzScheduler")
    public SchedulerFactoryBean quartzScheduler(ThreadPoolTaskExecutor threadPoolTaskExecutor,
                                                @Qualifier("job4Quartz1Trigger") Trigger job4Quartz1Trigger,
                                                @Qualifier("trigger2") Trigger trigger2) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setAutoStartup(true);
        scheduler.setStartupDelay(5);//延时5秒启动
        scheduler.setTaskExecutor(threadPoolTaskExecutor);
        scheduler.setTriggers(job4Quartz1Trigger, trigger2);
        return scheduler;
    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(500);
        return executor;
    }
}