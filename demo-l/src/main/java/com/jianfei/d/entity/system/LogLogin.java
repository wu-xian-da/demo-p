/**
  *project demo-l
  *@author changchun.wu
  *2017年7月3日下午2:22:17
  */
package com.jianfei.d.entity.system;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.entity.common.LoginStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogLogin extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FormQuery
	private String loginName;
	
	//private String params;
	
	private Date date;
	
	private String userAgent;
	
	@FormQuery
	private String ip;
	
	private int status;
}
