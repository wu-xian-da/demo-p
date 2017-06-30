/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午11:08:25
  */
package com.jianfei.d.service.info;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavBaseDao;
import com.jianfei.d.entity.info.NavBase;

@Service
public class NavBaseService extends CrudService<NavBaseDao, NavBase> {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	/******
	 * 获取父级可选择的栏目
	 * @return
	 */
	public List<NavBase> getParentList(){
		List<NavBase> navBases = null;
		try{
			navBases = this.dao.getParentList();
		}catch (Exception e) {
			logger.error("call:NavBaseService.getParentList() exception:::" + e);
		}
		return navBases;
	}
	
	/******
	 * 批量修改展示or不展示
	 * @param navBases
	 * @return
	 */
	public int updateNavBaseStatusBatch(List<NavBase> navBases){
		int result = 0;
		try{
			result = this.dao.updateNavBaseStatusBatch(navBases);
		}catch (Exception e) {
			logger.error("call:NavBaseService.updateNavBaseStatusBatch(List<NavBase> navBases) exception:::" + e);
		}
		return result;
	}
}
