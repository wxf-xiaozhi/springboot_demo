package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

/**
 * @ClassName: StopWatchTest
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-13 15:59
 */
@SpringBootTest
@Slf4j
public class StopWatchTest {


        @Test
        public  void testStopWatch() throws InterruptedException {
                StopWatch stopWatch = new StopWatch();

                // 任务一模拟休眠3秒钟
                stopWatch.start("TaskOneName");
                Thread.sleep(1000 * 3);
                log.info("当前任务名称：" + stopWatch.currentTaskName());
                stopWatch.stop();

                // 任务一模拟休眠10秒钟
                stopWatch.start("TaskTwoName");
                Thread.sleep(1000 * 10);
                log.info("当前任务名称：" + stopWatch.currentTaskName());
                stopWatch.stop();

                // 任务一模拟休眠10秒钟
                stopWatch.start("TaskThreeName");
                Thread.sleep(1000 * 10);
                log.info("当前任务名称：" + stopWatch.currentTaskName());
                stopWatch.stop();

                // 打印出耗时
                log.info("prettyPrint:{}",stopWatch.prettyPrint());
                log.info("shortSummary:{}",stopWatch.shortSummary());
                // stop后它的值为null
                log.info("currentTaskName:{}",stopWatch.currentTaskName());

                // 最后一个任务的相关信息
                log.info("getLastTaskName:{}",stopWatch.getLastTaskName());
                log.info("getLastTaskInfo:{}",String.valueOf(stopWatch.getLastTaskInfo()));

                // 任务总的耗时  如果你想获取到每个任务详情（包括它的任务名、耗时等等）可使用
                log.info("所有任务总耗时：" + stopWatch.getTotalTimeMillis());
                log.info("任务总数：" + stopWatch.getTaskCount());
                log.info("所有任务详情：" + stopWatch.getTaskInfo());
        }

}
