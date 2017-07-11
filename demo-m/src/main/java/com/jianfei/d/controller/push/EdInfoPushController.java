/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午3:58:40
  */
package com.jianfei.d.controller.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoPushType;
import com.jianfei.d.entity.push.InfoPush;
import com.jianfei.d.service.push.InfoPushService;

/******
 * 信息推送(已推送)
 * @author ATH
 *
 */
@Controller
@RequestMapping(value = "/sys/push/ed")
public class EdInfoPushController {

	@Autowired
	private InfoPushService infoPushService;
	
	private void setBases(Model model) {
		model.addAttribute("pushTypes", InfoPushType.values());
	}
	
	@GetMapping("/detail/{pid}")
	public String detail(@PathVariable("pid") Long id, Model model) {
		InfoPush infoPush = infoPushService.get(id);
		
		setBases(model);
		model.addAttribute("infoPush", infoPush);
		return "push/ed/detail";
	}
	
	@RequestMapping
	public String list(Model model, InfoPush infoPush) {
		infoPush.setPushStatus(InfoPushStatus.YTS);
		
		setBases(model);
		model.addAttribute("page", infoPushService.findPage(infoPush));
		return "push/ed/list";
	}
	
}
