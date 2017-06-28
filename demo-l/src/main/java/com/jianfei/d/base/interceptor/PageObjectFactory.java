/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日下午4:44:40
  */
package com.jianfei.d.base.interceptor;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import com.jianfei.d.entity.common.Page;

/*****
 * 创建分页对象,Page按照集合处理
 * @author changchun.wu
 */
public class PageObjectFactory extends DefaultObjectFactory {
	
	public <T> boolean isCollection(Class<T> type){
		if (type == Page.class) {
			return true;
		}
		return super.isCollection(type);
	}
	
	public <T> T create(Class<T> type){
		if (type == Page.class) {
			return (T) new Page();
		}
		return super.create(type);
	}
}
