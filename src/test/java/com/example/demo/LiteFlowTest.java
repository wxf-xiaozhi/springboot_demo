package com.example.demo;

import com.example.demo.context.MyContext;
import com.example.demo.param.MyParam;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @ClassName: EasyExcelTest
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-28 16:11
 */
@SpringBootTest
@Slf4j
public class LiteFlowTest {

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    public void getChain1() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
        log.info(response.getExecuteStepStrWithTime());
    }

    @Test
    public void getChain2() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain2", "chain2");
        log.info(response.getExecuteStepStrWithTime());
    }
    @Test
    public void getChain3() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain3", "chain3");
        log.info(response.getExecuteStepStrWithTime());
    }

    @Test
    public void getChain4() {
        MyParam param = new MyParam();
        param.setParamKey("流程入参");
        LiteflowResponse response = flowExecutor.execute2RespWithRid("chain4", param,"requestId", MyContext.class);
        log.info("步骤：{}",response.getExecuteStepStrWithTime());
//        log.info("步骤1：{}", JSONUtil.toJsonStr(response.getExecuteSteps()));
//        log.info("步骤1：{}",JSONUtil.toJsonStr(response.getExecuteStepQueue()));
    }

}
