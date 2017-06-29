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

	YJLM("一级栏目",1),EJLM("二级栏目",2);
	
	private String name;
	
	private int value;

	public static String getName(int value){
		for (NavLevel u : NavLevel.values()) {
			if (u.getValue() == value) {
				return u.name;
			}
		}
		return null;
	}
	
	private NavLevel(String name, int value) {
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
