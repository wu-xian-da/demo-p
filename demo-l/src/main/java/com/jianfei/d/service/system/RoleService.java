/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日下午4:16:32
  */
package com.jianfei.d.service.system;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.system.RoleDao;
import com.jianfei.d.entity.system.Role;

@Service
public class RoleService extends CrudService<RoleDao,Role>{
	
	public int save(Role entity){
		this.dao.insert(entity);
		return this.dao.insertRoleMenu(entity);
	}
	
	public int delete(Long id){
		this.dao.deleteRoleMenu(id);
		return this.dao.delete(id);
	}
	
	public int update(Role entity){
		this.dao.deleteRoleMenu(entity.getId());
		this.dao.update(entity);
		this.dao.insertRoleMenu(entity);
		return super.update(entity);
	}
	
	public Integer getCountByRoleName(Role role){
		return this.dao.getCountByRoleName(role);
	}

}
