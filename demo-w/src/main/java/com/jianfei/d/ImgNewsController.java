/**
  *project demo-w
  *@author changchun.wu
  *2017年7月6日下午5:27:40
  */
package com.jianfei.d;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jianfei.d.base.BaseController;
import com.jianfei.d.entity.info.ImgNews;
import com.jianfei.d.service.info.ImgNewsService;

/***
 * 图片新闻
 * @author changchun.wu
 */
@Controller
@RequestMapping("/web/news")
public class ImgNewsController extends BaseController{

	@Autowired
	private ImgNewsService imgNewsService;
	
	@RequestMapping("/toDetail/{pid}")
	public ModelAndView toDetail(@PathVariable("pid") Long id, Model model){
		ImgNews imgNews = imgNewsService.get(id);
		
		model.addAttribute("imgNews", imgNews);
		return new ModelAndView("news/detail.jsp");
	}
}
