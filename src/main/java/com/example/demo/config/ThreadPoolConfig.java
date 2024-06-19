package com.example.demo.config;

import com.br.logtrace.multithread.LogTraceThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadPoolConfig
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-06-19 19:57
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {


        @Bean("myThreadPool")
        public ThreadPoolTaskExecutor myTask() {
            ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
            taskExecutor.setCorePoolSize(2);
            taskExecutor.setMaxPoolSize(10);
            taskExecutor.setQueueCapacity(10);
            taskExecutor.setKeepAliveSeconds(10);
            taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            taskExecutor.setThreadFactory(new LogTraceThreadFactory("logTraceThreadPool-"));
            return taskExecutor;
        }

}
