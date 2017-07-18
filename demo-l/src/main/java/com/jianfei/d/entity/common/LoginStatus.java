/**
  *project demo-l
  *@author changchun.wu
  *2017年7月3日下午2:25:28
  */
package com.jianfei.d.entity.common;

public enum LoginStatus {
	
	Success(0),Fail(1);
	
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	private LoginStatus(int value){
		this.value =value;
	}
	
}
