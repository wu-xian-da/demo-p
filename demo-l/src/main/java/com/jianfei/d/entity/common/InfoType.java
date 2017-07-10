/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午2:27:37
  */
package com.jianfei.d.entity.common;

/***
 * 信息类型
 * @author changchun.wu
 */
public enum InfoType {
	
	JTL("交通类"),WZL("文章类");
	
	private String name;
	
	
	
	private InfoType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
