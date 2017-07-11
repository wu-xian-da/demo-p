/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午2:39:05
  */
package com.jianfei.d.service.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavInfoDao;
import com.jianfei.d.dao.push.InfoPushDao;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoPushType;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.NavInfo;
import com.jianfei.d.entity.push.InfoPush;

@Service
public class NavInfoService extends CrudService<NavInfoDao, NavInfo> {
	
	@Autowired
	private InfoPushDao infoPushDao;
	
	private List<InfoPush> dealPushData(List<NavInfo> navInfoList){
		List<InfoPush> infoPushList = new ArrayList<InfoPush>();
		InfoPush infoPush = null;
		for(NavInfo navInfo : navInfoList){
			navInfo.setPushStatus(InfoPushStatus.YTS);
			
			infoPush = new InfoPush();
			infoPush.setInfoName(navInfo.getTitle());
			infoPush.setInfoType(InfoPushType.LMWZ);
			infoPush.setInfoContent(navInfo.getContent());
			infoPushList.add(infoPush);
		}
		return infoPushList;
	}
	
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
	
	/****
	 * 批量修改推送状态
	 * @param navInfos
	 * @return
	 */
	public int updateNavInfoPushStatusBatch(List<NavInfo> navInfos){
		List<NavInfo> navInfoList = this.dao.getListByIds(navInfos);
		infoPushDao.insertBatch(this.dealPushData(navInfoList));
		this.dao.updateNavInfoPushStatusBatch(navInfoList);
		return 1;
	}
	
	/****
	 * 信息模块
	 * 根据多个id,查询数据
	 * @param navInfos
	 * @return
	 */
	public List<NavInfo> getListByIds(List<NavInfo> navInfos){
		return this.dao.getListByIds(navInfos);
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
