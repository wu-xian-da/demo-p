/**
  *project demo-m
  *@author changchun.wu
  *2017年6月28日上午10:48:02
  */
package com.jianfei.d.base.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import com.jianfei.d.common.config.Constants;

/****
 * 表单验证(包含验证码)过滤类
 * @author changchun.wu
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{
	
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

	    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
	            ServletResponse response) throws Exception {
	        
	        return super.onLoginSuccess(token, subject, request, response);
	    }

	    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
	            ServletResponse response) {
	        
	        return super.onLoginFailure(token, e, request, response);
	    }
}
