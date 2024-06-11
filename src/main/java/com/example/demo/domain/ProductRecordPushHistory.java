package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * (商品提报推送记录表)
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/16 13:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_act_product_report_push_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRecordPushHistory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * ID
     */
    private Long id;
    /**
     * 推送链id
     */
    private Integer chainId;
    /**
     * 提报表ID
     */
    private Long reportId;

    /**
     * 推送类型s
     */
    private String bizTypes;

    /**
     * 推送类型names
     */
    private String bizTypeNames;

    /**
     * 当前推送类型
     */
    private Integer currentType;

    /**
     * 当前推送类型名称
     */
    private String currentTypeName;

    /**
     * 当前推送结果，0-成功，1-失败
     */
    private Integer currentPushResult;


    /**
     * traceId
     */
    private String traceId;


}
