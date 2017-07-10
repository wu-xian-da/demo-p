/**
  *project demo-l
  *@author changchun.wu
  *2017年7月10日下午4:40:55
  */
package com.jianfei.d.dao.push;

import java.util.Map;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.push.InfoPush;

public interface InfoPushDao extends CrudDao<InfoPush>{
	
	public int updatePushStatusById(Map<String , Object> map);

}
