package com.jianfei.d.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jianfei.d.common.config.EsConfig;
import com.jianfei.d.common.config.EsRequestJson;
import com.jianfei.d.common.utils.ElasticsearchRestService;
import com.jianfei.d.common.vo.es.agg.ResponseAggs;
import com.jianfei.d.controller.base.BaseController;

/**
 * 访问记录
* @author ZhangBo   
* @date 2017年6月6日 上午10:50:40
 */
@Controller
@RequestMapping("/sys/member/access")
public class AccessController extends BaseController{
    
    @Autowired
    private EsConfig esConfig;
    
    @Autowired
    private ElasticsearchRestService elasticsearchRestService;
    
    @RequestMapping
    public String list(Model model) {
        //day
        ResponseAggs days = this.elasticsearchRestService.get(
                this.esConfig.getUrl(0, 0, "_all"), 
                EsRequestJson.DAY_RANGE, 
                ResponseAggs.class);
        
        //hour range
        ResponseAggs hours = this.elasticsearchRestService.get(
                this.esConfig.getUrl(), 
                EsRequestJson.DAY_HOUR, 
                ResponseAggs.class);
        
        model.addAttribute("days", days);
        model.addAttribute("hours", hours);
        return "member/access/list";
    }
    
    @ResponseBody
    @GetMapping("/devices")
    public ResponseAggs getDevices(String date){
        ResponseAggs devices = this.elasticsearchRestService.get(
                this.esConfig.getUrl(date), 
                EsRequestJson.DEVICE_TYPE, 
                ResponseAggs.class);
        return devices;
    }
    
    @ResponseBody
    @GetMapping("/auths")
    public ResponseAggs getAuths(String date){
        ResponseAggs auths = this.elasticsearchRestService.get(
                this.esConfig.getUrl(date), 
                EsRequestJson.AUTH_METHODS, 
                ResponseAggs.class);
        return auths;
    }
    
    
}
