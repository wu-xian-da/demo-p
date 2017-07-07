/**
  *project demo-w
  *@author changchun.wu
  *2017年6月28日下午5:12:22
  */
package com.jianfei.d;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jianfei.d.base.BaseController;

@Controller
public class IndexController extends BaseController{

	private static final String ENCODE = "UTF-8";
	
	@RequestMapping("/")   
	public ModelAndView index(HttpServletRequest request){
		
		//获取设备推送参数
        String queryString = request.getQueryString();
        if(queryString != null){
            try {
                request.setAttribute("queryString", URLEncoder.encode(queryString, ENCODE));
            } catch (Exception e) {
            }
        }
        
        return new ModelAndView("index.jsp");
        
	}
}
