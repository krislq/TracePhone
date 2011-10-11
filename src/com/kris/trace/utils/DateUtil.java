package com.kris.trace.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.text.TextUtils;
/**
 * date util,could use the class to get time with formated
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since 1.0.0 06:32:00
 * @version 1.0.0
 */
public class DateUtil {

	public static final long 		DAY_MILL 						= 60 * 60 * 24;
	
	public static final String 		DATE_DEFAULT_FORMATE 			= "yyyyMMdd";
	public static final String 		DATE_FORMATE_ALL 				= "yyyy-MM-dd HH:mm:ss";
	public static final String 		DATE_FORMATE_TRANSACTION		= "dd/mm/yyyy, hh:mm";
	public static final String 		DATE_FORMATE_DAY_HOUR_MINUTE 	= "MM/dd HH:mm";
	public static final String 		DATE_FORMATE_HOUR_MINUTE 		= "HH:mm";
	
	public static SimpleDateFormat	 dateFormate =new SimpleDateFormat();
	/**
	 * 
	 * @param milliseconds 
	 * @return the time formated by "yyyy-MM-dd HH:mm:ss"
	 */
	public static String toTime(long milliseconds){
		return toTime(new Date(milliseconds),null);
	}
	/**
	 * 
	 * @param milliseconds
	 * @param pattern
	 * @return the time formated
	 */
	public static String toTime(long milliseconds,String pattern){
		return toTime(new Date(milliseconds),pattern);
	}
	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toTime(Date date,String pattern){
		if(TextUtils.isEmpty(pattern))
		{
			pattern = DATE_DEFAULT_FORMATE;
		}
		dateFormate.applyPattern(pattern);
		if(date ==null)
		{
			date = new Date();
		}
		return dateFormate.format(date);
	}
	/**
	 * 
	 * @param context 
	 * @param createTime the time to be formated
	 * @return like X seconds/minutes/hours/weeks ago ,
	 */
	public static String getTimeAgo(Context context,long createTime) {
		long curTime = System.currentTimeMillis();
		long between = (curTime - createTime) / 1000;
		
		String str = "";
		
		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		if (day >= 1) {
			if (day > 7) {
				str = 1 + " week ago";
			} else {
				str = day + " day ago";
			}
		} else if (hour > 0) {
			str = hour + " hour ago";
		} else if(minute > 0 ){
			str = ((minute == 0) ? 1 : minute) + " mimute ago";
		}
		else
		{
			str = between + " second ago";
		}
		
		return str;
	}
	
}
