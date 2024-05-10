package com.example.demo;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.easyexcel.DemoData;
import com.example.demo.service.easyexcel.DemoDataListener;
import com.example.demo.utils.TestFileUtil;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    public void getEasyExcel1() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
        log.info(response.getExecuteStepStrWithTime());
    }

}
