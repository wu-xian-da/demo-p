/**
  *project demo-l
  *@author changchun.wu
  *2017年7月10日下午5:09:20
  */
package com.jianfei.d.common.vo;

/***
 * 微信获取access_token的接口返回VO
 * @author changchun.wu
 */
public class AccessTokenResultVO {

	private String access_token;
	
	private String expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
}
