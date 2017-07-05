/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日下午3:27:51
  */
package com.jianfei.d.base.filter.xss;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/*********
 * 特殊字符过滤
 * @author changchun.wu
 */
/*@WebFilter(urlPatterns="/*",filterName="filter0")*/
public class XssFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		filterChain.doFilter(request, response);
	}

}
