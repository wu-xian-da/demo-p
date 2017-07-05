/**
  *project demo-l
  *@author changchun.wu
  *2017年6月26日上午9:27:35
  */
package com.jianfei.d.entity.system;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.entity.common.MenuType;

@Setter
@Getter
@EqualsAndHashCode(callSuper=true,of={Menu.ID2})
public class Menu extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6418890562777034774L;
	
	static final String ID2 = "id";
	
	//@NotBlank(message = "名称不能为空")
	//@Length(max = 50,message = "长度不能超过50")
	private String name;//资源名称
	
	//@Length(max = 200,message = "长度不能超过200")
	private String href;//资源路径
	
	//@NotNull(message = "类型不能为空")
	private MenuType type;
	
	//@Length(max=80,message="长度不能超过80")
	private String permission;//权限字符串
	
	//@NotNull(message="排序号不能为空")
	//@Digits(integer=4,fraction=0,message="超过4位数字最大限制")
	private Integer sort = 1;//排序号
	
	private String icon;//图标
	
	private Menu parent;//父编号
	
	private List<Menu> childs;
}
