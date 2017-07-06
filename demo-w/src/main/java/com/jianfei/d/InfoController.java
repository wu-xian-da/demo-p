/**
  *project demo-w
  *@author changchun.wu
  *2017年7月6日下午5:32:25
  */
package com.jianfei.d;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jianfei.d.base.BaseController;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.NavContent;
import com.jianfei.d.entity.info.NavInfo;
import com.jianfei.d.entity.info.NavSecondMenu;
import com.jianfei.d.service.info.NavContentService;
import com.jianfei.d.service.info.NavInfoService;
import com.jianfei.d.service.info.NavSecondMenuService;

@Controller
@RequestMapping("/info")
public class InfoController extends BaseController{
	
	@Autowired
	private NavInfoService navInfoService;
	
	@Autowired
	private NavSecondMenuService navSecondMenuService;
	
	@Autowired
	private NavContentService navContentService;
	
	@RequestMapping("/toList/{pid}")
	public ModelAndView toList(@PathVariable("pid") Long id, Model model){
		//获取渲染列表模板
		NavSecondMenu navSec = navSecondMenuService.getByNavId(id);
		List<NavInfo> infoList = navInfoService.getListByNavIdAndStatus(id, InfoStatus.YSK);
		
		model.addAttribute("navSec", navSec);
		model.addAttribute("infoList", infoList);
		//return new ModelAndView(navSec.getMenuListTemplate().getFilePath());
		return new ModelAndView("info/list.jsp");
	}
	
	@RequestMapping("/toDetail/{pid}")
	public ModelAndView toDetail(@PathVariable("pid") Long id, Model model){
		//获取渲染内容模板
		NavContent navContent = navContentService.getByNavId(id);
		NavInfo infoDetail = navInfoService.get(id);
		
		model.addAttribute("navContent", navContent);
		model.addAttribute("infoDetail", infoDetail);
		//return new ModelAndView(navContent.getContentTemplate().getFilePath());
		return new ModelAndView("info/detail.jsp");
	}
}
