/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日上午9:52:42
  */
package com.jianfei.d.controller.info;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jianfei.d.common.config.FileConfig;
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
	
	@Autowired
	private FileConfig fileConfig;
	
	private void setModel(Model model){
		model.addAttribute("infoStatuss",InfoStatus.values());
	}
	
	@GetMapping("/create")
	public String createForm(Model model){
		return "info/news/form";
	}

	@PostMapping("/create")
	public String create(ImgNews imgNew,BindingResult result,Model model,RedirectAttributes attrs){

		//初始化状态
		imgNew.setStatus(InfoStatus.DSH);
		int r = imgNewsService.save(imgNew);
		if (r > 0) {
			super.addMessage(attrs,"保存图片新闻成功");
		} else {
			super.addMessage(attrs, "保存图片新闻失败,请重试");
		}
		return "redirect:/sys/info/news";
	}
	
	@GetMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") Long id,Model model){
		model.addAttribute("news", imgNewsService.get(id));
		return "info/news/form";
	}
	
	@PostMapping("/update/{pid}")
	public String update(ImgNews imgNew,BindingResult result,Model model,RedirectAttributes attrs){
		imgNew.setStatus(InfoStatus.DSH);
		int r = imgNewsService.update(imgNew);
		if (r > 0) {
			super.addMessage(attrs, "修改图片新闻成功");
		} else {
			super.addMessage(attrs, "修改图片新闻失败,请重试!");
		}
		return "redirect:/sys/info/news";
	}
	
	@GetMapping("/delete/{pid}")
	public String delete(@PathVariable("pid") Long id,RedirectAttributes attrs){
		int r = imgNewsService.delete(id);
		if(r > 0){
			super.addMessage(attrs,"删除图片新闻成功");
		}else{
			super.addMessage(attrs,"删除图片新闻失败，请重试！");
		}
		return "redirect:/sys/info/news";
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
			news.setStatus(InfoStatus.YSK);
			news.setCheckTime(new Date());
		}
		int result = imgNewsService.updateImgNewsStatusBatch(imgNews.getImgNews());
		if (result > 0) {
			super.addMessage(attrs, "批量审核通过成功");
		} else {
			super.addMessage(attrs, "批量审核通过失败,请重试!");
		}
		return "redirect:/sys/info/news";
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
		int result = imgNewsService.updateImgNewsStatusBatch(imgNews.getImgNews());
		if (result > 0) {
			super.addMessage(attrs, "批量下刊成功");
		} else {
			super.addMessage(attrs, "批量下刊失败,请重试!");
		}
		return "redirect:/sys/info/news";
	}
	
	/****
	 * 上刊
	 * @param imgNews
	 * @param attrs
	 * @return
	 */
	/*@GetMapping("/check/ysk")
	public String checkYSK(ImgNews imgNews,RedirectAttributes attrs){
		imgNews.fileterImgNewss();
		for (ImgNews news : imgNews.getImgNews()) {
			news.setStatus(InfoStatus.YSK);
			news.setCheckTime(new Date());
		}
		int result = imgNewsService.updateImgNewsStatusBatch(imgNews.getImgNews());
		if (result > 0) {
			super.addMessage(attrs, "批量上刊成功");
		} else {
			super.addMessage(attrs, "批量上刊失败,请重试!");
		}
		return "redirect:/sys/info/news";
	}*/
	
	//恢复上刊
	@GetMapping("/check/hfsk")
	public String checkHFSK(ImgNews imgNews, RedirectAttributes attrs){
		imgNews.fileterImgNewss();
	
		for (ImgNews news : imgNews.getImgNews()) {
			news.setStatus(InfoStatus.YSK);
		}
	
		int result = imgNewsService.updateImgNewsStatusBatch(imgNews.getImgNews());
		if(result > 0){
			super.addMessage(attrs,"批量恢复上刊成功");
		}else{
			super.addMessage(attrs, "批量恢复上刊失败，请重试！");
		}
	
		return "redirect:/sys/info/news";
	}
	
	@GetMapping("/detail/{pid}")
	public String detail(@PathVariable("pid") Long id,Model model){
		model.addAttribute("news",imgNewsService.get(id));
		return "info/news/detail";
	}
	
	@RequestMapping
	public String list(Model model,ImgNews imgNew){
		setModel(model);
		model.addAttribute("page", imgNewsService.findPage(imgNew));
		model.addAttribute("webBaseUrl",fileConfig.getWebBaseUrl().startsWith("http://")?fileConfig.getWebBaseUrl():"http://"+fileConfig.getWebBaseUrl());
		return "info/news/list";
	}
}
