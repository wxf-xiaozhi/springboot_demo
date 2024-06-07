package com.example.demo.service.liteflow;

import cn.hutool.json.JSONUtil;
import com.example.demo.context.MyContext;
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
@Component("g")
public class GCmp extends NodeComponent {

    @Override
    public void process() {
        Object requestData = this.getRequestData();

        log.info("请求参数：{}", JSONUtil.toJsonStr(requestData));


        MyContext contextBean = this.getContextBean(MyContext.class);
        log.info("上下文：{}",JSONUtil.toJsonStr(contextBean));

    }
}
