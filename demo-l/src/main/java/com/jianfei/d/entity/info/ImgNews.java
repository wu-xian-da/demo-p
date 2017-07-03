/**
  *project demo-l
  *@author changchun.wu
  *2017年6月29日下午4:29:48
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

/****
 * 图片新闻实体类
 * @author changchun.wu
 */
@Getter
@Setter
public class ImgNews extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4395341929777662474L;
	
	@NotBlank(message="图片标题不能为空")
	@Length(max=500,message="图片标题长度不能超过500")
	@FormQuery
	private String title;//图片标题

    private String imgPath;//图片路径

    @FormQuery
    private InfoStatus status = InfoStatus.DSH;//状态(1:待审核;2:审核通过;3:已上刊;4:已下刊)

    private Date checkTime;//发布时间(审核时间)

    private InfoPushStatus pushStatus = InfoPushStatus.WTS;//推送状态(1:已推送;2:未推送)
    
    private String content;//内容

    private Date createTime;

    private Date updateTime;
    
    @FormQuery
    private String beginCheckTime;
    
    @FormQuery
    private String endCheckTime;
    
    private List<ImgNews> imgNews = null;//上刊,下刊,审核通过等
    
    public void fileterImgNewss(){
    	if (this.imgNews == null) {
			return;
		}
    	
    	Iterator<ImgNews> iter = this.imgNews.iterator();
    	while (iter.hasNext()) {
			ImgNews r = iter.next();
			if (r == null && r.getId() == null) {
				iter.remove();
			}
		}
    }

}
