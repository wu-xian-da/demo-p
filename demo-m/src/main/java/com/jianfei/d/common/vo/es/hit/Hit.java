package com.jianfei.d.common.vo.es.hit;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.common.utils.JodaUtil;
import com.jianfei.d.common.vo.AuthMethod;

@Getter
@Setter
public class Hit {
    
    @JSONField(name="_source")
    private Access access;
    
    /** search **/
    @JSONField(serialize=false,deserialize=false)
    @FormQuery
    private String start;
    
    @JSONField(serialize=false,deserialize=false)
    @FormQuery
    private String end;
    
    @JSONField(serialize=false,deserialize=false)
    private List<AuthMethod> methods = new ArrayList<>();
    
    @JSONField(serialize=false,deserialize=false)
    @FormQuery
    private String code;
    
    public boolean isDate(){
        return StringUtils.isNotBlank(this.start) || StringUtils.isNotBlank(this.end);
    }
    
    public String getStartDate(){
        if(this.start == null){
            return null;
        }
        return this.start + JodaUtil.HHMMSS_START;
    }
    
    public String getEndDate(){
        if(this.end == null){
            return null;
        }
        return this.end + JodaUtil.HHMMSS_END;
    }
    /** search **/
}
