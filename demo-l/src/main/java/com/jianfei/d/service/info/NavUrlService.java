/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午9:56:30
  */
package com.jianfei.d.service.info;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavUrlDao;
import com.jianfei.d.entity.info.NavUrl;

@Service
public class NavUrlService extends CrudService<NavUrlDao, NavUrl> {
	
	/****
	 * 操作前:先根据navId查询,如果存在,根据navId修改,如果不存在,新增
	 */
	
	/***
	 * 根据navId删除
	 * @param navId
	 * @return
	 */
	public int deleteByNavId(Long navId){
		return this.dao.deleteByNavId(navId);
	}

	/***
	 * 根据navId修改
	 * @param navUrl
	 * @return
	 */
	public int updateByNavId(NavUrl navUrl){
		return this.dao.updateByNavId(navUrl);
	}
	
	/******
	 * 根据navId查询
	 * @param navId
	 * @return
	 */
	public NavUrl getByNavId(Long navId){
		return this.dao.getByNavId(navId);
	}
}
