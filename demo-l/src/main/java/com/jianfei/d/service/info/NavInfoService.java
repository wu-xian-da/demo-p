/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午2:39:05
  */
package com.jianfei.d.service.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavInfoDao;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.NavInfo;

@Service
public class NavInfoService extends CrudService<NavInfoDao, NavInfo> {
	
	/***
	 * 获取某一栏目下的栏目信息
	 * @param navId
	 * @return
	 */
	public List<NavInfo> getListByNavId(Long navId){
		return this.dao.getListByNavId(navId);
	}
	
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
	
	//web begin
	/**
	 * 根据状态,获取某一栏目下的信息数据
	 * @param navId
	 * @param status
	 * @return
	 */
	public List<NavInfo> getListByNavIdAndStatus(Long navId,InfoStatus status){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("navId", navId);
		map.put("status", status);
		return this.dao.getListByNavIdAndStatus(map);
	}
	//web end
}
