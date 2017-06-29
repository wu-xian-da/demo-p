/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日上午9:57:27
  */
package com.jianfei.d.entity.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.common.utils.JodaUtil;
import com.jianfei.d.common.utils.Reflections;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page<T> {
	private int pageNo;
	
	private int pageSize;
	
	private long totalRecord;
	
	private int viewPageNumber = 8;
	
	private List<T> data = new ArrayList<T>();
	
	private T entity;
	
	private String urlParams;
	
	public int getViewStartPage(){
		int viewStartPageNumber;
		if (getTotalPage()<viewPageNumber) {
			viewStartPageNumber = 1;
		}else {
			int sub = this.pageNo - viewPageNumber / 2;
			int tmp = getTotalPage() - viewPageNumber + 1;
			if (sub > 0) {
				viewStartPageNumber = 1 + sub;
			} else {
				viewStartPageNumber = 1;
			}
			viewStartPageNumber = Math.min(viewStartPageNumber, tmp);
		}
		
		return viewStartPageNumber;
	}

	public int getViewEndPage(){
		int viewEndPageNumber;
		if (getTotalPage() < viewPageNumber) {
			viewEndPageNumber = getTotalPage();
		} else {
			int sub = this.pageNo - viewPageNumber / 2;
			if (sub > 0) {
				viewEndPageNumber = viewPageNumber + sub;
			} else {
				viewEndPageNumber = viewPageNumber;
			}
			viewEndPageNumber = Math.min(getTotalPage(), viewEndPageNumber);
		}
		return viewEndPageNumber;
	}
	
	public int gerBeginIndex(){
		return (this.pageNo - 1) * this.pageSize;
	}
	
	public boolean isFirst(){
		return (this.totalRecord == 0 || this.pageNo == 1);
	}
	
	public boolean isLast(){
		return (this.totalRecord == 0 || this.getTotalPage() == pageNo);
	}
	
	public int getTotalPage() {
		return (int) Math.ceil((double)totalRecord/(double)pageSize);
	}
	
	public void setEntity(T entity){
		this.entity = entity;
		this.buildSearchParams();
	}

	private void buildSearchParams() {
		Field[] fields = this.entity.getClass().getDeclaredFields();
		List<String> params = new ArrayList<String>();
		for (Field field : fields) {
			field.setAccessible(true);
			Object fieldValue = Reflections.getFieldValue(this.entity, field);
			if (fieldValue == null) {
				continue;
			}
			
			FormQuery query = field.getAnnotation(FormQuery.class);
			if (query != null) {
				String[] keys = query.value();
				if (keys == null || keys.length == 0) {//简单属性
					this.converObjToString(params,field.getName(),fieldValue);
				} else {//复合属性 
					for (String key : keys) {
						fieldValue = Reflections.invokeGetter(this.entity, key);
						this.converObjToString(params, key, fieldValue);
					}
				}
			} 
		}
		if (params.size() > 0) {
			this.urlParams = "&"+ StringUtils.join(params,'&');
		}
	}

	private void converObjToString(List<String> params, String key,Object fieldValue) {
		if (fieldValue != null) {
			//String fieldValueStr = fieldValue.toString();
			String fieldValueStr = null;
			if (fieldValue.getClass() == Date.class) {
				fieldValueStr = JodaUtil.format((Date)fieldValue);
			} else {
				fieldValueStr = fieldValue.toString();
			}
			if (StringUtils.isNotBlank(fieldValueStr)) {
				params.add(key+" = "+fieldValueStr);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
