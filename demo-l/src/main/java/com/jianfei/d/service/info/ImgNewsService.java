/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午3:30:27
  */
package com.jianfei.d.service.info;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.ImgNewsDao;
import com.jianfei.d.dao.push.InfoPushDao;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoPushType;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.ImgNews;
import com.jianfei.d.entity.push.InfoPush;

@Service
public class ImgNewsService extends CrudService<ImgNewsDao, ImgNews> {
	
	@Autowired
	private InfoPushDao infoPushDao;
	
	private List<InfoPush> dealPushData(List<ImgNews> imgNewsList){
		List<InfoPush> infoPushList = new ArrayList<InfoPush>();
		InfoPush infoPush = null;
		for (ImgNews imgNews : imgNewsList) {
			imgNews.setPushStatus(InfoPushStatus.YTS);
			infoPush = new InfoPush();
			infoPush.setInfoName(imgNews.getTitle());
			infoPush.setInfoType(InfoPushType.TPXW);
			infoPush.setInfoImg(imgNews.getImgPath());
			infoPush.setInfoContent(imgNews.getContent());
			infoPushList.add(infoPush);
		}
		return infoPushList;
	}
	
	/***
	 * 批量修改状态
	 * @param imgNews
	 * @return
	 */
	public int updateImgNewsStatusBatch(List<ImgNews> imgNews){
		return this.dao.updateImgNewsStatusBatch(imgNews);
	}
	
	/***
	 * 批量修改推送状态
	 * @param imgNews
	 * @return
	 */
	public int updateImgNewsPushStatusBatch(List<ImgNews> imgNews){
		List<ImgNews> imgNewsList = this.dao.getListByIds(imgNews);
		infoPushDao.insertBatch(this.dealPushData(imgNewsList));
		this.dao.updateImgNewsPushStatusBatch(imgNewsList);
		return 1;
	}
	
	/****
	 * 信息推送
	 * 根据多个id查询数据
	 * @param imgNewss
	 * @return
	 */
	public List<ImgNews> getListByIds(List<ImgNews> imgNewss){
		return this.dao.getListByIds(imgNewss);
	}
	
	//web begin
	/***
	 * 根据状态,获取数据
	 * @param status
	 * @return
	 */
	public List<ImgNews> getListByStatus(InfoStatus status){
		return this.dao.getListByStatus(status);
	}
	//web end
}
