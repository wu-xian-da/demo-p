/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日上午11:06:55
  */
package com.jianfei.d.entity.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.entity.common.UserStatus;

@Getter
@Setter
public class User extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 238935356827842071L;
	
	//@NotBlank(message="真实姓名不能为空")
	//@Length(max=20,message="真实姓名长度不能超过20")
	private String name;//真实姓名
	
	//@Length(max=20,message="登录名长度不能超过20")
	//@NotBlank(message="登录名不能为空")
	@FormQuery
	private String loginName;//登录名
	
	//@Length(max=20,message="联系电话长度不能超过20")
	private String tel;
	
	//@NotBlank(message="密码不能为空")
	//@Length(max=32,min=6,message="密码长度(6-32)位")
	private String password;//密码
	
	//@NotNull
	@FormQuery("role.id")
	private Role role;//角色
	
	private Department department;//部门
	
	private UserStatus status = UserStatus.OPEN;//启用,禁用
	
	private Date createDate;//创建时间
	
	private String salt;//加密密码的盐
	
	/****vo start****/
	@FormQuery
	private List<Long> departments;
	
	private String rePassword;//重复密码
	
	private String reTwoPassword;
	
	private List<User> users;//启用,禁用,密码初始化使用
	
	private List<String> isNot = new ArrayList<String>();//排除不显示用户
	
	public void filterUsers(){
		if (users == null) {
			return ;
		}
		Iterator<User> iter = users.iterator();
		
		while (iter.hasNext()) {
			User r = iter.next();
			if (r == null || r.getId() == null) {
				iter.remove();
			}
		}
	}
	
	/***
	 * 加密盐组合
	 * @return
	 */
	public String getCredentialsSalt(){
		return loginName + salt;
	}
	/****vo end****/
}
