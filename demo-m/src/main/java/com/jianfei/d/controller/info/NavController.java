/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日上午8:43:37
  */
package com.jianfei.d.controller.info;

import java.util.List;

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
import com.jianfei.d.entity.common.NavType;
import com.jianfei.d.entity.common.TemplateType;
import com.jianfei.d.entity.info.NavBase;
import com.jianfei.d.entity.info.Template;
import com.jianfei.d.service.info.NavBaseService;
import com.jianfei.d.service.info.NavContentService;
import com.jianfei.d.service.info.NavSecondMenuService;
import com.jianfei.d.service.info.NavUrlService;
import com.jianfei.d.service.info.TemplateService;

/*****
 * 栏目管理
 * @author changchun.wu
 */
@Controller
@RequestMapping(value="/sys/info/nav")
public class NavController extends BaseController{
	
	@Autowired
	private NavBaseService navBaseService;
	
	@Autowired
	private NavSecondMenuService navSecondMenuService;
	
	@Autowired
	private NavContentService navContentService;
	
	@Autowired
	private NavUrlService navUrlService;
	
	@Autowired
	private TemplateService templateService;
	
	private void setModel(Model model){
		List<Template> listTemplates = templateService.getListByType(TemplateType.LB.getValue());
		List<Template> contentTemplates = templateService.getListByType(TemplateType.NR.getValue());
		model.addAttribute("listTemplates", listTemplates);
		model.addAttribute("contentTemplates", contentTemplates);
		model.addAttribute("navTypes", NavType.values());
	}

	@GetMapping("/create")
	public String createForm(Model model){
		return "";
	}
	
	@PostMapping("/create")
	public String create(@Valid NavBase navBase,BindingResult result,Model model,RedirectAttributes attrs){
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
	public String update(@Valid NavBase navBase,BindingResult result,Model model,RedirectAttributes attrs){
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
	public String list(Model model,NavBase navBase){
		return "";
	}
}
