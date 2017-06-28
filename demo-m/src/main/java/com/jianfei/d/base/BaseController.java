/**
  *project demo-m
  *@author changchun.wu
  *2017年6月27日下午4:00:38
  */
package com.jianfei.d.base;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jianfei.d.common.config.Constants;
import com.jianfei.d.common.utils.SessionUtils;
import com.jianfei.d.entity.system.Menu;

public abstract class BaseController {

	protected Logger log = LoggerFactory.getLogger(getClass());
	
	/****
	 * 添加flash消息
	 * @param redirectAttributes
	 * @param messages
	 */
	protected void addMessage(RedirectAttributes redirectAttributes,String... messages){
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute(Constants.MESSAGE,sb.toString());
	}
	
	/***
	 * 添加错误
	 * @param result
	 * @param objectName
	 * @param field
	 * @param defaultMessage
	 */
	protected void addError(BindingResult result,String objectName,String field,String defaultMessage){
		FieldError error = new FieldError(objectName, field, defaultMessage);
		result.addError(error);
	}
	
	protected String getBaseIndex(String baseUrl){
		List<Menu> userMenus = SessionUtils.getSessionAttribute(Constants.USER_MENUS);
		for (Menu menu : userMenus) {
			if (menu.getHref().equals(baseUrl)) {
				 /*List<Menu> a = m.getChildren();
                if(a.size() > 0 && a.get(0).getChildren().size() > 0){
                    return "redirect:" + a.get(0).getChildren().get(0).getUrl();
                }*/
			}
		}
		return null;
	}
	
	/****
	 * 使用springmvc提交数组是,如果list大小超过256,就会报错
	 * @param binder
	 */
	public void initBinder(WebDataBinder binder){
		binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
