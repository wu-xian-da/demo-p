/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午11:08:25
  */
package com.jianfei.d.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavBaseDao;
import com.jianfei.d.entity.info.NavBase;

@Service
public class NavBaseService extends CrudService<NavBaseDao, NavBase> {
	
	/******
	 * 获取父级可选择的栏目(操作栏目时使用)
	 * @return
	 */
	public List<NavBase> getParentList(){
		return this.dao.getParentList();
	}
	
	/******
	 * 批量修改展示or不展示
	 * @param navBases
	 * @return
	 */
	public int updateNavBaseStatusBatch(List<NavBase> navBases){
		return this.dao.updateNavBaseStatusBatch(navBases);
	}
	
	/****
	 * 获取可选择的叶子栏目(操作栏目信息时使用)
	 * @return
	 */
	public List<NavBase> getleafList(){
		return this.dao.getleafList();
	}
}
