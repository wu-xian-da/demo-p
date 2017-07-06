/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午3:14:13
  */
package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;

/***
 * 栏目扩展实体类-下辖二级菜单
 * @author changchun.wu
 */
@Getter
@Setter
public class NavSecondMenu extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -679985842801208799L;
	
	private String menuHeadIcon;//头部图片路径
	
	private Long menuListTemplateId;//列表模板id
	
	private Long menuContentTemplateId;//内容模板id
	
	private Long navId;//所属基础栏目id
	
	private String navName;//所属栏目名称
	
	private Date createTime;
	
	private Date updateTime;
	
	private Template menuListTemplate = null;//列表模板
	
	private Template menuContentTemplate = null;//内容模板

}
