/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午9:23:35
  */
package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.info.NavContent;

public interface NavContentDao extends CrudDao<NavContent> {

	public int deleteByNavId(Long navId);
	
	public int updateByNavId(NavContent navContent);
	
	public NavContent getByNavId(Long navId);
	
	public List<NavContent> getListByTemplateId(Long contentTemplate);
}
