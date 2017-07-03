/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午2:35:33
  */
package com.jianfei.d.entity.common;

/***
 * 信息栏目状态
 * @author changchun.wu
 */
public enum NavStatus {
	
	ZS("展示"),QXZS("取消展示");
	
	private String name;
	
	private NavStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
