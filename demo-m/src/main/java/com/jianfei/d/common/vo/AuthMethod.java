/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午4:59:13
  */
package com.jianfei.d.common.vo;

import com.jianfei.d.common.config.EsConfig;

import lombok.Getter;

@Getter
public enum AuthMethod {

	SMS("sms","短信认证"),WX("wx","微信认证");
	
	private String name;

	private String key;
	
	private String value;
	
	AuthMethod(String key, String value){
		this.key = key;
		this.value = value;
    }
	
	private AuthMethod(String name) {
		this.name = name;
	}

	public static AuthMethod getAuthMethodByUrl(String url){
        if(url == null){
            return null;
        }
        for(AuthMethod method : AuthMethod.values()){
            if(url.startsWith(EsConfig.LOG_AUTH_PREFIX + method.getKey())){
                return method;
            }
        }
        return null;
    }
	
}
