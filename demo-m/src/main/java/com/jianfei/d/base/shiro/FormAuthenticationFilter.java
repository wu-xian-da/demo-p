/**
  *project demo-m
  *@author changchun.wu
  *2017年6月28日上午10:48:02
  */
package com.jianfei.d.base.shiro;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jianfei.d.common.config.Constants;
import com.jianfei.d.entity.common.LoginStatus;
import com.jianfei.d.entity.system.LogLogin;
import com.jianfei.d.service.system.LogLoginService;

/****
 * 表单验证(包含验证码)过滤类
 * @author changchun.wu
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{
	
	@Autowired
	private LogLoginService logLoginService;
	
	 protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
	        String username = getUsername(request);
	        String password = getPassword(request);
	        boolean rememberMe = isRememberMe(request);
	        String host = super.getHost(request);

	        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host);
	    }

	    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
	    	ae.printStackTrace();
	    	request.setAttribute(Constants.MESSAGE, "登陆出错");
	        super.setFailureAttribute(request, ae);
	    }

	    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,ServletResponse response) throws Exception {
	        HttpServletRequest httpRequest = WebUtils.getHttpRequest(request);
	        this.logLoginService.save(new LogLogin(getUsername(request),request.getParameterMap().toString(),new Date(),httpRequest.getHeader("User-Agent"),httpRequest.getRemoteAddr(),LoginStatus.Success));
	        return super.onLoginSuccess(token, subject, request, response);
	    }

	    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,ServletResponse response) {
	    	 HttpServletRequest httpRequest = WebUtils.getHttpRequest(request);
		     this.logLoginService.save(new LogLogin(getUsername(request),request.getParameterMap().toString(),new Date(),httpRequest.getHeader("User-Agent"),httpRequest.getRemoteAddr(),LoginStatus.Fail));  
	        return super.onLoginFailure(token, e, request, response);
	    }
}
