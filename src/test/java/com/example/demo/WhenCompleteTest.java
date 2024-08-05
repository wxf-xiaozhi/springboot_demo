package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName: EasyExcelTest
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-28 16:11
 */
@SpringBootTest
@Slf4j
public class WhenCompleteTest {


    /**
     * whenComplete会返回CompletableFuture，对whenComplete返回的CompletableFuture
     * 使用 CompletableFuture.allOf().join()才能起到效果
     * @throws InterruptedException
     */
    @Test
    public void testThreadPoolFeign() throws InterruptedException {

        CompletableFuture<String> cf1 =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(10*60);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("---------------------{}","cf1");
                    return "测试1";
                });
        CompletableFuture<String> stringCompletableFuture = cf1.whenComplete((str, t) -> {
            log.info("start---------------------{}", "cf1111111111");

            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("---------------------{}", "cf1111111111");
        });

        CompletableFuture<String> cf2 =
                CompletableFuture.supplyAsync(() -> {

                    log.info("---------------------{}","cf2");
                    return "测试2";
                }).whenComplete((vo,t)->{});
        cf2.whenComplete((vo, t) -> {
            log.info("start---------------------{}","cf222222222222222");
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("---------------------{}","cf222222222222222");
        });
        CompletableFuture.allOf(cf1, cf2).join();

        log.info("---------------------{}","main");
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


