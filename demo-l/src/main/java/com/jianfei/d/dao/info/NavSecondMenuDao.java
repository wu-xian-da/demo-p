/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午9:19:53
  */
package com.jianfei.d.dao.info;

import java.util.List;
import java.util.Map;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.info.NavSecondMenu;

public interface NavSecondMenuDao extends CrudDao<NavSecondMenu> {
	
	public int deleteByNavId(Long navId);
	
	public int updateByNavId(NavSecondMenu navSecondMenu);
	
	public NavSecondMenu getByNavId(Long navId);
	
	public List<NavSecondMenu> getListByTemplateId(Map<String, Object> map);
}
