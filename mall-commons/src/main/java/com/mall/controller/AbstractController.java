package com.mall.controller;

import com.mall.entity.SellerEntity;
import com.mall.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SellerEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
}
