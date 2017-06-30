/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午9:17:35
  */
package com.jianfei.d.dao.info;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.info.NavUrl;

public interface NavUrlDao extends CrudDao<NavUrl>{
	
	public int deleteByNavId(Long navId);
	
	public int updateByNavId(NavUrl navUrl);
	
	public NavUrl getByNavId(Long navId);
}
