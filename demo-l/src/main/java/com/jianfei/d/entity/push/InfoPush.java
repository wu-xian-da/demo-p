/**
  *project demo-l
  *@author changchun.wu
  *2017年7月10日下午4:35:01
  */
package com.jianfei.d.entity.push;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoPushType;

/****
 * 信息推送模块推送信息
 * @author changchun.wu
 */
@Getter
@Setter
public class InfoPush extends BaseEntity{
	
	@FormQuery
    private String infoName;

    @FormQuery
    private InfoPushType infoType;

    private String infoImg;

    @FormQuery
    private InfoPushStatus pushStatus = InfoPushStatus.DCL;

    private Date pushTime;

    private Date createTime;

    private Date updateTime;

    @FormQuery
    private String pushRange;//信息推送范围

    private String infoContent;
    
    @FormQuery
    private String beginCreateTime;//生成时间检索字段开始
    
    @FormQuery
    private String endCreateTime;//生成时间检索字段结束
    
    @FormQuery
    private String beginPushTime;//推送时间检索字段开始
    
    @FormQuery
    private String endPushTime;//推送时间检索字段结束
}
