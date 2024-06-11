package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品提报表
 *
 * @author haiqiang.xu
 * @date 2024/3/13 17:44
 */
@TableName("t_act_product_report")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductReport extends BaseEntity {

    private static final long serialVersionUID = -6429572980371168499L;

    @TableId(type = IdType.AUTO)

    private Long id;

    private Long customerId;

    private Long projectId;

    /**"活动ID*/
    private Long activityId;

    /**"商品ID*/
    private Long productId;

    /**"商品code*/
    private String couponCode;

    /**"一级业务类型*/
    private Integer firstBizType;

    /**"二级业务类型*/
    private Integer secondBizType;

    /**"流程节点状态 1-待配置组编辑 2-退回(待配置组编辑) 3-待产品经理确认 4-待项目经理确认 5-退回(配置组编辑) 6-推送中 7-推送成功 8-推送失败*/
    private Integer reportStatus;

    /**
     * @see ZlPushService
     */
    /**"商品提报类型 （推送链类型）*/
    private Integer reportType;

    /**"推送成功次数*/
    private Integer pushSuccessNum;

    /**"当前代办人英文，多个逗号隔开*/
    private String approveEn;

    /**"当前代办人中文，多个逗号隔开*/
    private String approveCn;

    /**"项目经理英文*/
    private String projectManagerEn;

    /**"项目经理中文*/
    private String projectManagerCn;

    /**"产品经理英文*/
    private String productManagerEn;

    /**"产品经理中文*/
    private String productManagerCn;

    /**"首次推送成功时间*/
    private Date firstPushSuccessTime;
}
