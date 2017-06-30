/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午3:46:03
  */
package com.jianfei.d.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NoticeDao;
import com.jianfei.d.entity.info.Notice;

@Service
public class NoticeService extends CrudService<NoticeDao, Notice> {
	
	public int updateNoticeStatusBatch(List<Notice> notices){
		return this.dao.updateNoticeStatusBatch(notices);
	}
	
	public int updateNoticePushStatusBatch(List<Notice> notices){
		return this.dao.updateNoticePushStatusBatch(notices);
	}
}
