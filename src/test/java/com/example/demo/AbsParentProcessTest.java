package com.example.demo;

import com.example.demo.service.lowchain.AChildProcess;
import com.example.demo.service.lowchain.BChildProcess;
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
public class AbsParentProcessTest {

    @Resource
    private AChildProcess aProcess;

    @Resource
    private AChildProcess aChildProcess;

//    @Resource
//    private AbsParentProcess absParentProcess;

    @Resource
    private BChildProcess bProcess;




    @Test
    public void testAProcess() {
        aChildProcess.process();
    }

    @Test
    public void testBProcess() {
        bProcess.process();
    }

//    @Test
//    public void testParentProcess() {
//        absParentProcess.process();
//    }

}
