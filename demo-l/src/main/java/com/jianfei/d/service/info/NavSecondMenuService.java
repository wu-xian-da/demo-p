/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午11:04:11
  */
package com.jianfei.d.service.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavSecondMenuDao;
import com.jianfei.d.entity.info.NavSecondMenu;

@Service
public class NavSecondMenuService extends CrudService<NavSecondMenuDao, NavSecondMenu> {

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
    		logger.error("call:NavSecondMenuService.deleteByNavId(Long navId) exception:::" + e);
		}
    	return result;
    }
	
    /******
     * 根据navId修改
     * @param navSecondMenu
     * @return
     */
	public int updateByNavId(NavSecondMenu navSecondMenu){
		int result = 0;
    	try{
    		result = this.dao.updateByNavId(navSecondMenu);
    	}catch (Exception e) {
    		logger.error("call:NavSecondMenuService.updateByNavId(NavSecondMenu navSecondMenu) exception:::" + e);
		}
    	return result;
	}
	
	/******
	 * 根据navId查询
	 * @param navId
	 * @return
	 */
	public NavSecondMenu getByNavId(Long navId){
		NavSecondMenu navSecondMenu = null;
    	try{
    		navSecondMenu = this.dao.getByNavId(navId);
    	}catch (Exception e) {
    		logger.error("call:NavSecondMenuService.getByNavId(Long navId) exception:::" + e);
		}
    	return navSecondMenu;
	}
	
	/******
	 * 列表模板或内容模板删除时使用(参数传其一就可)
	 * @param listTemplateId
	 * @param contentTemplateId
	 * @return
	 */
	public List<NavSecondMenu> getListByTemplateId(Long listTemplateId, Long contentTemplateId){
		List<NavSecondMenu> navSecondMenus = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			if(null != listTemplateId && listTemplateId.intValue() > 0){
				map.put("listTemplateId", listTemplateId);
			}
			if(null != contentTemplateId && contentTemplateId.intValue() > 0){
				map.put("contentTemplateId", contentTemplateId);
			}
			
			navSecondMenus = this.dao.getListByTemplateId(map);
    	}catch (Exception e) {
    		logger.error("call:NavSecondMenuService.getListByTemplateId(Long listTemplateId, Long contentTemplateId) exception:::" + e);
		}
		return navSecondMenus;
	}
}
