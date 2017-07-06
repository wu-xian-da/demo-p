/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日下午3:43:44
  */
package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.Notice;

public interface NoticeDao extends CrudDao<Notice> {
	
	public int updateNoticeStatusBatch(List<Notice> notices);
	
	public int updateNoticePushStatusBatch(List<Notice> notices);
	
	//web begin
	
	public List<Notice> getListByStatus(InfoStatus status);
	
	public List<Notice> getTopNByStatus(InfoStatus status);
	
	//web end
}
