/**
  *project demo-w
  *@author changchun.wu
  *2017年7月6日下午5:24:19
  */
package com.jianfei.d.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jianfei.d.config.GlobalSiteConfig;
import com.jianfei.d.config.SiteConfig;

public class BaseController {

	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected SiteConfig siteConfig;
	
	/***
	 * cdnDomain
	 * @return
	 */
	@ModelAttribute(GlobalSiteConfig.CND_DOMAIN)
	public String getCdnDomain(){
		return siteConfig.getCdnDomain();
	}
}
