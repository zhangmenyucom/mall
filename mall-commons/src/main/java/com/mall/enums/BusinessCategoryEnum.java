package com.mall.enums;/**
 * ${author} on 2018/10/24.
 */

import com.mall.entity.BusinessCategoryEntity;
import lombok.Getter;

import java.util.*;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/10/24 16:57
 */
@Getter
public enum BusinessCategoryEnum {
    FOOD_DRINK(1, "餐饮美食"),
    MALL(2, "百货超市"),
    DIANQI(3, "电器用具"),
    YULE_KTV(4, "娱乐KTV"),
    CHALOU_XIUXIAN(5, "休闲茶楼"),
    FUSHI_XIEBAO(6, "服务鞋包"),
    LUYOU_CHUXIN(7, "旅游出行"),
    QICHE_JIADIAN(8, "汽车家电"),
    MOBILE(9, "手机通讯");

    private Integer code;
    private String name;
    public static Map<Integer, String> businessCategoryMap = new HashMap<>(BusinessCategoryEnum.values().length);
    public static List<BusinessCategoryEntity> businessCategoryEnumList = new ArrayList<>();

    static {
        Arrays.stream(BusinessCategoryEnum.values()).forEach(item -> businessCategoryMap.put(item.getCode(), item.name));
        Arrays.stream(BusinessCategoryEnum.values()).forEach(item -> {
            BusinessCategoryEntity businessCategoryEntity = new BusinessCategoryEntity();
            businessCategoryEntity.setCode(item.getCode());
            businessCategoryEntity.setName(item.getName());
            businessCategoryEnumList.add(businessCategoryEntity);
        });
    }

    BusinessCategoryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
