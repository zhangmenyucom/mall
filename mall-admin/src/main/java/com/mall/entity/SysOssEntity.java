package com.mall.entity;

import com.mall.entity.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 * 表名 sys_oss
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-25 12:13:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOssEntity extends com.mall.entity.BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * URL地址
     */
    private String url;
    /**
     * 创建时间
     */
    private Date createDate;
}
