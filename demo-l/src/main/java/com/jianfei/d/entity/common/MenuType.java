/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日下午4:57:34
  */
package com.jianfei.d.entity.common;

import lombok.Getter;

@Getter
public enum MenuType {
	MENU("菜单"),BUTTON("按钮");
	
	private String name;
	
	MenuType (String name){
		this.name = name;
	}
}
