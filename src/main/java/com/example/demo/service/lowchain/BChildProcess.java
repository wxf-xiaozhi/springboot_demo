package com.example.demo.service.lowchain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyProcess
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-06-11 23:31
 */
@Slf4j
@Component
public class BChildProcess extends AbsParentProcess {
    @Override
    public void process() {
        log.info("BProcess");
        super.process();
    }

    @Override
    public AbsParentProcess getNext() {
        log.info("next");
        return null;
    }

}
