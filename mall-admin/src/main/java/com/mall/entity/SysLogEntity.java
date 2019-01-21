package com.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统日志
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-08 10:40:56
 */
@Data
public class SysLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    //用户名
    private String username;
    //用户操作
    private String operation;
    //请求方法
    private String method;
    //请求参数
    private String params;
    //IP地址
    private String ip;
    //创建时间
    private Date createDate;
}
