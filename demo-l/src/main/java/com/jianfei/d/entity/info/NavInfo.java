/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午4:10:46
  */
package com.jianfei.d.entity.info;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoStatus;

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
	
	@NotBlank(message="信息名称不能为空")
	@Length(max=500,message="信息名称长度不能超过500")
	@FormQuery
	private String title;//标题
	
	@NotBlank(message="信息类型不能为空")
	@FormQuery
	private Integer type;//类型(1:交通类,2:文章类)
	
	@FormQuery
	private Integer status = InfoStatus.DSH.getValue();//状态(1:待审核,2:审核通过,3:已上刊,4:已下刊)
	
	private Long navId;//所属栏目id
	
	@FormQuery("navBase.id")
	private NavBase navBase;//所属栏目
	
	private Date checkTime;//发布时间
	
	private Integer pushStatus = InfoPushStatus.WTS.getValue();//推送状态(1:已推送,2:未推送)
	
	private String content;
	
	private Date createTime;
	
	private Date updateTime;
	
	private List<NavInfo> infos = null;//上刊,下刊,审核通过等
	
	public void fileterNavInfos(){
		if (this.infos == null) {
			return ;
		}
		
		Iterator<NavInfo> iter = this.infos.iterator();
		while (iter.hasNext()) {
			NavInfo r = iter.next();
			if (null == r && null == r.getId()) {
				iter.remove();
			}
		}
	}
}
