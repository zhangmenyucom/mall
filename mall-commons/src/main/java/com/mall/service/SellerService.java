package com.mall.service;




import com.mall.entity.SellerEntity;
import com.mall.entity.UserWindowDto;
import com.mall.page.Page;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2016年9月18日 上午9:43:39
 */
public interface SellerService {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SellerEntity queryByUserName(String username);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     * @return
     */
    SellerEntity queryObject(Long userId);

    /**
     * 查询用户列表
     */
    List<SellerEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存用户
     */
    void save(SellerEntity user);

    /**
     * 修改用户
     */
    void update(SellerEntity user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    int updatePassword(Long userId, String password, String newPassword);

    /**
     * 根据条件分页查询
     * @param userEntity
     * @param pageNum
     * @return
     */
    Page<UserWindowDto> findPage(UserWindowDto userEntity, int pageNum);
}
