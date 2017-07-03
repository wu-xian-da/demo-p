/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日上午11:45:22
  */
package com.jianfei.d.entity.common;

/******
 * 信息推送(包括：图片新闻、紧急公告、栏目信息等)状态
 * @author 
 *
 */
public enum InfoPushStatus {
	
	YTS("已推送"),WTS("未推送");
	
	private String name;
	
	private InfoPushStatus(String name){
		this.name = name;
	}
	
	/*public static String getName(int value){
		for (InfoPushStatus u : InfoPushStatus.values()) {
			if (u.getValue() == value) {
				return u.name;
			}
		}
		return null;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
