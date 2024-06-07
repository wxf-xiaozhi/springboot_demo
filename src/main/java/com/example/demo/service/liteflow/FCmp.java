package com.example.demo.service.liteflow;

import cn.hutool.core.util.ObjectUtil;
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
@Component("f")
public class FCmp extends NodeComponent {

    @Override
    public void process() {
        Object requestData = this.getRequestData();

        log.info("请求参数：{}", JSONUtil.toJsonStr(requestData));

        MyContext contextBean = this.getContextBean(MyContext.class);
        if(ObjectUtil.isNotNull(contextBean)){
            contextBean.setKey("我是上下文");
        }


    }
}
