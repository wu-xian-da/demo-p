package com.jianfei.d.common.vo.es.hit;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.annotation.JSONField;
import com.jianfei.d.common.vo.AuthMethod;

@Getter
@Setter
public class Access {

    private String url;
    
    @JSONField(name="@timestamp")
    private Date timestamp;
    
    private String requestId;
    
    private String clientIp;
    
    private String xForwardedFor;
    
    public String getCode(){
        if(this.url == null){
            return null;
        }
        int index = this.url.lastIndexOf('/');
        if(index > 0){
            return this.url.substring(index + 1);
        }
        return null;
    }
    
    public AuthMethod getAuth(){
        return AuthMethod.getAuthMethodByUrl(this.url);
    }
}
