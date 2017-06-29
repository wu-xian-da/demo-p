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
	
	LB("列表页模板",1),NR("内容页模板",2);
	
	private String name;
	
	private int value;
	
	public static String getName(int value){
		for (TemplateType u : TemplateType.values()) {
			if (u.getValue() == value) {
				return u.name;
			}
		}
		return null;
	}
	
	private TemplateType(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
