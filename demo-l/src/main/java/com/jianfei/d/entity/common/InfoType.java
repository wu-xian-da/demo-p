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
	
	JTL("交通类",1),WZL("文章类",2);
	
	private String name;
	
	private int value;
	
	private InfoType(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	//普通方法
	public static String getName(int value){
		for (InfoType u : InfoType.values()) {
			if (u.getValue() == value) {
				return u.name;
			}
		}
		return null;
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
