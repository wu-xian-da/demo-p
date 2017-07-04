/**
  *project demo-m
  *@author changchun.wu
  *2017年6月29日上午11:36:52
  */
package com.jianfei.d.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jianfei.d.common.config.Constants;
import com.jianfei.d.common.utils.SessionUtils;
import com.jianfei.d.common.vo.Message;
import com.jianfei.d.common.vo.MessageStatus;
import com.jianfei.d.entity.system.Menu;

public abstract class BaseController {
    
    protected Logger log = LoggerFactory.getLogger(getClass());
    
    /**
     * 添加Flash消息
     * @param message
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        if(model instanceof RedirectAttributes){
            RedirectAttributes redirectAttributes = (RedirectAttributes)model;
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, new Message(sb.toString(), MessageStatus.SUC));
        }
        else{
            model.addAttribute(Constants.MESSAGE, new Message(sb.toString(), MessageStatus.SUC));
        }
    }
    
    /**
     * 添加Flash消息
     * @param message
     */
    protected void addMessage(RedirectAttributes redirectAttributes,MessageStatus status ,String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":" ");
        }
        redirectAttributes.addFlashAttribute(Constants.MESSAGE,new Message(sb.toString(),status));
    }
    
    /**
     * 添加错误
     * @param result
     * @param objectName
     * @param defaultMessage
     */
    protected void addError(BindingResult result, String objectName, String field, String defaultMessage){
        FieldError error = new FieldError(objectName, field, defaultMessage);
        result.addError(error);
    }
    
    @SuppressWarnings("unchecked")
    protected String getBaseIndex(String baseUrl){
        List<Menu> userMenus = ((List<Menu>)SessionUtils.getSessionAttribute(Constants.USER_MENUS));
        for(Menu m : userMenus){
            if(m.getHref().equals(baseUrl)){
               List<Menu> a = m.getChilds();
               if (a.size() > 0 && a.get(0).getChilds().size() > 0) {
            	   return "redirect:"+a.get(0).getChilds().get(0).getHref();
               }
            }
        }
        return null;
    }
    
    @InitBinder  
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, 
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);  
    }
    
}

