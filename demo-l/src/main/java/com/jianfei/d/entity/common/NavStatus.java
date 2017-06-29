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
	
	ZS("展示",1),QXZS("取消展示",2);
	
	private String name;
	
	private int value;

	public static String getName(int value){
		for (NavStatus u : NavStatus.values()) {
			if (u.getValue() == value) {
				return u.name;
			}
		}
		return null;
	}
	
	private NavStatus(String name, int value) {
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
