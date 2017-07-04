/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日下午2:45:21
  */
package com.jianfei.d.controller.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jianfei.d.controller.base.BaseController;
import com.jianfei.d.entity.system.LogAccess;
import com.jianfei.d.entity.system.LogLogin;
import com.jianfei.d.service.system.LogAccessService;
import com.jianfei.d.service.system.LogLoginService;

/****
 * 登录日志&访问日志
 * @author changchun.wu
 */
@Controller
@RequestMapping("/sys/log")
public class LogController extends BaseController {
	
	@Autowired
	private LogLoginService logLoginService;
	
	@Autowired
	private LogAccessService logAccessService;
	
	@GetMapping
	public String index(){
		return super.getBaseIndex("/sys/log");
	}
	
	@RequestMapping("/login")
	public String login(Model model,LogLogin log){
		model.addAttribute("page",this.logLoginService.findPage(log));
		return "log/login";
	}
	
	@RequestMapping("/access")
	public String access(Model model,LogAccess log){
		model.addAttribute("page", this.logAccessService.findPage(log));
		return "log/access";
	}
}
