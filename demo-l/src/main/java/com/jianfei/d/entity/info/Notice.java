/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午3:03:22
  */
package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;

/***
 * 紧急公告实体类
 * @author changchun.wu
 */
@Getter
@Setter
public class Notice extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -889210723462472954L;

	private String title;
	
	private Short status;//状态(1:待审核,2:审核通过,3:已上刊,4:已下刊)
	
	private Date checkTime;//发布时间
	
	private Short pushStatus;//推送状态(1:已推送,2:未推送)
	
	private String content;//内容
	
	private Date createTime;
	
	private Date updateTime;
}
