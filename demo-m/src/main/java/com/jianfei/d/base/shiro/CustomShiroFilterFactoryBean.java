/**
  *project demo-m
  *@author changchun.wu
  *2017年6月28日上午9:38:10
  */
package com.jianfei.d.base.shiro;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.springframework.util.StringUtils;

/****
 * 重写ShiroFilterFactoryBean
 * 提取DefaultFilterChainManager
 * @author changchun.wu
 */
public class CustomShiroFilterFactoryBean extends ShiroFilterFactoryBean{
	
	private FilterChainManager filterChainManager;
	
	/***
	 * 重写创建DefaultFilterChainManager方法
	 */
	protected FilterChainManager createFilterChainManager(){
		FilterChainManager manager = getFilterChainManager();
		Map<String,Filter> defaultFilters = manager.getFilters();
		//apply global settings if necessary
		for (Filter filter : defaultFilters.values()) {
			applyGlobalPropertiesIfNecessary(filter);
		}
		
		//Apply the acquired and/or configured filters
		Map<String,Filter> filters = getFilters();
		if (!CollectionUtils.isEmpty(filters)) {
			for (Map.Entry<String, Filter> entry : filters.entrySet()) {
				String name = entry.getKey();
				Filter filter = entry.getValue();
				applyGlobalPropertiesIfNecessary(filter);
				if (filter instanceof Nameable) {
					((Nameable) filter).setName(name);
				}
				/*
				 *'init' argument is false,since Spring-configured filters should be initialized
				 *in Spring(i.e. 'init-method=blah') or implement InitializingBean: 
				 */
				manager.addFilter(name, filter,false);
			}
		}
		//build up the chains
		Map<String, String> chains = getFilterChainDefinitionMap();
		if (!CollectionUtils.isEmpty(chains)) {
			for (Map.Entry<String,String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue();
				manager.createChain(url, chainDefinition);
			}
		}
		return manager;
	}

	private void applyGlobalPropertiesIfNecessary(Filter filter){
		
		applyLoginUrlIfNecessary(filter);
		
		applySuccessUrlIfNecessary(filter);
		
		applyUnauthorizedUrlIfNecessary(filter);
		
	}
	
	private void applyLoginUrlIfNecessary(Filter filter){
		String loginUrl = getLoginUrl();
		if (StringUtils.hasText(loginUrl) && (filter instanceof AccessControlFilter)) {
			AccessControlFilter acFilter = (AccessControlFilter) filter;
			//only apply the login url if they haven't explicitly configured one already:
			String existingLoginUrl = acFilter.getLoginUrl();
			if (AccessControlFilter.DEFAULT_LOGIN_URL.equals(existingLoginUrl)) {
				acFilter.setLoginUrl(loginUrl);
			}
		}
	}
	
	private void applySuccessUrlIfNecessary(Filter filter){
		String successUrl = getSuccessUrl();
        if (StringUtils.hasText(successUrl) && (filter instanceof AuthenticationFilter)) {
            AuthenticationFilter authcFilter = (AuthenticationFilter) filter;
            //only apply the successUrl if they haven't explicitly configured one already:
            String existingSuccessUrl = authcFilter.getSuccessUrl();
            if (AuthenticationFilter.DEFAULT_SUCCESS_URL.equals(existingSuccessUrl)) {
                authcFilter.setSuccessUrl(successUrl);
            }
        }
	}
	
	private void applyUnauthorizedUrlIfNecessary(Filter filter){
		String unauthorizedUrl = getUnauthorizedUrl();
        if (StringUtils.hasText(unauthorizedUrl) && (filter instanceof AuthorizationFilter)) {
            AuthorizationFilter authzFilter = (AuthorizationFilter) filter;
            //only apply the unauthorizedUrl if they haven't explicitly configured one already:
            String existingUnauthorizedUrl = authzFilter.getUnauthorizedUrl();
            if (existingUnauthorizedUrl == null) {
                authzFilter.setUnauthorizedUrl(unauthorizedUrl);
            }
        }
	}
	
	public FilterChainManager getFilterChainManager() {
		// TODO Auto-generated method stub
		return filterChainManager;
	}
	
	public void setFilterChainManager(FilterChainManager filterChainManager){
		this.filterChainManager = filterChainManager;
	}
}
