/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日上午9:02:25
  */
package com.jianfei.d.entity.system;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.base.entity.BaseEntity;

@Getter
@Setter
public class Department extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6879390533831012019L;
	
	@NotBlank(message = "部门名称不能为空")
	@FormQuery
	private String name;//部门名称
	
	@FormQuery("parent.id")
	private Department parent;//上级部门
	
	private Integer sort = 1;//排序号(默认1)
}
