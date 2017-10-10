/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日上午9:40:42
  */
package com.jianfei.d.base.filter.page;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.jianfei.d.entity.common.PageParam;

/******
 * 分页过滤器
 * 拦截浏览器分页参数
 * @author changchun.wu
 */
@WebFilter(urlPatterns="/*",filterName="filter1")
public class PageFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		
		PageContext.setPageParam(this.getPageParam(request));
		
		System.out.println(request+".........................1");
		try {
			filterChain.doFilter(request, response);
		} finally {
			PageContext.clearPageParam();
		}
		
		
		
	}
	
	/*******
	 * 获取页面/页码大小
	 * @param request
	 * @return
	 */
	private PageParam getPageParam(HttpServletRequest request) {
		String pn = request.getParameter(PageParam.PAGE_NO_NAME);
		String ps = request.getParameter(PageParam.PAGE_SIZE_NAME);
		System.out.println(pn+"========================"+ps);
		PageParam param = new PageParam();
		try {
			if (pn == null && ps == null) {
				param.setPn(param.getPn());
				param.setPs(param.getPs());
			} else {
				param.setPn(Integer.valueOf(pn));
				param.setPs(Integer.valueOf(ps));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return param;
	}
}
