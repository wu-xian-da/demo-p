/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日下午3:30:50
  */
package com.jianfei.d.base.filter.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class XssRequestWrapper extends HttpServletRequestWrapper{
	
	private static final Whitelist WHITE_LIST = Whitelist.none();

	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name){
		String value = super.getParameter(name);
		if (value == null) {
			return value;
		}
		return Jsoup.clean(value, WHITE_LIST);
	}
	
	@Override
	public String[] getParameterValues(String name){
		String[] values = super.getParameterValues(name);
		if (values == null) {
			return null;
		}
		
		int length = values.length;
		String[] filterValues = new String[length];
		for (int i = 0; i < length; i++) {
			String value = values[i];
			if (value == null) {
				filterValues[i] = value;
				continue;
			}
			filterValues[i] = Jsoup.clean(value, WHITE_LIST);
		}
		return filterValues;
	}
	
	@Override
	public String getHeader(String name){
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		return Jsoup.clean(value, WHITE_LIST);
	}
}
