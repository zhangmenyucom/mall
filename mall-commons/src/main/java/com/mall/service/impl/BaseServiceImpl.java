package com.mall.service.impl;
/**
 * ${author} on 2018/9/19.
 */

import com.mall.annotation.MerchantFilter;
import com.mall.dao.BaseDao;
import com.mall.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/9/19 11:42
 */
public abstract class BaseServiceImpl<T, Dao extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    private Dao dao;

    public Dao getDao() {
        return this.dao;
    }

    @Override
    public T queryObject(Long id) {
        return this.dao.queryObject(id);
    }

    @Override
    public List<T> queryList(Map<String, Object> map) {
        return this.dao.queryList(map);
    }

    @Override
    public List<T> queryList(Map<String, Object> map, Long merchantId) {
        map.put("merchantId", merchantId);
        return this.dao.queryList(map);
    }

    @Override
    @MerchantFilter
    public int queryTotal(Map<String, Object> map) {
        return this.dao.queryTotal(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map,Long merchantId) {
        map.put("merchantId",merchantId);
        return this.dao.queryTotal(map);
    }

    @Override
    @MerchantFilter
    public int save(T t) {
        return this.dao.save(t);
    }

    @Override
    public int update(T t) {
        return this.dao.update(t);
    }

    @Override
    public int delete(Long id) {
        return this.dao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return this.dao.deleteBatch(ids);
    }
}
