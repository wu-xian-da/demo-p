/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午3:59:44
  */
package com.jianfei.d.controller.push;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoPushType;
import com.jianfei.d.entity.push.InfoPush;
import com.jianfei.d.service.push.InfoPushService;

/******
 * 信息推送(待处理)
 * @author ATH
 *
 */
@Controller
@RequestMapping(value = "/sys/push/ing")
public class IngInfoPushController extends BaseController{
	
	@Autowired
	private InfoPushService infoPushService;
	
	private void setBases(Model model) {
		model.addAttribute("pushTypes", InfoPushType.values());
	}
	
	@GetMapping("/create")
	public String createForm(Model model) {
		this.setBases(model);
		return "push/ing/form";
	}
	
	@PostMapping("/create")
	public String create(InfoPush infoPush, BindingResult result,
			Model model, RedirectAttributes attrs) {
		int r = infoPushService.save(infoPush);
		if(r > 0){
			super.addMessage(attrs, "保存推送信息成功");
		}else{
			super.addMessage(attrs, "保存推送信息失败，请重试！");
		}
		return "redirect:/sys/push/ing";
	}

	@GetMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") Long id, Model model) {
		InfoPush infoPush = infoPushService.get(id);
		
		this.setBases(model);
		model.addAttribute("infoPush", infoPush);
		return "push/ing/form";
	}

	@PostMapping("/update/{pid}")
	public String update(InfoPush infoPush, BindingResult result,
			Model model, RedirectAttributes attrs) {
		int r = infoPushService.update(infoPush);
		if(r > 0){
			super.addMessage(attrs, "修改推送信息成功");
		}else{
			super.addMessage(attrs, "修改推送信息失败，请重试！");
		}
		
		return "redirect:/sys/push/ing";
	}

	@GetMapping("/delete/{pid}")
	public String delete(@PathVariable("pid") Long id, RedirectAttributes attrs) {
		
		int result = infoPushService.delete(id);
		if(result > 0){
			super.addMessage(attrs, "删除推送信息成功");
		}else{
			super.addMessage(attrs, "删除推送信息失败，请重试！");
		}
		return "redirect:/sys/push/ing";
	}
	
	/******
	 * 信息推送
	 * @param pushId 推送信息ID
	 * @param pushRange 推送范围
	 * @return
	 */
	@GetMapping("/push")
	public String push(Long pushId, String pushRange, HttpServletRequest request, RedirectAttributes attrs) {
		StringBuffer baseURLStr = new StringBuffer("");
			baseURLStr.append(request.getScheme());
			baseURLStr.append("://");
			baseURLStr.append(request.getServerName());
			baseURLStr.append(":");
			baseURLStr.append(request.getServerPort());
		String contextPath = request.getContextPath();
		if(StringUtils.isNotBlank(contextPath)){
			baseURLStr.append("/");
			baseURLStr.append(contextPath);
		}
		int result = infoPushService.push(pushId, pushRange, baseURLStr.toString());
		
		if(10000 == result){
			super.addMessage(attrs, "推送信息成功");
		}else{
			if(10001 == result){
				super.addMessage(attrs, "推送信息失败(获取微信授权失败)，请重试！");
			}else if(10002 == result){
				super.addMessage(attrs, "推送信息失败(上传封面图片失败)，请重试！");
			}else if(10003 == result){
				super.addMessage(attrs, "推送信息失败(上传富文本中图片失败)，请重试！");
			}else if(10004 == result){
				super.addMessage(attrs, "推送信息失败(上传图文消息素材失败)，请重试！");
			}else if(10005 == result){
				super.addMessage(attrs, "推送信息失败(群发消息失败)，请重试！");
			}else if (10006 == result){
				super.addMessage(attrs, "推送信息失败(群发消息推送成功数已达到微信官方月度限制)，请下月再试！");
			}else if (10007 == result){
				super.addMessage(attrs, "推送信息失败(上传封面图片失败，图片大小超过限制，64K以内)，请修改后重试！");
			}else{
				super.addMessage(attrs, "推送信息失败，请重试！");
			}
		}
		return "redirect:/sys/push/ing";
	}
	
	@RequestMapping
	public String list(Model model, InfoPush infoPush) {
		infoPush.setPushStatus(InfoPushStatus.DCL);
		
		this.setBases(model);
		model.addAttribute("page", infoPushService.findPage(infoPush));
		return "push/ing/list";
	}
}

