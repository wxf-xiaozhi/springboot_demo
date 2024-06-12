package com.example.demo.service.chain.push;

import cn.hutool.json.JSONUtil;
import com.example.demo.domain.ProductReport;
import com.example.demo.enums.ZLSystemBizTypeEnum;
import com.example.demo.service.chain.push.result.PushResult;
import com.example.demo.service.chain.result.NodeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 清结算推送
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/9 19:13
 */
@Slf4j
@Component
public class DPush extends AbsPush {



    @Override
    public String getZlBizName() {
        return ZLSystemBizTypeEnum.YH_PRODUCT_P_COUPON.getDesc();
    }

    @Override
    public Integer getZlBizType() {
        return ZLSystemBizTypeEnum.YH_PRODUCT_P_COUPON.getCode();

    }

    @Override
    public NodeResult<List<PushResult>> pushZl(ProductReport productReport) {
        log.info("清结算开始推送，report:{}", JSONUtil.toJsonStr(productReport));
        Long reportId = productReport.getId();
        NodeResult result = NodeResult.fail();
        List<PushResult> settleNotifies = new ArrayList<>();
        List<PushResult> array = new ArrayList<>();
        for (PushResult productSettleNotify : settleNotifies) {
            PushResult pushResult = null;
            // TODO 调用第三方接口
            if(pushResult.isSuccess()){
                array.add(pushResult);
            }else{
                result.setIsNeedSuccessCallBack(false);
            }
        }
        Integer status = array.size() == settleNotifies.size()?0:1;
        result.setStatus(status);
        result.setData(array);
        return result;
    }


    @Override
    public void successCallBack(NodeResult result, ProductReport report) {
        List<PushResult> dataArray = (List<PushResult>)result.getData();
        for (PushResult o : dataArray) {
            log.info("回调：{}",JSONUtil.toJsonStr(o));
        }
    }


}
