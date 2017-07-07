/**
  *project demo-w
  *@author changchun.wu
  *2017年7月6日下午5:23:24
  */
package com.jianfei.d;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jianfei.d.base.BaseController;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.ImgNews;
import com.jianfei.d.entity.info.NavBase;
import com.jianfei.d.entity.info.Notice;
import com.jianfei.d.service.info.ImgNewsService;
import com.jianfei.d.service.info.NavBaseService;
import com.jianfei.d.service.info.NoticeService;

/**
 * HOME 页
 * @author changchun.wu
 */
@Controller
@RequestMapping("/web")
public class HomeController extends BaseController{

	@Autowired
	private ImgNewsService imgNewsService;
	
	@Autowired
	private NavBaseService navBaseService;
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/home")
	public ModelAndView home(Model model){
		//图片新闻
		List<ImgNews> imgNewsList = imgNewsService.getListByStatus(InfoStatus.YSK);
		//导航链接
		List<NavBase> navList = navBaseService.getShowFirstNavList();
		//紧急公告
		List<Notice> noticeList = noticeService.getTopNByStatus(InfoStatus.YSK);
		
		model.addAttribute("imgNewsList", imgNewsList);
		model.addAttribute("navList", navList);
		model.addAttribute("noticeList", noticeList);
        return new ModelAndView("home.jsp");
	}
}
