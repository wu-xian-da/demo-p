/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午5:04:50
  */
package com.jianfei.d.common.vo.es.agg;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.annotation.JSONField;

@Getter
@Setter
public class ResponseAggs {

	@JSONField(name="aggregations",serialize=false)
	private Aggregations aggs;
	
	@JSONField(name="aggs")
    public Aggregations getAggs(){
        return this.aggs;
    }
}
