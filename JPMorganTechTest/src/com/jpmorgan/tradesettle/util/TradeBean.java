package com.jpmorgan.tradesettle.util;

import java.math.BigDecimal;
import java.util.Date;

public class TradeBean {

	public String entity;
	public String buySell;
	public double agreedFX;
	public String currency;
	public Date instructionDate;
	public Date settleDate;
	public Date revisedSettleDate;
	public int units;
	public BigDecimal price;
	public BigDecimal usdPrice;
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getBuySell() {
		return buySell;
	}
	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	public double getAgreedFX() {
		return agreedFX;
	}
	public void setAgreedFX(double agreedFX) {
		this.agreedFX = agreedFX;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}
	public Date getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getUsdPrice() {
		return usdPrice;
	}
	public void setUsdPrice(BigDecimal usdPrice) {
		this.usdPrice = usdPrice;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb = sb.append(entity).append(" ").append(buySell).append(agreedFX).append(currency).append(instructionDate).append(revisedSettleDate).append(units).append(price);
		return  sb.toString();
	}
	
	public Date getRevisedSettleDate() {
		return revisedSettleDate;
	}
	public void setRevisedSettleDate(Date revisedSettleDate) {
		this.revisedSettleDate = revisedSettleDate;
	}
	
}
