/**
  *project demo-w
  *@author changchun.wu
  *2017年7月6日下午5:35:03
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
import com.jianfei.d.entity.info.Notice;
import com.jianfei.d.service.info.NoticeService;

/**
 * 紧急公告
 * @author changchun.wu
 */
@Controller
@RequestMapping("/web/notice")
public class NoticeController extends BaseController{
	
	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/toList")
	public ModelAndView toList(Model model){
		List<Notice> noticeList = noticeService.getListByStatus(InfoStatus.YSK);
		
		model.addAttribute("noticeList", noticeList);
		return new ModelAndView("notice/list.jsp");
	}
	
	@RequestMapping("/toDetail/{pid}")
	public ModelAndView toDetail(@PathVariable("pid") Long id, Model model){
		Notice notice = noticeService.get(id);
		
		model.addAttribute("notice", notice);
		return new ModelAndView("notice/detail.jsp");
	}
	
}
