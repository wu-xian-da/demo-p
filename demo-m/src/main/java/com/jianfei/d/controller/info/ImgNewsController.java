/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日上午9:52:42
  */
package com.jianfei.d.controller.info;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jianfei.d.common.vo.MessageStatus;
import com.jianfei.d.controller.base.BaseController;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.ImgNews;
import com.jianfei.d.service.info.ImgNewsService;

/******
 * 图片新闻
 * @author changchun.wu
 */
@Controller
@RequestMapping(value="/sys/info/news")
public class ImgNewsController extends BaseController{
	
	@Autowired
	private ImgNewsService imgNewsService;
	
	private void setModel(Model model){
		
	}
	
	@GetMapping("/create")
	public String createForm(Model model){
		return "";
	}

	@PostMapping("/create")
	public String create(@Valid ImgNews imgNew,BindingResult result,Model model,RedirectAttributes attrs){
		if (result.hasErrors()) {
			setModel(model);
			return "";
		}
		return "";
	}
	
	@GetMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") Long id,Model model){
		return "";
	}
	
	@PostMapping("/update/{pid}")
	public String update(@Valid ImgNews imgNew,BindingResult result,Model model,RedirectAttributes attrs){
		if (result.hasErrors()) {
			setModel(model);
			return "";
		}
		return "";
	}
	
	@GetMapping("/delete/{pid}")
	public String delete(@PathVariable("pid") Long id,RedirectAttributes attrs){
		return "";
	}
	
	/****
	 * 审核通过
	 * @param imgNews
	 * @param attrs
	 * @return
	 */
	@GetMapping("/check/shtg")
	public String checkSHTG(ImgNews imgNews,RedirectAttributes attrs){
		imgNews.fileterImgNewss();
		for (ImgNews news : imgNews.getImgNews()) {
			news.setStatus(InfoStatus.SHTG);
			news.setCheckTime(new Date());
		}
		this.imgNewsService.updateImgNewsStatusBatch(imgNews.getImgNews());
		super.addMessage(attrs,MessageStatus.SUC ,"审核通过成功");
		return "";
	}
	
	/***
	 * 下刊
	 * @param imgNews
	 * @param attrs
	 * @return
	 */
	@GetMapping("/check/yxk")
	public String checkYXK(ImgNews imgNews,RedirectAttributes attrs){
		imgNews.fileterImgNewss();
		for (ImgNews news : imgNews.getImgNews()) {
			news.setStatus(InfoStatus.YXK);
			news.setCheckTime(new Date());
		}
		this.imgNewsService.updateImgNewsStatusBatch(imgNews.getImgNews());
		super.addMessage(attrs,MessageStatus.SUC ,"下刊成功");
		return "";
	}
	
	/****
	 * 上刊
	 * @param imgNews
	 * @param attrs
	 * @return
	 */
	@GetMapping("/check/ysk")
	public String checkYSK(ImgNews imgNews,RedirectAttributes attrs){
		imgNews.fileterImgNewss();
		for (ImgNews news : imgNews.getImgNews()) {
			news.setStatus(InfoStatus.YSK);
			news.setCheckTime(new Date());
		}
		this.imgNewsService.updateImgNewsStatusBatch(imgNews.getImgNews());
		super.addMessage(attrs,MessageStatus.SUC, "上刊成功");
		return "";
	}
	
	@RequestMapping
	public String list(Model model,ImgNews imgNew){
		return "";
	}
}
