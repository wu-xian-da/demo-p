/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日上午10:51:40
  */
package com.jianfei.d.controller.info;

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

import com.jianfei.d.controller.base.BaseController;
import com.jianfei.d.entity.info.NavInfo;
import com.jianfei.d.service.info.NavBaseService;
import com.jianfei.d.service.info.NavInfoService;

/***
 * 栏目文章
 * @author changchun.wu
 */
@Controller
@RequestMapping(value="/sys/info/navinfo")
public class NavInfoController extends BaseController {
	
	@Autowired
	private NavInfoService navInfoService;
	
	@Autowired
	private NavBaseService navBaseService;
	
	private void setModel(Model model){
		
	}
	
	@GetMapping("/create")
	public String createForm(Model model){
		return "";
	}

	@PostMapping("/create")
	public String create(@Valid NavInfo navInfo,BindingResult result,Model model,RedirectAttributes attrs){
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
	public String update(@Valid NavInfo navInfo,BindingResult result,Model model,RedirectAttributes attrs){
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
	
	@RequestMapping
	public String list(Model model,NavInfo navInfo){
		return "";
	}
}
