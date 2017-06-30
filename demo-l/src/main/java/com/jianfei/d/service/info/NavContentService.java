/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午11:06:21
  */
package com.jianfei.d.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavContentDao;
import com.jianfei.d.entity.info.NavContent;

@Service
public class NavContentService extends CrudService<NavContentDao, NavContent> {
	
	/******
	 * 操作前：先根据navId查询，如果存在，根据navId修改，如果不存在，新增
	 */
	
	/******
	 * 根据navId删除
	 * @param navId
	 * @return
	 */
    public int deleteByNavId(Long navId){
    	return this.dao.deleteByNavId(navId);
    }
	
    /******
     * 根据navId修改
     * @param navContent
     * @return
     */
	public int updateByNavId(NavContent navContent){
		return this.dao.updateByNavId(navContent);
	}
	
	/******
	 * 根据navId查询
	 * @param navId
	 * @return
	 */
	public NavContent getByNavId(Long navId){
		return this.dao.getByNavId(navId);
	}
	
	/******
	 * 内容模板删除时使用
	 * @param contentTemplateId
	 * @return
	 */
	public List<NavContent> getListByTemplateId(Long contentTemplateId){
		return this.dao.getListByTemplateId(contentTemplateId);
	}
}
