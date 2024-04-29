package com.example.demo.service.liteflow;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ACmp
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-23 15:38
 */
@Slf4j
@Component("c")
public class CCmp extends NodeComponent {

    @Override
    public void process() {
        log.info("--------C------------");
    }
}
