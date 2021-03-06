package com.mall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实体
 * 表名 category
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-21 15:32:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryEntity extends Tree<CategoryEntity> {

    private Long merchantId;
    //主键
    private Long id;
    //分类名称
    private String name;
    //关键字
    private String keywords;
    //描述
    private String frontDesc;
    //父节点
    private Integer parentId;
    //排序
    private Integer sortOrder;
    //首页展示
    private Integer showIndex;
    //显示
    private Integer isShow;
    //banner图片
    private String bannerUrl;
    //icon链接
    private String iconUrl;
    //图片
    private String imgUrl;
    //手机banner
    private String wapBannerUrl;
    //级别
    private String level;
    //类型
    private Integer type;
    //
    private String frontName;

    //翻译用字段
    private String show;

}
