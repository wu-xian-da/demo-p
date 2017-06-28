/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日下午3:30:44
  */
package com.jianfei.d.dao.system;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.system.Department;

public interface DepartmentDao extends CrudDao<Department>{
	
	public List<Department> getParent();

}
