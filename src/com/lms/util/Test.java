package com.lms.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		//String s="2468.045";
		
		//new BigDecimal(s).longValue();
		//long value = bd.longValue();
		//System.out.println(value);
		
		/*float amount = 17913750;

		DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		symbols.setCurrencySymbol(""); // Don't use null
		formatter.setDecimalFormatSymbols(symbols);
		String moneyString = formatter.format(amount);

		System.out.println(moneyString);*/
		 
		/* StringTokenizer st=new StringTokenizer(moneyString,"Rs.");
		 while(st.hasMoreTokens())
		 {
			 System.out.println(st.nextToken()+"");
		 }*/
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String createdDate=dateFormat.format(cal.getTime());
		*/
	/*	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar1 = Calendar.getInstance();
		
		int yearpart = 2010;
		int monthPart = 11;
		int dateDay = 1;
		
		calendar1.set(yearpart, monthPart, dateDay);
		
		System.out.println(calendar1.getActualMaximum(Calendar.DATE));
		//System.out.println(dateFormat.format(calendar1.getTime()));
		
		//System.out.println(dateFormat.format(calendar1.getT .getTime()).);
	//	System.out.println(calendar1.getActualMaximum(calendar1.getTime()));
		
		/* Calendar calendar = Calendar.getInstance();
		// calendar.get

		    int lastDate = calendar.getActualMaximum(Calendar.DATE);

		    System.out.println("Date     : " + dateFormat.format(calendar.getTime()));
		    System.out.println("Last Date: " + lastDate);
		    
		    System.out.println(calendar.getActualMinimum(Calendar.DATE)); 
		    
		    System.out.println("First Date     : " + dateFormat.format(calendar.getTime()));
		    
		    */
		/*int commercialpipes=0;
		int commercialpipesVAT=0;
		int commercialCables=0;
		int commercialCablesVAT=0;
		int commercialInstTotalDGAccess=0;
		int lowSidemateriaSupply=0;
		int lowSidemateriaSupplyVAT=0;
		int labourinstallation=0;
		int installationlabourvat=0;
		int sublowsideworks=0;
		int totalprojectvalue=0;




		String name="commercialpipes="+commercialpipes+",commercialpipesVAT="+commercialpipesVAT+",commercialCables="+commercialCables+",commercialCablesVAT="+commercialCablesVAT+",commercialInstTotalDGAccess="+commercialInstTotalDGAccess+",lowSidemateriaSupply="+lowSidemateriaSupply+",lowSidemateriaSupplyVAT="+lowSidemateriaSupplyVAT+",labourinstallation="+labourinstallation+",installationlabourvat="+installationlabourvat+",sublowsideworks="+sublowsideworks+",totalprojectvalue="+totalprojectvalue;
		String[] exploded=name.split(",");
		System.out.println(exploded.length);
		for(int i=0;i<exploded.length-1;i++)
		{
			String[] t=exploded[i].split("=");
			System.out.println(t[0]);
			System.out.println(t[1]);
		}*/
		
		//String str="15DGJH0221";
		//System.out.println(str.substring(4, 6));
		
		/*Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR);      // The current year as an int
		
		System.out.println(now.get(Calendar.YEAR));
		System.out.println(now.get(Calendar.JANUARY));*/
		/*Calendar now = Calendar.getInstance();
		String sel_txt_year;
		String start_date="",end_date="";
		if ((now.get(Calendar.MONTH)) >= 3) {
			
			sel_txt_year = now.get(Calendar.YEAR) + "-"
					+ (now.get(Calendar.YEAR) + 1);
			start_date=now.get(Calendar.YEAR)+"-04"+"-01";
			end_date=(now.get(Calendar.YEAR)+1)+"-03"+"-31";
		} else {
			sel_txt_year =(now.get(Calendar.YEAR) - 1) + "-"
					+ now.get(Calendar.YEAR);
		}
		System.out.println(start_date);
		System.out.println(end_date);*/
		//String s=System.getProperty("catalina.base");
		//System.out.println(s);
		
		float amount = 179543750;

		DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("hi_IN"));
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		symbols.setCurrencySymbol(""); // Don't use null
		formatter.setDecimalFormatSymbols(symbols);
		String moneyString = formatter.format(amount);

		System.out.println(moneyString);
	}
}
