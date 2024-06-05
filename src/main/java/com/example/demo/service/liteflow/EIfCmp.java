package com.example.demo.service.liteflow;

import com.yomahub.liteflow.core.NodeBooleanComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ACmp
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-04-23 15:38
 */
@Slf4j
@Component("e")
public class EIfCmp extends NodeBooleanComponent {


    @Override
    public boolean processBoolean() throws Exception {
        log.info("if component 组件开始执行");
        return false;
    }
}
