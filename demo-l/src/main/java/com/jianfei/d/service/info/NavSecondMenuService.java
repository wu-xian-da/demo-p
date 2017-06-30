/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午11:04:11
  */
package com.jianfei.d.service.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavSecondMenuDao;
import com.jianfei.d.entity.info.NavSecondMenu;

@Service
public class NavSecondMenuService extends CrudService<NavSecondMenuDao, NavSecondMenu> {
	
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
     * @param navSecondMenu
     * @return
     */
	public int updateByNavId(NavSecondMenu navSecondMenu){
		return this.dao.updateByNavId(navSecondMenu);
	}
	
	/******
	 * 根据navId查询
	 * @param navId
	 * @return
	 */
	public NavSecondMenu getByNavId(Long navId){
		return this.dao.getByNavId(navId);
	}
	
	/******
	 * 列表模板或内容模板删除时使用(参数传其一就可)
	 * @param listTemplateId
	 * @param contentTemplateId
	 * @return
	 */
	public List<NavSecondMenu> getListByTemplateId(Long listTemplateId, Long contentTemplateId){
		Map<String,Object> map = new HashMap<String,Object>();
		return this.dao.getListByTemplateId(map);
	}
}
