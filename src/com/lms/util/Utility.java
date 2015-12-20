package com.lms.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility {
	
	
//public static void main(String[] args) {
		/*int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(year%100);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
		
	//getEngineModel("ENGINE");
	Set set=new HashSet();
	set.add("'Volvo'");
	set.add("'MTU'");
	//System.out.println(set);
	String s=set.toString();
	String t=s.replace("[", "");
	String p=t.replace("]", "");
	
	System.out.println(p);
	
	}
*/
	
	public static String encodeURIComponent(String component)   {     
		String result = null;      
		
		try {       
			result = URLEncoder.encode(component, "UTF-8") 
				   .replaceAll("\\%28", "(")                          
				   .replaceAll("\\%29", ")")   		
				   .replaceAll("\\+", "%20")                          
				   .replaceAll("\\%27", "'")   			   
				   .replaceAll("\\%21", "!")
				   .replaceAll("\\%7E", "~");  
		}
		catch (UnsupportedEncodingException e) {       
			result = component;     
		}      
		
		return result;   
	}  
	public static String getCurrentdate()
	{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String createdDate=dateFormat.format(cal.getTime());
		return createdDate;
		
	}
	
	public static String getFirstDayofCurrentMonth()
	
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int yearpart = calendar.get(Calendar.YEAR);
		int monthPart = calendar.get(Calendar.MONTH);;
		int dateDay = 1;
		calendar.set(yearpart, monthPart, dateDay);
		System.out.println(dateFormat.format(calendar.getTime()));
		
		return dateFormat.format(calendar.getTime());
	}
	public static String getCurrentMonth()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int yearpart = calendar.get(Calendar.YEAR);
		int monthPart = calendar.get(Calendar.MONTH);
		return monthPart+"";
	}
	
public static String getLastDayofCurrentMonth()
	
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int yearpart = calendar.get(Calendar.YEAR);
		int monthPart = calendar.get(Calendar.MONTH);
		int dateDay = calendar.getActualMaximum(Calendar.DATE);;
		calendar.set(yearpart, monthPart, dateDay);
		System.out.println(dateFormat.format(calendar.getTime()));
		
		return dateFormat.format(calendar.getTime());
	}

public static String getBusinessType(String s)

{
	
	String str=s.substring(2, 4);
	return str;
}
	
	

}
