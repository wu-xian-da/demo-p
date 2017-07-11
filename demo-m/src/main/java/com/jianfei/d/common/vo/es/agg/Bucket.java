/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午5:01:32
  */
package com.jianfei.d.common.vo.es.agg;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bucket {

	@JSONField(name="doc_count")
    private int count;
    
    private String key;
    
    @JSONField(name="key_as_string")
    private String keyString;
    
    
    public String getKeyForDate(){
        if(key == null){
            return null;
        }
        int _indexChar = key.indexOf(".");
        if(_indexChar > 0){
            String date = key.substring(_indexChar + 1);
            return date.replaceAll(".", "月") + "日";
        }
        return null;
    }
}
