/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午9:27:22
  */
package com.jianfei.d.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.TemplateDao;
import com.jianfei.d.entity.common.TemplateType;
import com.jianfei.d.entity.info.Template;

@Service
public class TemplateService extends CrudService<TemplateDao, Template> {
	
	/****
	 * 根据模板类型查询模板
	 * @param type
	 * @return
	 */
	public List<Template> getListByType(TemplateType type){
		return this.dao.getListByType(type);
	}
}
