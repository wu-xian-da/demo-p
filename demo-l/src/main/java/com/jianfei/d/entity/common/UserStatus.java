/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日下午4:59:45
  */
package com.jianfei.d.entity.common;

import lombok.Getter;

@Getter
public enum UserStatus {
	OPEN("启用"),CLOSE("禁用");
	
	private String name;
	
	UserStatus(String name){
		this.name = name;
	}
}
