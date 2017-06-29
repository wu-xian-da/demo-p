/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午2:49:12
  */
package com.jianfei.d.entity.common;

/***
 * 栏目类型
 * @author changchun.wu
 */
public enum NavType {
	
	XXEJCD("下辖二级菜单",1),WEJCD("无二级菜单",2),URLWL("URL外链",3);
	
	private String name;
	
	private int value;

	public static String getName(int value){
		for (NavType u : NavType.values()) {
			if (u.getValue() == value) {
				return u.name;
			}
		}
		return null;
	}
	
	private NavType(String name, int value) {
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
