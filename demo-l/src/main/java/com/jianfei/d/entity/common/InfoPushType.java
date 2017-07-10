/**
  *project demo-l
  *@author changchun.wu
  *2017年7月10日下午4:30:36
  */
package com.jianfei.d.entity.common;

/***
 * 信息推送中的信息类型
 * @author changchun.wu
 */
public enum InfoPushType {
	
	LMWZ("栏目文章"), TPXW("图片新闻"), TZGG("通知公告");
	
	private String name;

	private InfoPushType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
