/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午8:48:30
  */
package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.info.NavBase;

public interface NavBaseDao extends CrudDao<NavBase> {
	
	public List<NavBase> getParentList();
	
	public List<NavBase> getListByParentId(Long parentId);
	
	public int updateNavBaseStatusBatch(List<NavBase> navBase);
	
	public List<NavBase> getleafList();
	
	//web begin
	
	public List<NavBase> getShowFirstNavList();
	
	public NavBase getShowFirstNavById(Long id);
	
	public List<NavBase> getShowSecNavListByParentId(Long parentId);
	
	//web end

}
