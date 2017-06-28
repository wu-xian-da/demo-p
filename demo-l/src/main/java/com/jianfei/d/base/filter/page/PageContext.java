/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日上午9:28:32
  */
package com.jianfei.d.base.filter.page;

import com.jianfei.d.entity.common.PageParam;

/***
 * Page 上下文传递
 * @author changchun.wu
 */
public class PageContext {
	private static ThreadLocal<PageParam> PAGE_PARAM = new ThreadLocal<PageParam>();
	
	public static PageParam getPageParam(){
		return PAGE_PARAM.get();
	}
	
	public static void setPageParam(PageParam _pageParam){
		
		if (_pageParam == null) {
			return;
		}
		PAGE_PARAM.set(_pageParam);
	}
	
	public static void clearPageParam(){
		PAGE_PARAM.remove();
	}
}
