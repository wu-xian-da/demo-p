/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日上午9:39:22
  */
package com.jianfei.d.page.common;

import lombok.Getter;
import lombok.Setter;

/********
 * Page 传参
 * @author changchun.wu
 */
@Getter
@Setter
public class PageParam {
	
	public static final String PAGE_SIZE_NAME = "ps";//页面大小参数
	
	public static final String PAGE_NO_NAME = "pn";//页码参数
	
	private int ps = 10;//页面默认值
	
	private int pn = 1;//页码默认值
	
	private long totalRecord;//总记录数

}
