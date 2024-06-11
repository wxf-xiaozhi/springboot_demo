package com.example.demo.service.chain.push;

import cn.hutool.json.JSONUtil;
import com.example.demo.domain.ProductReport;
import com.example.demo.enums.ZLSystemBizTypeEnum;
import com.example.demo.service.chain.push.result.PushResult;
import com.example.demo.service.chain.commonresult.ZlPushCommResult;
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
public class APush extends AbsPush {



    @Override
    public String getZlBizName() {
        return ZLSystemBizTypeEnum.QJS.getDesc();
    }

    @Override
    public Integer getZlBizType() {
        return ZLSystemBizTypeEnum.QJS.getCode();

    }

    @Override
    public ZlPushCommResult<List<PushResult>> pushZl(ProductReport productReport) {
        log.info("清结算开始推送，report:{}", JSONUtil.toJsonStr(productReport));
        Long reportId = productReport.getId();
        ZlPushCommResult result = ZlPushCommResult.fail();
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
    public void successCallBack(ZlPushCommResult result, ProductReport report) {
        List<PushResult> dataArray = (List<PushResult>)result.getData();
        for (PushResult o : dataArray) {
            log.info("回调：{}",JSONUtil.toJsonStr(o));
        }
    }


}
