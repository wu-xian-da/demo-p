/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日下午3:11:50
  */
package com.jianfei.d.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class HttpUtils {

	/***
	 * 获取用户远程地址
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
		if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
}
