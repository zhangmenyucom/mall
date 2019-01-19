package com.mall.entity;


import com.mall.validator.group.AddGroup;
import com.mall.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 系统用户
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2016年9月18日 上午9:28:55
 */
@Data
public class SellerEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 密码
     */
    private transient String password;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    /**
     * 部门id
     **/
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 创建人
     **/
    private Long createUserId;

    /**
     * 总佣金
     **/
    private BigDecimal totalBalance;
    /**
     * 可用佣金
     **/
    private BigDecimal avilableBalance;
    /**
     * 冻结佣金
     **/
    private BigDecimal lockBalance;

}
