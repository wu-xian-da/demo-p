/**
  *project demo-l
  *@author changchun.wu
  *2017年7月10日下午4:46:43
  */
package com.jianfei.d.service.push;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.push.InfoPushDao;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.push.InfoPush;

@Service
public class InfoPushService extends CrudService<InfoPushDao, InfoPush> {
	public int updatePushStatusById(Long id,InfoPushStatus pushStatus,Date pushTime,Date updateTime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pushStatus", pushStatus);
	    map.put("pushTime", pushTime);
	    map.put("updateTime", updateTime);
	    return this.dao.updatePushStatusById(map);
	}
}
