/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日下午4:08:12
  */
package com.jianfei.d.common.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

/**
 * 日期工具类
 */
public class JodaUtil extends DateUtils {
	
	private final static String YYYY_MM_DD = "yyyy-MM-dd";
	
	private final static String YYYY_MM_DDHH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	
	public static Date parseDate(String dateStr){
		DateTime dt = DateTime.parse(dateStr);
		return dt.toDate();
	}
	
	//获得指定日期24点时间
    public static Date getNight(Date date) {  
        DateTime dt = new DateTime(date).millisOfDay().withMaximumValue();
        return dt.toDate();
    }
    
    //获得指定日期0点时间
    public static Date getMorning(Date date) {  
        DateTime newDt = new DateTime(date).millisOfDay().withMinimumValue();
        return newDt.toDate();
        
    }
	
	public static String format(Date date,String pattern){
		DateTime dt = new DateTime(date);
		return dt.toString(pattern);
	}
	
	public static String format(Date date){
		DateTime dt = new DateTime(date);
		return dt.toString(YYYY_MM_DD);
	}
	
	public static String formatHHmmss(Date date){
		DateTime dt = new DateTime(date);
		return dt.toString(YYYY_MM_DDHH_mm_ss);
	}
	
	public static String getYear(){
		DateTime dt = new DateTime(new Date());
		return dt.toString("yyyy");
	}
	
	public static String getMonth(){
		DateTime dt = new DateTime(new Date());
		return dt.toString("MM");
	}
	
	public static String getDay(){
		DateTime dt = new DateTime(new Date());
		return dt.toString("dd");
	}
	
	public static void main(String[] args) {
		System.out.println(formatHHmmss(getMorning(new Date())));
	}
}

