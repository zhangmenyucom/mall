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
public enum OrderStatusEnum {
    CREATED(0, "订单未支付"),
    CANCEL(101, "已取消"),
    CANCEL_WITH_REFUND(102, "已取消 已退款"),
    DELETE(102, "已删除"),
    PAYED(200, "已支付"),
    WAITTING_SHIP(201, "待开团"),
    SPELL_ING(300, "已经开团"),
    SPELL_SUCESS(301, "拼团成功"),
    REFUND_WITH_SPELL(401, "未开团失败未退款"),
    REFUND_WITHOUT_SPELL(402, "未开团失败退款"),
    SPELL_END(501, "已核销");


    private Integer code;
    private String desc;

    public  static Map<Integer, OrderStatusEnum> orderStatusEnumMap = new HashMap<>(OrderStatusEnum.values().length);

    static {
        Arrays.stream(OrderStatusEnum.values()).forEach(item -> orderStatusEnumMap.put(item.getCode(), item));
    }

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
