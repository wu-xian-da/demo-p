/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日下午3:19:12
  */
package com.jianfei.d.base.interceptor;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jianfei.d.common.utils.HttpUtils;
import com.jianfei.d.common.utils.SessionUtils;
import com.jianfei.d.entity.system.LogAccess;
import com.jianfei.d.entity.system.User;
import com.jianfei.d.service.system.LogAccessService;

/**
 * 访问日志拦截
 * @author changchun.wu
 */
public class LogAccessInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private LogAccessService logAccessService;
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{
		User user = SessionUtils.getUser();
		try {
			this.logAccessService.save(new LogAccess(user,new Date(),request.getRequestURI(),this.buildParams(request),HttpUtils.getRemoteAddr(request)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String buildParams(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = request.getParameter(name);
			builder.append(name).append("=").append(value).append("|");
		}
		return builder.toString();
	}
	
}
