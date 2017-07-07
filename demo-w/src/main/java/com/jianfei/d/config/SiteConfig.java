/**
  *project demo-w
  *@author changchun.wu
  *2017年7月7日下午4:02:52
  */
package com.jianfei.d.config;

/***
 * 站点基础配置
 * @author changchun.wu
 */
public class SiteConfig {
	
	//js/css/img等静态资源服务器
	private String cdnDomain;

	public String getCdnDomain() {
		return cdnDomain;
	}

	public void setCdnDomain(String cdnDomain) {
		this.cdnDomain = cdnDomain;
	}
}
