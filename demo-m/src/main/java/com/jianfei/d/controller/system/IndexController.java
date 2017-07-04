/**
  *project demo-m
  *@author changchun.wu
  *2017年7月4日下午3:53:38
  */
package com.jianfei.d.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jianfei.d.controller.base.BaseController;

/***
 * 模块首页
 * @author changchun.wu
 */
@Controller("SystemIndex")
public class IndexController extends BaseController {
	
	@GetMapping("/sys/system")
	public String index(){
		return super.getBaseIndex("/sys/system");
	}
}
