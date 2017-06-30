/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午11:06:21
  */
package com.jianfei.d.service.info;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavContentDao;
import com.jianfei.d.entity.info.NavContent;

@Service
public class NavContentService extends CrudService<NavContentDao, NavContent> {

	Logger logger = Logger.getLogger(this.getClass());
	
	/******
	 * 操作前：先根据navId查询，如果存在，根据navId修改，如果不存在，新增
	 */
	
	/******
	 * 根据navId删除
	 * @param navId
	 * @return
	 */
    public int deleteByNavId(Long navId){
    	int result = 0;
    	try{
    		result = this.dao.deleteByNavId(navId);
    	}catch (Exception e) {
    		logger.error("call:NavContentService.deleteByNavId(Long navId) exception:::" + e);
		}
    	return result;
    }
	
    /******
     * 根据navId修改
     * @param navContent
     * @return
     */
	public int updateByNavId(NavContent navContent){
		int result = 0;
    	try{
    		result = this.dao.updateByNavId(navContent);
    	}catch (Exception e) {
    		logger.error("call:NavContentService.updateByNavId(NavContent navContent) exception:::" + e);
		}
    	return result;
	}
	
	/******
	 * 根据navId查询
	 * @param navId
	 * @return
	 */
	public NavContent getByNavId(Long navId){
		NavContent navContent = null;
		try{
			navContent = this.dao.getByNavId(navId);
		}catch (Exception e) {
    		logger.error("call:NavContentService.getByNavId(Long navId) exception:::" + e);
		}
		return navContent;
	}
	
	/******
	 * 内容模板删除时使用
	 * @param contentTemplateId
	 * @return
	 */
	public List<NavContent> getListByTemplateId(Long contentTemplateId){
		List<NavContent> navContents = null;
		try{
			navContents = this.dao.getListByTemplateId(contentTemplateId);
		}catch (Exception e) {
    		logger.error("call:NavContentService.getListByTemplateId(Long contentTemplateId) exception:::" + e);
		}
		return navContents;
	}
}
