package com.example.demo.async;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

public class ThreadDeadLockTest {

    private ThreadPoolTaskExecutor executor;

    @BeforeEach
    void setUp() {
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("test-ab-");
        executor.initialize();
    }

    @AfterEach
    void tearDown() {
        if (executor != null) {
            executor.shutdown();
        }
    }


    private String methodA() {
        log("A: start");
        int queueSize = executor.getQueueSize();
        log("A Executor queue size: " + queueSize);

        List<CompletableFuture<Void>> futures =
                IntStream.range(0, 10)
                .mapToObj(x ->  CompletableFuture.runAsync(this::methodB,executor)).collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        log("A: B completed with B, continue A work 10s");

        sleepSeconds(10); // A does another 10 seconds of work after B completes
        String res = "A(B)";
        log("A: end, res=" + res);
        return res;
    }

    private void methodB() {
        log("B: start (10s)");
        sleepSeconds(10); // B takes 10 seconds
        log("B: end");
    }

    private static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private static void log(String msg) {
        String thread = Thread.currentThread().getName();
        String time = java.time.LocalTime.now().toString();
        System.out.println("[" + time + "] [" + thread + "] " + msg);
    }


    @Test
    void testExecutorSync() {

        int queueSize = executor.getQueueSize();
        log("start Executor queue size: " + queueSize);

        List<CompletableFuture<Void>> futures = IntStream.range(0, 10)
                .mapToObj(i ->  CompletableFuture.runAsync(this::methodA, executor))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

//        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        // Allow generous timeout depending on machine; nominally ~20s if ample threads
        // INSERT_YOUR_CODE
        // 每一秒打印一次线程池阻塞队列长度
//        new Thread(() -> {
//            try {
//                while (!all.isDone()) {
//                    int queueSize1 = executor.getQueueSize();
//                    log("Executor queue size: " + queueSize1);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }).start();

//        Assertions.assertTimeoutPreemptively(Duration.ofMinutes(3), all::join);
//
//        long elapsedMs = Duration.between(start, Instant.now()).toMillis();
//        long completed = futures.stream().filter(CompletableFuture::isDone).count();
//        log("Load test finished: completed=" + completed + "/" + n + ", elapsedMs=" + elapsedMs);
//
//        // Spot-check a few results
//        Assertions.assertTrue(futures.stream().limit(5).allMatch(f -> "A(B)".equals(f.join())));
    }
}


