package com.example.demo;

import com.github.rholder.retry.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RetryTest
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 20:53
 */
public class RetryTest {

    public static void main(String[] args) {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("ddddd"+(int)(Math.random()*10));
//        }
        final Map<Long, Attempt> attempts = new HashMap<>();
        RetryListener listener = new RetryListener() {
            @Override
            public <V> void onRetry(Attempt<V> attempt) {
                V result = attempt.getResult();
                System.out.println("================="+result);
                attempts.put(attempt.getAttemptNumber(), attempt);

            }
        };

        Retryer<Boolean> RETRYER = RetryerBuilder.<Boolean>newBuilder()
                //设置异常重试源
                .retryIfExceptionOfType(Exception.class)
                .retryIfRuntimeException()
                .retryIfResult(res ->  !res)
                //设置等待间隔时间
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                //设置最大重试次数
                .withStopStrategy(StopStrategies.stopAfterAttempt(10))
                .withRetryListener(listener)
                .build();


        try {
            RETRYER.call(() -> getResult());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (RetryException e) {
            throw new RuntimeException(e);
        }

        System.out.println("====================重试次数："+attempts.size());

    }
    private static Boolean getResult(){
        int x = (int)(Math.random()*10);
        System.out.println("====================:"+x);
        return x/2 == 1;
    }




}
