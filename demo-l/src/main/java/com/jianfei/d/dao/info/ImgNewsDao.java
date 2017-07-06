/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午3:27:41
  */
package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.ImgNews;

public interface ImgNewsDao extends CrudDao<ImgNews> {
	
	public int updateImgNewsStatusBatch(List<ImgNews> imgNews);
	
	public int updateImgNewsPushStatusBatch(List<ImgNews> imgNews);
	
	//web begin
	
	public List<ImgNews> getListByStatus(InfoStatus status);
	
	//web end
}
