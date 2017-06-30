/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午9:56:30
  */
package com.jianfei.d.service.info;

import org.apache.log4j.Logger;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavUrlDao;
import com.jianfei.d.entity.info.NavUrl;

public class NavUrlService extends CrudService<NavUrlDao, NavUrl> {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	/****
	 * 操作前:先根据navId查询,如果存在,根据navId修改,如果不存在,新增
	 */
	
	/***
	 * 根据navId删除
	 * @param navId
	 * @return
	 */
	public int deleteByNavId(Long navId){
		int result = 0;
		try {
			result = this.dao.deleteByNavId(navId);
		} catch (Exception e) {
			logger.error("call:NavUrlService.deleteByNavId(Long navId) exception:::"+e);
		}
		return result;
	}

	/***
	 * 根据navId修改
	 * @param navUrl
	 * @return
	 */
	public int updateByNavId(NavUrl navUrl){
		int result = 0;
		try {
			result = this.dao.updateByNavId(navUrl);
		} catch (Exception e) {
			logger.error("call:NavUrlService.updateByNavId(NavUrl navUrl) exception:::"+e);
		}
		return result;
	}
	
	/******
	 * 根据navId查询
	 * @param navId
	 * @return
	 */
	public NavUrl getByNavId(Long navId){
		NavUrl navUrl = null;
		try{
			navUrl = this.dao.getByNavId(navId);
		}catch (Exception e) {
    		logger.error("call:NavUrlService.getByNavId(Long navId) exception:::" + e);
		}
		return navUrl;
	}
}
