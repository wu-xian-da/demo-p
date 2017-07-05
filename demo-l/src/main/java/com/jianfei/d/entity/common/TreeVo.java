/**
  *project demo-l
  *@author changchun.wu
  *2017年7月5日上午9:55:54
  */
package com.jianfei.d.entity.common;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeVo {
	
	private String label;
	
	private String value;
	
	private List<TreeVo> childs;
}
