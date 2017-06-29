/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午4:10:46
  */
package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;

/***
 * 栏目下信息实体类
 * @author changchun.wu
 */
@Getter
@Setter
public class NavInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4516670942089518702L;
	
	private String title;//标题
	
	private Short type;//类型(1:交通类,2:文章类)
	
	private Short status;//状态(1:待审核,2:审核通过,3:已上刊,4:已下刊)
	
	private Long navId;//所属栏目
	
	private Date checkTime;//发布时间
	
	private Short pushStatus;//推送状态(1:已推送,2:未推送)
	
	private String content;
	
	private Date createTime;
	
	private Date updateTime;
}
