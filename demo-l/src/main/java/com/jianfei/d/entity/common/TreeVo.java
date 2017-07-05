/**
  *project demo-l
  *@author changchun.wu
  *2017年7月5日上午9:55:54
  */
package com.jianfei.d.entity.common;

import java.util.List;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeVo {
	
	private String label;
	
	private Long value;
	
	private int level;
	
	private List<TreeVo> childs;
	
	public static final SimplePropertyPreFilter parentFilter = new SimplePropertyPreFilter(TreeVo.class,"value","label");
	
	public static final SimplePropertyPreFilter childFilter = new SimplePropertyPreFilter(TreeVo.class, "value", "label", "level");
}
