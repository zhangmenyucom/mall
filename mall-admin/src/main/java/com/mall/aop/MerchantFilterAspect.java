package com.mall.aop;

import com.mall.entity.BaseEntity;
import com.mall.entity.CategoryEntity;
import com.mall.entity.SellerEntity;
import com.mall.utils.Constant;
import com.mall.utils.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 数据过滤，切面处理类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017年10月23日 下午13:33:35
 */
@Aspect
@Component
public class MerchantFilterAspect {
    @Pointcut("@annotation(com.mall.annotation.MerchantFilter)")
    public void merchantFilterCut() {

    }

    /**
     * 前置通知
     *
     * @param point 连接点
     */
    @Before("merchantFilterCut()")
    public void dataFilter(JoinPoint point) throws Exception {
        SellerEntity user = ShiroUtils.getUserEntity();
        if (user != null) {
            for (Object params : point.getArgs()) {
                if (params instanceof BaseEntity) {
                    BaseEntity baseEntity = (BaseEntity) params;
                    baseEntity.setMerchantId(user.getId());
                }
                if (params instanceof Map) {
                    Map map = (Map) params;
                    map.put("merchantId", user.getId());
                }
                if (params instanceof CategoryEntity) {
                    CategoryEntity categoryEntity = (CategoryEntity) params;
                    categoryEntity.setMerchantId(user.getId());
                }
            }
        } /*else {
            for (Object params : point.getArgs()) {
                if (params instanceof BaseEntity) {
                    BaseEntity baseEntity = (BaseEntity) params;
                    if (baseEntity.getMerchantId() == null) {
                        throw new Exception(point.getSignature() + ":merchantId不能为空");
                    }
                }
                if (params instanceof Map) {
                    Map map = (Map) params;
                    if (map.get("merchantId") == null) {
                        throw new Exception(point.getSignature() + ":merchantId不能为空");
                    }
                }
                if (params instanceof CategoryEntity) {
                    CategoryEntity categoryEntity = (CategoryEntity) params;
                    if (categoryEntity.getMerchantId() == null) {
                        throw new Exception(point.getSignature() + ":merchantId不能为空");
                    }
                }
            }
        }*/
    }
}
