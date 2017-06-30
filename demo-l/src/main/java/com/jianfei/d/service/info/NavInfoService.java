/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午2:39:05
  */
package com.jianfei.d.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavInfoDao;
import com.jianfei.d.entity.info.NavInfo;

@Service
public class NavInfoService extends CrudService<NavInfoDao, NavInfo> {
	
	/****
	 * 批量修改状态
	 * @param navInfo
	 * @return
	 */
	public int updateNavInfoStatusBatch(List<NavInfo> navInfo){
		return this.dao.updateNavInfoStatusBatch(navInfo);
	}
	
	public int updateNavInfoPushStatusBatch(List<NavInfo> navInfos){
		return this.dao.updateNavInfoPushStatusBatch(navInfos);
	}
}
