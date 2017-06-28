/**
  *project demo-m
  *@author changchun.wu
  *2017年6月27日下午5:10:41
  */
package com.jianfei.d.base.shiro;

/****
 * 自定义用户名,密码,验证码令牌
 * @author changchun.wu
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken{

	public UsernamePasswordToken(){
		super();
	}
	
	public UsernamePasswordToken(String username,char[] password,boolean rememberMe,String host){
		super(username,password,rememberMe,host);
	}
}
