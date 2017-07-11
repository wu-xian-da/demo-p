/**
  *project demo-l
  *@author changchun.wu
  *2017年7月11日上午11:38:04
  */
package com.jianfei.d.common.vo;

/***
 * 微信新增永久素材的接口返回VO
 * @author changchun.wu
 */
public class AddMaterialResultVO {
	
	private String media_id;
	
	private String url;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
