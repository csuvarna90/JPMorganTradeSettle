package com.jpmorgan.tradesettle.util;

import java.util.Calendar;
import java.util.Date;

public class TradeUtil {

	public static Date validateSettleDate(Date incoming,String flag) {
		Date nextWorkDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(incoming);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		if("ST".equals(flag)) {
			//Friday and Saturday OFF. Next working day is Sunday
			if(day==Calendar.FRIDAY ) {
				
				cal.add(Calendar.DATE, 2); //to get Sunday
								
			}else if (day==Calendar.SATURDAY) {
				
				cal.add(Calendar.DATE, 1); //to get Sunday
				
			}
			
		}else if("MF".equals(flag)) {
			//Saturday and Sunday OFF. Next working day is Monday
			if(day==Calendar.SATURDAY ) {
				
				cal.add(Calendar.DATE, 2); //to get Monday
								
			}else if (day==Calendar.SUNDAY) {
				
				cal.add(Calendar.DATE, 1); //to get Monday
				
			}		
			
		}
		
		nextWorkDate = cal.getTime();
		
		return nextWorkDate;
	}
	
	public static String checkCurrencyFlag(String currency) {
		String flag="";
		if("AED".equals(currency) || "SAR".equals(currency)) {
			flag = "ST";//Sunday-thursday
		}else {
			flag = "MF";//Monday-Friday
		}
		return flag;
	}

}
