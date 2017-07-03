/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午2:54:07
  */
package com.jianfei.d.entity.common;

/**
 * 模板类型
 * @author changchun.wu
 */
public enum TemplateType {
	
	LB("列表页模板"),NR("内容页模板");
	
	private String name;
	
	private TemplateType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
