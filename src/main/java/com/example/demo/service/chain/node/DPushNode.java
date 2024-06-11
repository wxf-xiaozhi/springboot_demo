package com.example.demo.service.chain.node;

import cn.hutool.json.JSONUtil;
import com.example.demo.domain.ProductReport;
import com.example.demo.enums.ZLSystemBizTypeEnum;
import com.example.demo.service.chain.AbsPush;
import com.example.demo.service.chain.SettleNotifyResult;
import com.example.demo.service.chain.ZlPushCommResult;
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
public class DPushNode extends AbsPush {



    @Override
    public String getZlBizName() {
        return ZLSystemBizTypeEnum.YH_PRODUCT_P_COUPON.getDesc();
    }

    @Override
    public Integer getZlBizType() {
        return ZLSystemBizTypeEnum.YH_PRODUCT_P_COUPON.getCode();

    }

    @Override
    public ZlPushCommResult<List<SettleNotifyResult>> pushZl(ProductReport productReport) {
        log.info("清结算开始推送，report:{}", JSONUtil.toJsonStr(productReport));
        Long reportId = productReport.getId();
        ZlPushCommResult result = ZlPushCommResult.fail();
        List<SettleNotifyResult> settleNotifies = new ArrayList<>();
        List<SettleNotifyResult> array = new ArrayList<>();
        for (SettleNotifyResult productSettleNotify : settleNotifies) {
            SettleNotifyResult settleNotifyResult = null;
            // TODO 调用第三方接口
            if(settleNotifyResult.isSuccess()){
                array.add(settleNotifyResult);
            }else{
                settleNotifyResult.setNeedSetCallBackId(false);
            }
        }
        Integer status = array.size() == settleNotifies.size()?0:1;
        result.setStatus(status);
        result.setData(array);
        return result;
    }


    @Override
    public void successCallBack(ZlPushCommResult result, ProductReport report) {
        List<SettleNotifyResult> dataArray = (List<SettleNotifyResult>)result.getData();
        for (SettleNotifyResult o : dataArray) {
            log.info("回调：{}",JSONUtil.toJsonStr(o));
        }
    }


}
