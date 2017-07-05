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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoStatus;

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

	//@NotBlank(message="紧急公告标题不能为空")
	//@Length(max=500, message="紧急公告标题长度不能超过500")
	@FormQuery
	private String title;
	
	@FormQuery
	private InfoStatus status = InfoStatus.DSH;//状态(1:待审核,2:审核通过,3:已上刊,4:已下刊)
	
	private Date checkTime;//发布时间
	
	private InfoPushStatus pushStatus = InfoPushStatus.WTS;//推送状态(1:已推送,2:未推送)
	
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
	
	 //上刊、下刊、审核通过等
	private List<Notice> notices = null;
	
	public void fileterNotices(){
		if (this.notices == null) {
			return ;
		}
		Iterator<Notice> iter = this.notices.iterator();
		while (iter.hasNext()) {
			Notice r = (Notice) iter.next();
			if (r == null && r.getId() == null) {
				iter.remove();
			}
		}
	}
}
