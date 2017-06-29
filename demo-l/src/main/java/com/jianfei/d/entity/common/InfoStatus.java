/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日上午11:52:13
  */
package com.jianfei.d.entity.common;

/***
 * 信息(包括:图片新闻,紧急公告,栏目信息等)状态
 * @author changchun.wu
 */
public enum InfoStatus {
	
	DSH("待审核",1),SHTG("审核通过",2),YSK("已上刊",3),YXK("已下刊",4);

	private String name;
	
	private int value;
	
	private InfoStatus(String name,int value){
		this.name = name;
		this.value = value;
	}
	
	public static String getName(int value){
		for (InfoStatus u : InfoStatus.values()) {
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
