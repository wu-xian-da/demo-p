/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午3:08:46
  */
package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;

/****
 * 栏目扩展实体类-外链
 * @author changchun.wu
 */
@Getter
@Setter
public class NavUrl extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;//外链URL
	
	private String target;//_blank:新页面打开;_self:当前页打开
	
	private Long navId;//所属基础栏目ID
	
	private Date createTime;
	
	private Date updateTime;
}
