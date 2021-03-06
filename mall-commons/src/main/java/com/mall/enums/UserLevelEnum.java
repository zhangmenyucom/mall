package com.mall.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/28 22:23
 */
@Getter
public enum UserLevelEnum {
    /**
     * 普通会员
     **/
    NORMAL(1L, "普通会员"),
    /**
     * 白银商家
     **/
    SILVER_SELLER(2L, "白银商家"),
    /**
     * 黄金商家
     **/
    GOLDEN_SELLER(2L, "黄金商家");

    UserLevelEnum(Long levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }

    private Long levelId;

    private String levelName;

    public static Map<Long, UserLevelEnum> LEVEL_MAP = new HashMap<>();

    static {
        Arrays.asList(UserLevelEnum.values()).forEach(e -> LEVEL_MAP.put(e.getLevelId(), e));
    }
}
