package com.example.demo.service.chain;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.service.chain.commonresult.ZlCommonPayload;
import lombok.Data;

/**
 * @Description 调用清结算的返回值
 * @Author @yao.liu
 * @Date 2024/3/18 14:58
 * @Version 1.0
 */
@Data
public class SettleNotifyResult extends ZlCommonPayload {

    /**
     * 响应码 00：成功，其他：错误
     */
    @JSONField(name = "return_code")
    private String returnCode;

    /**
     * 响应码描述
     */
    @JSONField(name = "return_msg")
    private String returnMsg;

    /**
     * 主键，新增接口返回的值
     */
    @JSONField(name = "uuid")
    private String uuid;


    private Long brSettleId;

    public boolean isSuccess() {
        return "00".equals(this.returnCode);
    }

    /**
     * 构建失败的返回信息
     */
    public static SettleNotifyResult fail() {
        SettleNotifyResult result = new SettleNotifyResult();
        result.setReturnCode("999");
        result.setReturnMsg("系统出现未知错误");
        return result;
    }


}
