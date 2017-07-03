/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日下午4:01:57
  */
package com.jianfei.d.common.vo;

import lombok.Getter;

@Getter
public enum MessageStatus {
	SUC("info"),WARN("warning"),ERROR("danger");
	
	private String name;

	private MessageStatus(String name) {
		this.name = name;
	}
}
