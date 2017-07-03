/**
  *project demo-m
  *@author changchun.wu
  *2017年7月3日下午4:01:00
  */
package com.jianfei.d.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/***
 * 系统消息
 * @author changchun.wu
 */
@Getter
@Setter
@AllArgsConstructor
public class Message {
	private String info;
	
	private MessageStatus status =MessageStatus.SUC;
}
