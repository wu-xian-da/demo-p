package com.jianfei.d.page.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.common.utils.JodaUtil;
import com.jianfei.d.common.utils.Reflections;

/**
 * Page
* @author ZhangBo   
* @date 2015年7月28日 下午5:45:09
 */
@Getter
@Setter
public class Page<T> {
    
    private int[] defaultPageSizeSelect = {10, 20, 30, 50};
	
	private int pageNo; //当前页码
	
	private int pageSize; //默认页展示记录数
	
	private long totalRecord;// 总记录数
	
    private int viewPageNumber = 8;// 显示页面长度
	
    private List<T> data = new ArrayList<T>();
    
    private T entity; //查询对象
    
    private String urlParams; //get查询参数封装
    
	public int getViewStartPage(){
        int viewStartPageNumber;
        if (getTotalPage() < viewPageNumber) {
            viewStartPageNumber = 1;
        } 
        else {
            int sub = this.pageNo - viewPageNumber / 2;
            int tmp = getTotalPage() - viewPageNumber + 1;
            if (sub > 0) {
                viewStartPageNumber = 1 + sub;
            } 
            else {
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
        } 
        else {
            int sub = this.pageNo - viewPageNumber / 2;
            if (sub > 0) {
                viewEndPageNumber = viewPageNumber + sub;
            } 
            else {
                viewEndPageNumber = viewPageNumber;
            }
            viewEndPageNumber = Math.min(getTotalPage(), viewEndPageNumber);
        }
        return viewEndPageNumber;
    }
    
    public int getBeginIndex(){
        return (this.pageNo - 1) * this.pageSize;
    }
	
	public boolean isFirst(){
	    return (this.totalRecord == 0 || this.pageNo == 1);
	}

	public boolean isLast(){
        return (this.totalRecord == 0 || this.getTotalPage() == pageNo);
    }

	public int getTotalPage(){
	    return (int) Math.ceil((double) totalRecord / (double) pageSize);
	}
	
	public void setEntity(T entity){
	    this.entity = entity;
	    this.buildSearchParams();
	}
	
	private void buildSearchParams(){
	    Field[] fields = this.entity.getClass().getDeclaredFields();
	    List<String> params = new ArrayList<>();
        for(Field field : fields){
            field.setAccessible(true);
            Object fieldValue = Reflections.getFieldValue(this.entity, field);
            if(fieldValue == null){
                continue;
            }
            
            //AbstractProcessor 
            if(field.isAnnotationPresent(FormQuery.class)){
                FormQuery query = field.getAnnotation(FormQuery.class);
                String []keys = query.value();
                if(keys == null || keys.length == 0){ //简单属性
                    this.converObjToString(params, field.getName(), fieldValue);
                }
                else{ //复合属性
                    for(String key : keys){
                        fieldValue = Reflections.invokeGetter(this.entity, key);
                        this.converObjToString(params, key, fieldValue);
                    }
                }
            }
        }
        
        if(params.size() > 0){
            this.urlParams = "&" + StringUtils.join(params, '&');
        }
	}
	
	@SuppressWarnings("unchecked")
    private void converObjToString(List<String> params, String key, Object fieldValue){
	    if(fieldValue != null){
	        String fieldValueStr = null;
	        if(fieldValue instanceof Date){
	            fieldValueStr = JodaUtil.format((Date)fieldValue);
	        }
	        else if(fieldValue.getClass().isArray()){
	            //
	        }
	        else if(fieldValue instanceof List){
	            List<Object> list = (List<Object>)fieldValue;
	            for(int i=0; i < list.size(); i++){
	                params.add(key + "["+i+"]=" + list.get(i).toString());
	            }
	        }
	        else{
	            fieldValueStr = fieldValue.toString();
	        }
	        
            if(StringUtils.isNotBlank(fieldValueStr)){
                params.add(key + "=" + fieldValueStr);
            }
        }
	}
	
}
