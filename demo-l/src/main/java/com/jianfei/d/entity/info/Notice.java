/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午3:03:22
  */
package com.jianfei.d.entity.info;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.annotation.FormQuery;
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

	@FormQuery
	private String title;
	
	@FormQuery
	private Short status;//状态(1:待审核,2:审核通过,3:已上刊,4:已下刊)
	
	private Date checkTime;//发布时间
	
	private Short pushStatus;//推送状态(1:已推送,2:未推送)
	
	private String content;//内容
	
	private Date createTime;
	
	private Date updateTime;
	
	@FormQuery
	private String beginCheckTime;//时间检索字段
	
	@FormQuery
	private String endCheckTime;//时间检索字段
	
	public void setBeginCheckTime(String beginCheckTime){
		this.beginCheckTime = beginCheckTime;
	}
	
	public Date getBeginCheckTime(){
		if (StringUtils.isNotBlank(this.beginCheckTime)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = df.parse(this.beginCheckTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
		} else {
			return null;
		}
	}
	
	public void setEndCheckTime(String endCheckTime){
		this.endCheckTime = endCheckTime;
	}
	public Date getEndCheckTime(){
		if(StringUtils.isNotBlank(this.endCheckTime)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = df.parse(this.endCheckTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}else{
			return null;
		}
	}
}
