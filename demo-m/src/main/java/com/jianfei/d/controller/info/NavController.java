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

import com.jianfei.d.common.config.FileConfig;
import com.jianfei.d.controller.base.BaseController;
import com.jianfei.d.entity.common.NavStatus;
import com.jianfei.d.entity.common.NavType;
import com.jianfei.d.entity.common.TemplateType;
import com.jianfei.d.entity.info.NavBase;
import com.jianfei.d.entity.info.NavInfo;
import com.jianfei.d.entity.info.Template;
import com.jianfei.d.service.info.NavBaseService;
import com.jianfei.d.service.info.NavContentService;
import com.jianfei.d.service.info.NavInfoService;
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
	
	@Autowired
	private NavInfoService navInfoService;
	
	@Autowired
	private FileConfig fileConfig;
	
	private void setBases(Model model){
		//栏目类型
		model.addAttribute("navType", NavType.values());
		//图片上传回显前缀
		model.addAttribute("imgViewBase",fileConfig.getMapping());
	}
	
	/**
	 * 父栏目
	 * @param model
	 */
	private void setParentNav(Model model){
		List<NavBase> parentNavList = navBaseService.getParentList();
		model.addAttribute("parentNavList",parentNavList);
	}
	
	/***
	 * 栏目模板
	 * @param model
	 */
	private void setTemplates(Model model){
		List<Template> listTemplates = templateService.getListByType(TemplateType.LB);
		List<Template> contentTemplates = templateService.getListByType(TemplateType.NR);
		model.addAttribute("listTemplates", listTemplates);
		model.addAttribute("contentTemplates", contentTemplates);
	}

	@GetMapping("/create")
	public String createForm(Model model){
		setBases(model);
		setParentNav(model);
		setTemplates(model);
		return "info/nav/form";
	}
	
	@PostMapping("/create")
	public String create(NavBase navBase,BindingResult result,Model model,RedirectAttributes attrs){
		
		int r = navBaseService.addNav(navBase);
		if (r > 0) {
			super.addMessage(attrs, "保存栏目成功");
		} else {
			super.addMessage(attrs, "保存栏目失败,请重试!");
		}
		return "redirect:/sys/info/nav";
	}
	
	@GetMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") Long id,Model model){
		setBases(model);
		setParentNav(model);
		setTemplates(model);
		NavBase navBase = navBaseService.get(id);
		if (null != navBase.getNavType() && NavType.XXEJCD.equals(navBase.getNavType())) {
			navBase.setNavSecondMenu(navSecondMenuService.getByNavId(id));
		} else if (null != navBase.getNavType() && NavType.WEJCD.equals(navBase.getNavType())) {
			navBase.setNavContent(navContentService.getByNavId(id));
		} else if ((null != navBase.getNavType() && NavType.URLWL.equals(navBase.getNavType()))){
			navBase.setNavUrl(navUrlService.getByNavId(id));
		}
		model.addAttribute("navBase", navBase);
		return "info/nav/form";
	}
	
	@PostMapping("/update/{pid}")
	public String update(NavBase navBase,BindingResult result,Model model,RedirectAttributes attrs){
		
		int r = navBaseService.updateNav(navBase);
		if (r > 0) {
			super.addMessage(attrs, "修改栏目成功");
		} else {
			super.addMessage(attrs, "修改栏目失败,请重试!");
		}
		return "redirect:/sys/info/nav";
	}
	
	@GetMapping("/delete/{pid}")
	public String delete(@PathVariable("pid") Long id,RedirectAttributes attrs){
		//判断当前栏目下有没有子栏目：必须是叶子节点，才给删除
		List<NavBase> navList = navBaseService.getListByParentId(id);
		if (null != navList && navList.size() > 0) {
			super.addMessage(attrs, "删除栏目失败,该栏目下面目前存在子栏目");
			return "redirect:/sys/info/nav";
		}
		//判断当前栏目下有没有信息:有信息,不给删除
		List<NavInfo> infoList = navInfoService.getListByNavId(id);
		if (null != infoList && infoList.size() > 0) {
			super.addMessage(attrs, "删除栏目失败,该栏目下面存在相应的栏目信息");
			return "redirect:/sys/info/nav";
		}
		int result = navBaseService.delete(id);
		if (result > 0) {
			super.addMessage(attrs, "删除栏目成功");
		} else {
			super.addMessage(attrs, "删除栏目失败,请重试!");
		}
		return "redirect:/sys/info/nav";
	}
	
	/**
	 * 展示
	 * @param navBase
	 * @param attrs
	 * @return
	 */
	/*@GetMapping("/show")
	public String show(NavBase navBase,RedirectAttributes attrs){
		navBase.filterNavBases();
		for (NavBase nav : navBase.getNavBases()) {
			nav.setNavStatus(NavStatus.ZS);
		}
		int result = navBaseService.updateNavBaseStatusBatch(navBase.getNavBases());
		if (result > 0) {
			super.addMessage(attrs, "批量展示栏目成功");
		} else {
			super.addMessage(attrs, "批量展示栏目失败,请重试!");
		}
		return "redirect:/sys/info/nav";
	}*/
	
	/***
	 * 恢复展示
	 * @param navBase
	 * @param attrs
	 * @return
	 */
	@GetMapping("/hfshow")
	public String hfshow(NavBase navBase,RedirectAttributes attrs){
		navBase.filterNavBases();
		for (NavBase nav : navBase.getNavBases()) {
			nav.setNavStatus(NavStatus.ZS);
		}
		int result = navBaseService.updateNavBaseStatusBatch(navBase.getNavBases());
		if (result > 0) {
			super.addMessage(attrs, "批量恢复展示栏目成功");
		} else {
			super.addMessage(attrs, "批量恢复展示栏目失败,请重试!");
		}
		return "redirect:/sys/info/nav";
	}
	
	/**
	 * 取消展示
	 * @param navBase
	 * @param attrs
	 * @return
	 */
	@GetMapping("/hide")
	public String hide(NavBase navBase,RedirectAttributes attrs){
		navBase.filterNavBases();
		for (NavBase nav : navBase.getNavBases()) {
			nav.setNavStatus(NavStatus.QXZS);
		}
		int result = navBaseService.updateNavBaseStatusBatch(navBase.getNavBases());
		if (result > 0) {
			super.addMessage(attrs, "批量取消展示栏目成功");
		} else {
			super.addMessage(attrs, "批量取消展示栏目失败,请重试!");
		}
		return "redirect:/sys/info/nav";
	}
	
	@RequestMapping
	public String list(Model model,NavBase navBase){
		setBases(model);
		setParentNav(model);
		model.addAttribute("page",navBaseService.findPage(navBase));
		return "info/nav/list";
	}
}
