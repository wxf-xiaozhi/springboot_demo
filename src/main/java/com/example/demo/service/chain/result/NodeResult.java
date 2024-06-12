package com.example.demo.service.chain.result;

import lombok.Data;

import java.util.Objects;

/**
 * 推送响应公共返回
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/15 11:44
 */
@Data
public class NodeResult<T>{

    private Integer status = 1;




    private String errMsg;


    public void setStatus(Integer status) {
        this.status = status;
        if(this.isSuccess()){
            this.errMsg = "";
        }
    }

    /**
     * 标记push成功后是否需要执行成功的回调
     */
    private Boolean isNeedSuccessCallBack = true;

    private T data;

    public static <T> NodeResult success(T data) {
        NodeResult result = new NodeResult<T>();
        result.setData(data);
        result.setStatus(0);
        result.setErrMsg("");
        return result;
    }

    public static <T> NodeResult build(Integer status, T data) {
        NodeResult result = new NodeResult<T>();
        result.setData(data);
        result.setStatus(status);
        result.setErrMsg("");
        return result;
    }



    public static NodeResult fail() {
        NodeResult result = new NodeResult<>();
        result.setStatus(1);
        result.setErrMsg("系统出现未知错误");
        return result;
    }

    public boolean isSuccess() {
        return Objects.nonNull(this.status) && this.status == 0;
    }

//    public static void main(String[] args) {
//        YhSupplyChannelPayload supplyChannel = new YhSupplyChannelPayload();
//        supplyChannel.setResult(1);
//        supplyChannel.setChannelId("1");
//        supplyChannel.setChannelNo("1235");
//        ZlPushCommResult zlPushCommResult = success(supplyChannel);
//        YhSupplyChannelPayload data1 = (YhSupplyChannelPayload)zlPushCommResult.getData();
//        System.out.println(data1.getChannelNo());
//    }
}
