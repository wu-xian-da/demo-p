/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日下午4:37:10
  */
package com.jianfei.d.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.system.UserDao;
import com.jianfei.d.entity.system.User;

@Service
public class UserService extends CrudService<UserDao, User> {
	
	public int initPasswordBatch(List<User> users){
		return this.dao.initPasswordBatch(users);
	}
	
	public int updateUserStatusBatch(List<User> users){
		return this.dao.updateUserStatusBatch(users);
	}
	
	public User findByLoginName(String loginName){
		return this.dao.findByLoginName(loginName);
	}

}
