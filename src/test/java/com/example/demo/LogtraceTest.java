package com.example.demo;

import cn.hutool.core.lang.UUID;
import com.example.demo.feign.SampleFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName: EasyExcelTest
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-28 16:11
 */
@SpringBootTest
@Slf4j
public class LogtraceTest {

    @Autowired
    SampleFeignClient sampleFeignClient;

    @Resource
    ThreadPoolTaskExecutor myThreadPool;

    @Test
    public void testFeign() {
       String mapResult =    sampleFeignClient.sayHello(1);
        log.info("mapResult:{}", mapResult);
    }

    @Test
    public void testThreadPool() {
        MDC.put("traceId", UUID.fastUUID().toString());
        log.info("main thread");
        CompletableFuture<?>[] completableFutures =Arrays.stream(new Integer[]{0,1,2,4,5,6,7,8,9}).map((i)->
                        CompletableFuture.runAsync(() -> {
                            log.info("sub thread "+i);
                        }, myThreadPool)).toArray(CompletableFuture[]::new);

    }

    @Test
    public void testThreadPoolFeign() {
        MDC.put("traceId", UUID.fastUUID().toString());
        log.info("main thread");
        CompletableFuture<?>[] completableFutures =Arrays.stream(new Integer[]{0,1,2,4,5,6,7,8,9}).map((i)->
                CompletableFuture.runAsync(() -> {
                    String mapResult = sampleFeignClient.sayHello(i);
                }, myThreadPool)).toArray(CompletableFuture[]::new);

    }
}


