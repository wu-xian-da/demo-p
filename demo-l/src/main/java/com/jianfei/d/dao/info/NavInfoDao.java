/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午2:35:31
  */
package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.info.NavInfo;

public interface NavInfoDao extends CrudDao<NavInfo> {
	
	public int updateNavInfoStatusBatch(List<NavInfo> navInfo);
	
	public int updateNavInfoPushStatusBatch(List<NavInfo> navInfos);

}
