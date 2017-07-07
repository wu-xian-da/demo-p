/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日下午3:39:31
  */
package com.jianfei.d.dao.system;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.common.TreeVo;
import com.jianfei.d.entity.system.Menu;

public interface MenuDao extends CrudDao<Menu> {
	
	public List<TreeVo> findTree();
	
	public List<Menu> findMenuForRole();
	
}
