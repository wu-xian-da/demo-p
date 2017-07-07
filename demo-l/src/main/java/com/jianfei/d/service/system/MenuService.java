/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日下午4:31:52
  */
package com.jianfei.d.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.system.MenuDao;
import com.jianfei.d.entity.common.TreeVo;
import com.jianfei.d.entity.system.Menu;

@Service
public class MenuService extends CrudService<MenuDao,Menu>{
	
	public List<TreeVo> findTree(){
		return this.dao.findTree();
	}
	
	public List<Menu> findParentChild(){
		return this.dao.findParentChild();
	}
}
