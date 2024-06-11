package com.example.demo.service.chain.push.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Description 调用清结算的返回值
 * @Author @yao.liu
 * @Date 2024/3/18 14:58
 * @Version 1.0
 */
@Data
public class PushResult {

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
    public static PushResult fail() {
        PushResult result = new PushResult();
        result.setReturnCode("999");
        result.setReturnMsg("系统出现未知错误");
        return result;
    }


}
