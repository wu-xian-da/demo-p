/**
  *project demo-m
  *@author changchun.wu
  *2017年6月28日上午10:36:30
  */
package com.jianfei.d.base.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.jianfei.d.entity.system.Menu;
import com.jianfei.d.service.system.MenuService;

@Service
@Lazy
public class ShiroFilterChainManager {
	
	@Autowired
	private DefaultFilterChainManager filterChainManager;
	
	@Autowired
	private MenuService menuService;
	
	private Map<String, NamedFilterList> defaultFilterChains;
	
	public void init(){
		defaultFilterChains = new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
		List<Menu> menus = this.menuService.findAll();
		initFilterChains(menus);
	}

	public void initFilterChains(List<Menu> menus) {
		filterChainManager.getFilterChains().clear();
        
        if (defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }
        
        for (Menu m : menus) {
            if (StringUtils.isNotBlank(m.getPermission()) && StringUtils.isNotBlank(m.getHref())) {
                filterChainManager.addToChain(m.getHref(), "perms", m.getPermission());
            }
        }
        filterChainManager.addToChain("/sys/**", "user");
	}
}
