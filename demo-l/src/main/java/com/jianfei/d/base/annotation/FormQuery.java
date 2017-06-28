/**
  *project demo-l
  *@author changchun.wu
  *2017年6月22日下午5:58:42
  */
package com.jianfei.d.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*******
 * 标记为Form查询字段类型
 * @author changchun.wu
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormQuery {
	String[] value() default{};
}
