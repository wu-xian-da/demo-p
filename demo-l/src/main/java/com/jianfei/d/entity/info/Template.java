/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午2:59:07
  */
package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.entity.common.TemplateType;

/****
 * 模板实体类
 * @author changchun.wu
 */
@Getter
@Setter
public class Template extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6868110574868809552L;

	private String name;//名称
	
	private String imgPath;//模板图片路径
	
	private String filePath;//模板文件路径
	
	private TemplateType type;//类型(1:列表页模板,2:内容页模板)
	
	private Date createTime;
	
	private Date updateTime;
}
