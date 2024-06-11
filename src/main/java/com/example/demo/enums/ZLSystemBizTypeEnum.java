package com.example.demo.enums;

import lombok.Getter;

/**
 * 众联业务类型枚举
 *
 * @version v1.0
 * @Author: xiaofang.wu
 * @Date: 2024/4/10 17:12
 */
@Getter
public enum ZLSystemBizTypeEnum {
    ALL_BIZ(0, "推送全部节点"),
    DNF_SHOP_GOODS_TICKET(1, "都能发商品券"),
    DNF_ACTIVITY(2, "都能发活动"),
    YH_CASH_BACK_RULE(3, "有惠返现规则"),
    YH_SUPPLY_CHANNEL(4, "有惠券渠道"),
    YH_PRODUCT_P_COUPON(5, "有惠券商品"),
    YH_UNION_PRODUCT(6, "有惠权益券"),
    QJS(7, "清结算"),
    DNF_CHANNEL(8, "都能发渠道")

    ;

    private final Integer code;
    private final String desc;

    ZLSystemBizTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ZLSystemBizTypeEnum getByCode(Integer code) {
        for (ZLSystemBizTypeEnum value : ZLSystemBizTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        ZLSystemBizTypeEnum item = getByCode(code);
        if (item != null) {
            return item.getDesc();
        }
        return null;
    }
}
