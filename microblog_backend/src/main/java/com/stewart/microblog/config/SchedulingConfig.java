package com.stewart.microblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
public class SchedulingConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个定时任务线程池,数量为5
        taskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(5));
    }
}

