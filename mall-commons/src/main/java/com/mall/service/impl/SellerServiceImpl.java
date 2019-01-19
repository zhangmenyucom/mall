package com.mall.service.impl;


import com.mall.dao.SellerDao;
import com.mall.entity.SellerEntity;
import com.mall.entity.UserWindowDto;
import com.mall.page.Page;
import com.mall.page.PageHelper;
import com.mall.service.SellerService;
import com.mall.service.SysRoleService;
import com.mall.service.SysUserRoleService;
import com.mall.utils.Constant;
import com.mall.utils.RRException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author 李鹏军
 * @email 516195940@qq.com
 * @date 2016年12月18日 上午9:46:09
 */
@Service("sysUserService")
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sellerDao.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sellerDao.queryAllMenuId(userId);
    }

    @Override
    public SellerEntity queryByUserName(String username) {
        return sellerDao.queryByUserName(username);
    }

    @Override
    public SellerEntity queryObject(Long userId) {
        return sellerDao.queryObject(userId);
    }

    @Override
    public List<SellerEntity> queryList(Map<String, Object> map) {
        return sellerDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sellerDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SellerEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        user.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
        sellerDao.save(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SellerEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
        } else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        sellerDao.update(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userId) {
        sellerDao.deleteBatch(userId);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sellerDao.updatePassword(map);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SellerEntity user) {
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new RRException("新增用户所选角色，不是本人创建");
        }
    }


    @Override
    public Page<UserWindowDto> findPage(UserWindowDto userWindowDto, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        sellerDao.queryListByBean(userWindowDto);
        return PageHelper.endPage();
    }
}
