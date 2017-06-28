/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日下午4:34:32
  */
package com.jianfei.d.base.interceptor;

import java.util.List;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;

import com.jianfei.d.base.filter.page.PageContext;
import com.jianfei.d.entity.common.Page;
import com.jianfei.d.entity.common.PageParam;

/***
 * 对象封装把Mybatis List 数据封装为Page对象
 * @author changchun.wu
 */
public class PageObjectWrapper implements ObjectWrapper {
	
	private Page page;

	public PageObjectWrapper(Page page) {
		this.page = page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E> void addAll(List<E> element){
		PageParam param = PageContext.getPageParam();
		this.page.setData(element);
		this.page.setPageNo(param.getPn());
		this.page.setPageSize(param.getPs());
		this.page.setTotalRecord(param.getTotalRecord());
	}
	
	@Override
	public Object get(PropertyTokenizer prop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(PropertyTokenizer prop, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String findProperty(String name, boolean useCamelCaseMapping) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getGetterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSetterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getSetterType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getGetterType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSetter(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasGetter(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MetaObject instantiatePropertyValue(String name,PropertyTokenizer prop, ObjectFactory objectFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCollection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(Object element) {
		// TODO Auto-generated method stub

	}

}
