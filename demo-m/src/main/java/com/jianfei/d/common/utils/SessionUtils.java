/**
  *project demo-m
  *@author changchun.wu
  *2017年6月27日下午4:49:04
  */
package com.jianfei.d.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jianfei.d.common.config.Constants;
import com.jianfei.d.entity.system.User;

public class SessionUtils {
    
    public static Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){
        }
        return null;
    }

    /**
     * 获取当前登陆用户
     * @return
     */
    public static User getUser(){
        return (User)SecurityUtils.getSubject().getPrincipal();
    }
    
    public static void removeSite(){
        removeSessionArrtibute(Constants.SITE);
    }
    
    /**
     * remove Session Attribute
     * @param key
     */
    public static void removeSessionArrtibute(Object key){
        getSession().removeAttribute(key);
    }
    
    /**
     * set Session Attribute
     * @param key
     * @param value
     */
    public static void setSessionAttribute(Object key, Object value){
        getSession().setAttribute(key, value);
    }
    
    /**
     * get Session Attribute
     * @param key
     */
    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttribute(Object key){
        return (T)getSession().getAttribute(key);
    }
}
