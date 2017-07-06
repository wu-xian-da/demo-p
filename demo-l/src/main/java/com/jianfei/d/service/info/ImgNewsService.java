/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午3:30:27
  */
package com.jianfei.d.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.ImgNewsDao;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.ImgNews;

@Service
public class ImgNewsService extends CrudService<ImgNewsDao, ImgNews> {
	
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
		return this.dao.updateImgNewsPushStatusBatch(imgNews);
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
