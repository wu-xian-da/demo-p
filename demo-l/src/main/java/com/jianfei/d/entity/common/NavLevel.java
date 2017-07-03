/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午2:31:33
  */
package com.jianfei.d.entity.common;

/***
 * 栏目层级
 * @author changchun.wu
 */
public enum NavLevel {

	YJLM("一级栏目"),EJLM("二级栏目");
	
	private String name;
	
	private NavLevel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
