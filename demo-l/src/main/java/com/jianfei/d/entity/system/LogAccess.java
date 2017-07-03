/**
  *project demo-l
  *@author changchun.wu
  *2017年7月3日下午2:12:47
  */
package com.jianfei.d.entity.system;

import java.util.Date;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.base.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogAccess extends BaseEntity{
	
	@FormQuery("user.loginName")
	private User user;
	
	private Date date;
	
	private String requestUrl;
	
	private String params;
	
	@FormQuery
	private String ip;

}
