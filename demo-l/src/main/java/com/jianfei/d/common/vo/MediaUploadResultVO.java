/**
  *project demo-l
  *@author changchun.wu
  *2017年7月11日下午4:14:16
  */
package com.jianfei.d.common.vo;

/***
 * 微信新增临时素材的接口返回VO
 * @author changchun.wu
 */
public class MediaUploadResultVO {

	private String type;
	
	private String thumb_media_id;
	
	private Long created_at;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}
}
