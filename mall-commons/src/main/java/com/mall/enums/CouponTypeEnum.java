package com.mall.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/9/6 14:03
 */
@Getter
public enum CouponTypeEnum {
    ORDER_COUPON("0", "代码券"),
    USER_COUPON("1", "折扣券");

    private String code;
    private String desc;

    public static Map<String, CouponTypeEnum> couponTypeEnumMap = new HashMap<>(CouponTypeEnum.values().length);

    static {
        Arrays.stream(CouponTypeEnum.values()).forEach(item -> couponTypeEnumMap.put(item.getCode(), item));
    }

    CouponTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
