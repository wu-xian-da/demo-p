/**
  *project demo-m
  *@author changchun.wu
  *2017年7月4日下午3:49:46
  */
package com.jianfei.d.controller.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jianfei.d.controller.base.BaseController;

/***
 * 模块首页
 * @author changchun.wu
 */
@Controller("LogIndex")
public class IndexController extends BaseController {

	@GetMapping("/sys/log")
	public String index(){
		return super.getBaseIndex("/sys/log");
	}
}
