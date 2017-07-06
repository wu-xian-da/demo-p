/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午4:26:33
  */
package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;

/***
 * 栏目扩展实体类-无二级菜单
 * @author changchun.wu
 */
@Getter
@Setter
public class NavContent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7154854927831895097L;
	
	private String contentHeadIcon;//头部图片路径

    private Long contentTemplateId;//内容模板ID

    private Long navId;//所属基础栏目ID
    
    private String navName;//所属栏目名称

    private Date createTime;

    private Date updateTime;
    
    private Template contentTemplate = null;//内容模板

}
