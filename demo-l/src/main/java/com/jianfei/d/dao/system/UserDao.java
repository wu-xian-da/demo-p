/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日下午3:45:30
  */
package com.jianfei.d.dao.system;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.system.User;

public interface UserDao extends CrudDao<User> {

	public int initPasswordBatch(List<User> users);
	
	public int updateUserStatusBatch(List<User> users);
	
	public User findByLoginName(String loginName);
}
