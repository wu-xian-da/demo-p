/**
  *project demo-l
  *@author changchun.wu
  *2017年6月22日下午5:43:35
  */
package com.jianfei.d.base.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public abstract class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6601365296223852590L;
	
	/**
	 * 主键
	 */
	protected Long id;
}
