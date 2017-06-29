/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午4:28:15
  */
package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;
/***
 * 栏目基础实体类
 * @author changchun.wu
 */
@Getter
@Setter
public class NavBase extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7195497965682548835L;
	
	private String navName;//名称

    private Short navType;//类型(1:下辖二级菜单;2:无二级菜单;3:URL外链)

    private Integer navLevel;//层级(1:一级栏目;2:二级栏目;)

    private String navIcon;//图标路径

    private Integer navOrderNum;//排序权重

    private Short navStatus;//状态(1:展示;2:取消展示;)

    private Long parentId;//父级栏目ID

    private Date createTime;

    private Date updateTime;
    
    private NavSecondMenu navSecondMenu = null;//下辖二级菜单内容
    
    private NavContent navContent = null;//无二级菜单内容
    
    private NavUrl navUrl = null;//URL外链内容

}
