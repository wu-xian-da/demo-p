/**
  *project demo-l
  *@author changchun.wu
  *2017年7月11日上午11:39:49
  */
package com.jianfei.d.common.vo;

/****
 * 微信根据标签进行群发接口返回VO
 * @author changchun.wu
 */
public class MassSendAllResultVO {

	private int errcode;
	
	private String errmsg;
	
	private Long msg_id;
	
	private Long msg_data_id;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Long getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(Long msg_id) {
		this.msg_id = msg_id;
	}

	public Long getMsg_data_id() {
		return msg_data_id;
	}

	public void setMsg_data_id(Long msg_data_id) {
		this.msg_data_id = msg_data_id;
	}
	
	
}
