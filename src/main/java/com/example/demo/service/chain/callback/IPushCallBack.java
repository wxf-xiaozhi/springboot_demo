package com.example.demo.service.chain.callback;

import com.example.demo.domain.ProductReport;
import com.example.demo.service.chain.result.NodeResult;

/**
 * (一句话描述该类的功能)
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/9 20:15
 */
public interface IPushCallBack {

     /**
      * 推送众联业务成功后的回调方法，主要向我们业务表回填众联的业务id信息
      * 需要各个推送业务方具体实现
      * @param result
      */
     void successCallBack(NodeResult result, ProductReport report);

     /**
      * 推送失败的回调，absPush的默认处理测试重试2次，各个业务方也可以自定义自己的推送失败处理策略
      * @param productReport
      * @return
      */
     Boolean failCallBack(ProductReport productReport);
}
