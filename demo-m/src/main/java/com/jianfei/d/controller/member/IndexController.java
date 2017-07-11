package com.jianfei.d.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jianfei.d.controller.base.BaseController;

/**
 * 会员首页
* @author ZhangBo   
* @date 2017年6月8日 下午3:13:38
 */
@Controller("MemberIndexController")
public class IndexController extends BaseController{
    
    @GetMapping("/sys/member")
    public String index(){
        return super.getBaseIndex("/sys/member");
    }
}
