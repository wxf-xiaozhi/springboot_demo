package com.example.demo.feign;

import com.br.logtrace.constants.LogTraceConstants;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.MDC;

/**
 * @author yu.zhang
 * created on 2022-02-16
 */
@Data
//@ApiModel(value = "通用返回对象", description = "通用返回对象")
public class Result<T> {

//    @ApiModelProperty(value = "状态码", example = "0")
    private Integer code;

//    @ApiModelProperty(value = "信息描述")
    private String message;

//    @ApiModelProperty(value = "返回json数据对象")
    private T data;

//    @ApiModelProperty(value = "traceId")
    private String traceId;

    public Result(){
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.traceId = MDC.get(LogTraceConstants.TRACE_ID);
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = MDC.get(LogTraceConstants.TRACE_ID);
    }

    public Result(ResultEnum enums) {
        this.code = enums.getCode();
        this.message = enums.getMessage();
        this.traceId = MDC.get(LogTraceConstants.TRACE_ID);
    }

    public Result(ResultEnum enums, T data) {
        this.code = enums.getCode();
        this.message = enums.getMessage();
        this.data = data;
        this.traceId = MDC.get(LogTraceConstants.TRACE_ID);
    }
    
    public boolean ok() {
        return ObjectUtils.equals(code, 0);
    }
}
