package com.jpmorgan.tradesettle.report;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jpmorgan.tradesettle.util.*;

public class TradeReportGenerator {

	private static ArrayList<TradeBean> list = new ArrayList<TradeBean>();
	private BigDecimal totalIncoming = new BigDecimal(0.0);
	private BigDecimal totalOutGoing = new BigDecimal(0.0);
	
	public static void main(String[] args) {
		
		TradeReportGenerator report = new TradeReportGenerator();
		list.addAll(report.createSample());
		
		report.settleTrade(list);
		
		report.generateReport(list);

	}
	public ArrayList<TradeBean> createSample() {
		
		ArrayList<TradeBean> list = new ArrayList<TradeBean>();
		//String sample1 = "foo/B/0.50/SGP/01 Jan 2016/02 Jan 2016/200/100.25";
		//String sample2 = "bar/S/0.22/AED/05 Jan 2016/06 Jan 2016/450/150.55";
		TradeBean trade = new TradeBean();
		trade.setEntity("foo");
		trade.setBuySell("B");
		trade.setAgreedFX(0.50);
		trade.setCurrency("SGP");
		try {
			trade.setInstructionDate(new SimpleDateFormat("dd-MMM-yyyy").parse(("01-Jan-2016")));
			trade.setSettleDate(new SimpleDateFormat("dd-MMM-yyyy").parse(("02-Jan-2016")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		trade.setUnits(200);
		trade.setPrice(new BigDecimal(100.25));
		
		TradeBean trade1 = new TradeBean();
		trade1.setEntity("bar");
		trade1.setBuySell("S");
		trade1.setAgreedFX(0.22);
		trade1.setCurrency("AED");
		try {
			trade1.setInstructionDate(new SimpleDateFormat("dd-MMM-yyyy").parse(("05-Jan-2016")));
			trade1.setSettleDate(new SimpleDateFormat("dd-MMM-yyyy").parse(("06-Jan-2016")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		trade1.setUnits(450);
		trade1.setPrice(new BigDecimal(150.5));
		
		list.add(trade);
		list.add(trade1);
		
		
		return list;
	}
	
	
	public void settleTrade(ArrayList<TradeBean> list) {
		Date settleDate;
		Date revised;
		String currency;
		BigDecimal usdPrice = new BigDecimal(0.0);
		for (TradeBean tradeBean : list) {
			
			//Check whether the given settle Date satisfies the conditions
			settleDate = tradeBean.getSettleDate();
			currency = tradeBean.getCurrency();
			String flag = TradeUtil.checkCurrencyFlag(currency);
			
			revised = TradeUtil.validateSettleDate(settleDate, flag);
			tradeBean.setRevisedSettleDate(revised);
			
			usdPrice = tradeBean.getPrice().multiply( new BigDecimal(tradeBean.getUnits()) ).multiply(new BigDecimal(tradeBean.getAgreedFX()));
			
			tradeBean.setUsdPrice(usdPrice);
			
		}
		
		
		
	}
	
	public void generateReport(ArrayList<TradeBean> list) {
		
		BigDecimal highestIncoming = new BigDecimal(0.0);
		BigDecimal highestOutGoing = new BigDecimal(0.0);
		String highestIncomingEntity="";
		String highestOutgoingEntity="";
		
		for (TradeBean tradeBean : list) {
			if("S".equals(tradeBean.getBuySell()) ){
				
				totalIncoming = totalIncoming.add(tradeBean.getUsdPrice());
				if(highestIncoming.compareTo(tradeBean.getUsdPrice()) ==-1) {
					highestIncoming = tradeBean.getUsdPrice();
					highestIncomingEntity = tradeBean.getEntity();
				}
				
			}else if("B".equals(tradeBean.getBuySell()) ) {
				
				totalOutGoing = totalOutGoing.add(tradeBean.getUsdPrice());
				if(highestOutGoing.compareTo(tradeBean.getUsdPrice()) ==-1) {
					highestOutGoing = tradeBean.getUsdPrice();
					highestOutgoingEntity = tradeBean.getEntity();
				}
				
			}			
			 
		}
		
		System.out.println("Generating Report...");
		System.out.println("Total Incoming Amount = " + new DecimalFormat("#0.##").format(totalIncoming));
		System.out.println("Total Outgoing Amount = " + new DecimalFormat("#0.##").format(totalOutGoing));
		System.out.println("Highest valued Incoming Entity = " + highestIncomingEntity);
		System.out.println("Highest valued Incoming Amount = " + new DecimalFormat("#0.##").format(highestIncoming));
		System.out.println("Highest valued OutGoing Entity = " + highestOutgoingEntity);
		System.out.println("Highest valued OutGoing Amount = " + new DecimalFormat("#0.##").format(highestOutGoing));
	}


}
