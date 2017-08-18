/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午3:56:08
  */
package com.jianfei.d.controller.push;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jianfei.d.controller.base.BaseController;

/***
 * 信息推送模块
 * @author changchun.wu
 */
@Controller("PushIndexController")
public class IndexController extends BaseController{
	
	@GetMapping("/sys/push")
	public String index(){
		
		System.out.println("====++++");
		return super.getBaseIndex("/sys/push");
	}
}
