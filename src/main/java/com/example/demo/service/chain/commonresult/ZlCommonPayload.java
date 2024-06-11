package com.example.demo.service.chain.commonresult;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ZlCommonResult
 * @description: 创建结果响应
 * @author: xiaofang.wu
 * @create: 2024-04-19 19:54
 */
@Data
public class ZlCommonPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标记是否需要设置成功之后的Id到数据库
     */
    private Boolean needSetCallBackId = true;

}
