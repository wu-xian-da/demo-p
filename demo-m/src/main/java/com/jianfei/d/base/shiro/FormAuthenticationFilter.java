/**
  *project demo-m
  *@author changchun.wu
  *2017年6月28日上午10:48:02
  */
package com.jianfei.d.base.shiro;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.jianfei.d.common.config.Constants;
import com.jianfei.d.common.utils.HttpUtils;
import com.jianfei.d.entity.common.LoginStatus;
import com.jianfei.d.entity.system.LogLogin;
import com.jianfei.d.service.system.LogLoginService;

/****
 * 表单验证(包含验证码)过滤类
 * @author changchun.wu
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{
	
	private static final String UA = "User-Agent";
	
	@Autowired
	private LogLoginService logLoginService;
	
	/*protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        boolean rememberMe = isRememberMe(request);
        String host = super.getHost(request);

        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host);
    }*/

    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
    	ae.printStackTrace();
    	request.setAttribute(Constants.MESSAGE, ae.getMessage());
        super.setFailureAttribute(request, ae);
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
            ServletResponse response) throws Exception {
        
        this.saveLogLogin(request, LoginStatus.Success);
        return super.onLoginSuccess(token, subject, request, response);
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
            ServletResponse response) {
        
        this.saveLogLogin(request, LoginStatus.Fail);
        return super.onLoginFailure(token, e, request, response);
    }
    
    private void saveLogLogin(ServletRequest request, LoginStatus status){
    	System.out.println("----------------------"+status);
        ShiroHttpServletRequest httpRequest = (ShiroHttpServletRequest)request;
        try{
            this.logLoginService.save(new LogLogin(
                    getUsername(request), 
                    new Date(), 
                    httpRequest.getHeader(UA), 
                    HttpUtils.getRemoteAddr(httpRequest), 
                    status));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
