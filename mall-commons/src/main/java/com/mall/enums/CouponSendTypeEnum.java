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
public enum CouponSendTypeEnum {
    ORDER_COUPON("0", "用户领取"),
    USER_COUPON("1", "商家指定");

    private String code;
    private String desc;

    public static Map<String, CouponSendTypeEnum> couponTypeEnumMap = new HashMap<>(CouponSendTypeEnum.values().length);

    static {
        Arrays.stream(CouponSendTypeEnum.values()).forEach(item -> couponTypeEnumMap.put(item.getCode(), item));
    }

    CouponSendTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
