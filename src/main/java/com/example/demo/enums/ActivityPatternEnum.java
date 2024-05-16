package com.example.demo.enums;

import lombok.Getter;

import java.util.Optional;

/**
 * 活动模式
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/10 17:12
 */
@Getter
public enum ActivityPatternEnum {

    // 商品提报类型 0-非权益券-电子券活动(都能发活动), 1-个性化立减金活动（有惠返现规则）,
    // 2-权益券-立减金活动,3-权益券-微信红包活动,4-权益券-电子券活动,6-权益券-银联券活动,7-权益券-支付宝红包活动,8-权益券-数币红包活动
    NOT_UNION_PRODUCT_COUPON(0, "非权益券-电子券活动"),
    PERSON_IMMEDIATELY_MONEY(1, "个性化立减金活动"),
    UNION_PRODUCT_IMMEDIATELY_MONEY(2, "权益券-立减金活动"),
    UNION_PRODUCT_WX_RED_PACKAGE(3, "权益券-微信红包活动"),
    UNION_PRODUCT_COUPON(4, "权益券-电子券活动"),
    UNION_PRODUCT_BANK_COUPON(6, "权益券-银联券活动"),
    UNION_PRODUCT_ALIPAY_RED_PACKAGE(7, "权益券-支付宝红包活动"),
    UNION_PRODUCT_DCEP_RED_PACKAGE(8, "权益券-数币红包活动");


    private final Integer code;

    private final String desc;

    ActivityPatternEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ActivityPatternEnum getByCode(Integer code) {
        for (ActivityPatternEnum value : ActivityPatternEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        return Optional.ofNullable(getByCode(code))
                .map(ActivityPatternEnum::getDesc)
                .orElse(null);
    }
}
