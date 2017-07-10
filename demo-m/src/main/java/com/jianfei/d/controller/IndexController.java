/**
  *project demo-m
  *@author changchun.wu
  *2017年6月28日上午10:56:51
  */
package com.jianfei.d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jianfei.d.controller.base.BaseController;
import com.jianfei.d.service.system.UserService;

@Controller
public class IndexController extends BaseController{

	@Autowired
    private UserService userSerivce;
    
    /**
     * 入口
     * @return
     */
    @RequestMapping(value = "/")
    public String startPage() {
        return "redirect:/sys/index";
    }

    /**
     * 登陆页
     * @return
     */
    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    /**
     * 登陆失败
     * @return
     */
    @PostMapping("/login")
    public String login(Model model) {
        return "login";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/sys/index")
    public String index() {
        return "index";
    }
}
