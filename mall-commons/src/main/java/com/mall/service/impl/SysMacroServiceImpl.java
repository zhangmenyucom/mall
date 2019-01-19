package com.mall.service.impl;

import com.mall.cache.J2CacheUtils;
import com.mall.dao.SysMacroDao;
import com.mall.entity.SysMacroEntity;
import com.mall.service.SysMacroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用字典表Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-22 11:48:16
 */
@Service("sysMacroService")
public class SysMacroServiceImpl implements SysMacroService {
    @Autowired
    private SysMacroDao sysMacroDao;

    @Override
    public SysMacroEntity queryObject(Long macroId) {
        return sysMacroDao.queryObject(macroId);
    }

    @Override
    public List<SysMacroEntity> queryList(Map<String, Object> map) {
        return sysMacroDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysMacroDao.queryTotal(map);
    }

    @Override
    public int save(SysMacroEntity sysMacro) {
        sysMacro.setGmtCreate(new Date());
        sysMacroDao.save(sysMacro);
        J2CacheUtils.put("macroList", queryList(new HashMap<>()));
        return 1;
    }

    @Override
    public int update(SysMacroEntity sysMacro) {
        sysMacro.setGmtModified(new Date());
        sysMacroDao.update(sysMacro);
        J2CacheUtils.put("macroList", queryList(new HashMap<>()));
        return 1;
    }

    @Override
    public int delete(Long macroId) {
        sysMacroDao.delete(macroId);
        J2CacheUtils.put("macroList", queryList(new HashMap<>()));
        return 1;
    }

    @Override
    public int deleteBatch(Long[] macroIds) {
        sysMacroDao.deleteBatch(macroIds);
        J2CacheUtils.put("macroList", queryList(new HashMap<>()));
        return 1;
    }

    @Override
    public List<SysMacroEntity> queryMacrosByValue(String value) {
        return sysMacroDao.queryMacrosByValue(value);
    }
}