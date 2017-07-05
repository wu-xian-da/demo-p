/**
  *project demo-l
  *@author changchun.wu
  *2017年6月30日上午11:08:25
  */
package com.jianfei.d.service.info;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NavBaseDao;
import com.jianfei.d.dao.info.NavContentDao;
import com.jianfei.d.dao.info.NavSecondMenuDao;
import com.jianfei.d.dao.info.NavUrlDao;
import com.jianfei.d.entity.common.NavLevel;
import com.jianfei.d.entity.common.NavType;
import com.jianfei.d.entity.info.NavBase;
import com.jianfei.d.entity.info.NavContent;
import com.jianfei.d.entity.info.NavSecondMenu;
import com.jianfei.d.entity.info.NavUrl;

@Service
public class NavBaseService extends CrudService<NavBaseDao, NavBase> {
	
	@Autowired
	private NavSecondMenuDao navSecondMenuDao;
	
	@Autowired
	private NavContentDao navContentDao;
	
	@Autowired
	private NavUrlDao navUrlDao;
	
	/**
	 * 新增处理
	 * @param navBase
	 * @return
	 */
	@Transactional
	public int addNav(NavBase navBase){
		//一级,二级栏目处理
		if (navBase.getParentId() != null && navBase.getParentId().intValue() >0) {
			navBase.setNavLevel(NavLevel.EJLM);
		} else {
			navBase.setNavLevel(NavLevel.YJLM);
		}
		int result = this.dao.insert(navBase);
		if (result > 0) {
			if (navBase.getNavType() != null && NavType.XXEJCD.equals(navBase.getNavType()) && navBase.getNavSecondMenu() != null) {
				navBase.getNavSecondMenu().setNavId(navBase.getId());
				result = navSecondMenuDao.insert(navBase.getNavSecondMenu());
			} else if(null != navBase.getNavType() && NavType.WEJCD.equals(navBase.getNavType()) && null != navBase.getNavContent()){
				navBase.getNavContent().setNavId(navBase.getId());
				result = navContentDao.insert(navBase.getNavContent());
			} else if(null != navBase.getNavType() && NavType.URLWL.equals(navBase.getNavType()) && null != navBase.getNavUrl()){
				navBase.getNavUrl().setNavId(navBase.getId());
				result = navUrlDao.insert(navBase.getNavUrl());
			}
			
			if (result == 0) {
				throw new RuntimeException("新增栏目手动抛出的业务控制异常...");
			}
		}
		
		return result;
	}
	
	/******
	 * 修改处理
	 * @param navBase
	 * @return
	 */
	@Transactional
	public int updateNav(NavBase navBase) {
		//一级、二级栏目处理
		if(null != navBase.getParentId() && navBase.getParentId().intValue() > 0){
			navBase.setNavLevel(NavLevel.EJLM);
		}else{
			navBase.setNavLevel(NavLevel.YJLM);
		}
				
		int result = this.dao.update(navBase);
		
		if(result > 0){
            if(null != navBase.getNavType() && NavType.XXEJCD.equals(navBase.getNavType()) && null != navBase.getNavSecondMenu()){
            	NavSecondMenu navSecondMenu = navSecondMenuDao.getByNavId(navBase.getId());
            	if(null != navSecondMenu){
            		navBase.getNavSecondMenu().setNavId(navBase.getId());
            		result = navSecondMenuDao.updateByNavId(navBase.getNavSecondMenu());
            	}else{
            		navBase.getNavSecondMenu().setNavId(navBase.getId());
            		result = navSecondMenuDao.insert(navBase.getNavSecondMenu());
            	}
            }else if(null != navBase.getNavType() && NavType.WEJCD.equals(navBase.getNavType()) && null != navBase.getNavContent()){
            	NavContent navContent = navContentDao.getByNavId(navBase.getId());
            	if(null != navContent){
            		navBase.getNavContent().setNavId(navBase.getId());
            		result = navContentDao.updateByNavId(navBase.getNavContent());
            	}else{
            		navBase.getNavContent().setNavId(navBase.getId());
            		result = navContentDao.insert(navBase.getNavContent());
            	}
            }else if(null != navBase.getNavType() && NavType.URLWL.equals(navBase.getNavType()) && null != navBase.getNavUrl()){
            	NavUrl navUrl = navUrlDao.getByNavId(navBase.getId());
            	if(null != navUrl){
            		navBase.getNavUrl().setNavId(navBase.getId());
            		result = navUrlDao.updateByNavId(navBase.getNavUrl());
            	}else{
            		navBase.getNavUrl().setNavId(navBase.getId());
            		result = navUrlDao.insert(navBase.getNavUrl());
            	}
			}
            
            if(result == 0){
            	throw new RuntimeException("修改栏目手动抛出的业务控制异常...");
            }
		}
		
		return result;
	}
	
	/******
	 * 获取某一栏目的栏目
	 * @param parentId
	 * @return
	 */
	public List<NavBase> getListByParentId(Long parentId){
		return this.dao.getListByParentId(parentId);
	}
	
	/******
	 * 获取父级可选择的栏目(操作栏目时使用)
	 * @return
	 */
	public List<NavBase> getParentList(){
		return this.dao.getParentList();
	}
	
	/******
	 * 批量修改展示or不展示
	 * @param navBases
	 * @return
	 */
	public int updateNavBaseStatusBatch(List<NavBase> navBases){
		return this.dao.updateNavBaseStatusBatch(navBases);
	}
	
	/****
	 * 获取可选择的叶子栏目(操作栏目信息时使用)
	 * @return
	 */
	public List<NavBase> getleafList(){
		return this.dao.getleafList();
	}
}
