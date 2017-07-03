/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午9:07:42
  */
package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.info.Template;

public interface TemplateDao extends CrudDao<Template> {
	
	public List<Template> getListByType(Integer type);

}
