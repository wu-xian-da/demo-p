package com.jianfei.d.controller.member;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jianfei.d.base.filter.page.PageContext;
import com.jianfei.d.common.config.EsConfig;
import com.jianfei.d.common.utils.ElasticsearchRestService;
import com.jianfei.d.common.vo.AuthMethod;
import com.jianfei.d.common.vo.es.hit.Hit;
import com.jianfei.d.common.vo.es.hit.ResponseHits;
import com.jianfei.d.controller.base.BaseController;
import com.jianfei.d.entity.common.Page;
import com.jianfei.d.entity.common.PageParam;


/**
 * 认证
* @author ZhangBo   
* @date 2017年6月6日 上午10:50:21
 */
@Controller
@RequestMapping("/sys/member/auth")
public class AuthController extends BaseController{
    
    @Autowired
    private EsConfig esConfig;
    
    @Autowired
    private ElasticsearchRestService elasticsearchRestService;
    
    @RequestMapping
    public String list(Model model, Hit hit) {
        PageParam param = PageContext.getPageParam();
        int from = (param.getPn() - 1) * param.getPs();
        int size = param.getPs();
        
        ResponseHits hits = this.elasticsearchRestService.get(
                this.esConfig.getUrl(from, size, "_all"), 
                this.buildRequestJson(hit), 
                ResponseHits.class);
        
        if(hits != null){
            Page<Hit> page = new Page<Hit>();
            page.setPageNo(param.getPn());
            page.setPageSize(param.getPs());
            page.setData(hits.getHits().getHits());
            page.setEntity(hit);
            page.setTotalRecord(hits.getHits().getTotal());
            model.addAttribute("page", page);
        }
        model.addAttribute("methods", AuthMethod.values());
        return "member/auth/list";
    }
    
    private String buildRequestJson(Hit hit){
        String json = "{"
                + "\"_source\": {"
                    + "\"excludes\": ["
                        + "\"type\", \"agent\", \"method\", \"status\", \"ua\", \"httpVersion\""
                    + "]"
                + "},"
                + "\"query\": "
                + "{\"bool\": {";
                
                if(hit.isDate()){
                    json += "\"must\": {"
                            + "\"range\": { "
                            + "\"@timestamp\": {";
                            if(StringUtils.isNotBlank(hit.getStart())){
                                json += "\"gte\": \"" + hit.getStartDate() + "\",";
                            }
                            if(StringUtils.isNotBlank(hit.getEnd())){
                                json += "\"lte\": \"" + hit.getEndDate() + "\",";
                            }
                    json += "\"format\": \"yyyy-MM-dd HH:mm:ss\""
                            //+ ",\"time_zone\": \"+08:00\""
                            + "}}},";
                }
                
                json += "\"must\": { "
                        + "\"bool\": {";
                int methodSize = hit.getMethods().size();
                if(methodSize == 0 || methodSize > 1){
                    json += "\"should\":[";
                            int index_ = 1;
                            
                            List<AuthMethod> methods = methodSize == 0 ? Arrays.asList(AuthMethod.values()) : hit.getMethods();
                            methodSize = methods.size();
                            for(AuthMethod method : methods){
                                json += "{\"match\": {\"url\": {\"query\": \"/log/auth/" + method.getKey() +"\", \"operator\": \"and\"}}}";
                                if(index_++ < methodSize){
                                    json += ",";
                                }
                            }
                    json += "]";
                }
                else{
                    AuthMethod method = hit.getMethods().get(0);
                    json += "\"must\": {"
                            + "\"match\": {\"url\": {\"query\": \"/log/auth/" + method.getKey() +"\", \"operator\": \"and\"}}}"
                       + "}";
                }
                json += "}}";
                
                if(StringUtils.isNotBlank(hit.getCode())){
                    json += ",\"must\": {"
                            + "\"regexp\": {\"url\": {\"value\": \".*" + hit.getCode() + ".*\"}}"
                            +"}";
                }
                
                json += "}},\"sort\": {\"@timestamp\":{\"order\": \"desc\"}}}";
                return json;
    }
    
}
