/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午4:59:13
  */
package com.jianfei.d.common.vo;

import lombok.Getter;

@Getter
public enum AuthMethod {

	SMS("短信认证"),WX("微信认证");
	
	private String name;

	private AuthMethod(String name) {
		this.name = name;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public static AuthMethod getAuthMethodByUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
