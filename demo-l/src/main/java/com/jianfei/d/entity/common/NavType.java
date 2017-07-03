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
	
	XXEJCD("下辖二级菜单"),WEJCD("无二级菜单"),URLWL("URL外链");
	
	private String name;
	
	private NavType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
