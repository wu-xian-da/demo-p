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
	
	public int updateNavBaseStatusBatch(List<NavBase> navBase);
	
	public List<NavBase> getleafList();

}
