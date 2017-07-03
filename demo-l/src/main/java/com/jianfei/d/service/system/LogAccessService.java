/**
  *project demo-l
  *@author changchun.wu
  *2017年7月3日下午2:29:44
  */
package com.jianfei.d.service.system;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.system.LogAccessDao;
import com.jianfei.d.entity.system.LogAccess;

@Service
public class LogAccessService extends CrudService<LogAccessDao, LogAccess> {

}
