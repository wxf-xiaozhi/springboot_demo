package com.example.demo.service.chain.push;

import com.example.demo.domain.ProductReport;
import com.example.demo.service.chain.callback.IPushCallBack;
import com.example.demo.service.chain.commonresult.ZlPushCommResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 推送链节点的抽象类
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/9 18:08
 */
@Data
@Slf4j
public abstract class AbsPush implements IPushCallBack {
    /**
     * 获取众联业务名称
     * @return
     */
    public abstract String getZlBizName();

    /**
     * 获取众联业务类型
     * @return
     */
    public abstract Integer getZlBizType();

    /**
     * 推送众联各业务类型的公共方法，各个推送业务方具体实现
     * @param report
     * @return
     */
    public abstract ZlPushCommResult pushZl(ProductReport report);

    /**
     * 检查众联推送结果是否成功
     * @param result
     * @return
     */
    public boolean checkSuccess(ZlPushCommResult result) {
        return result.isSuccess();
    }

    public  Boolean isNeedSuccessCallBack(ZlPushCommResult result){
        return result != null && result.isSuccess() && result.getIsNeedSuccessCallBack();
    }


    @Override
    public abstract void successCallBack(ZlPushCommResult result,ProductReport report);

    @Override
    public Boolean failCallBack(ProductReport report){
//        Integer count = 2;
//        Boolean pushResult = false;
//        while (count > 0){
//            ZlPushCommResult jsonObject = rePush(report,count);
//            pushResult = checkSuccess(jsonObject);
//            if(pushResult){
//                return true;
//            }
//            count--;
//        }
        return false;
    }

    private ZlPushCommResult rePush(ProductReport report, Integer count)  {
        log.warn("开始第{}次重试推送",(2-count+1));
        return this.pushZl(report);
    }
}
