package com.lms.model;

public class QuoteRpt {
	private String engine;
	
	private String orderWinQty;
	private String orderLostQty;
	private String orderPendingQty;
	private String orderWinAmount;
	private String orderLostAmount;
	private String orderPendingAmount;
	
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	
	public String getOrderWinQty() {
		return orderWinQty;
	}
	public void setOrderWinQty(String orderWinQty) {
		this.orderWinQty = orderWinQty;
	}
	public String getOrderLostQty() {
		return orderLostQty;
	}
	public void setOrderLostQty(String orderLostQty) {
		this.orderLostQty = orderLostQty;
	}
	public String getOrderPendingQty() {
		return orderPendingQty;
	}
	public void setOrderPendingQty(String orderPendingQty) {
		this.orderPendingQty = orderPendingQty;
	}
	public String getOrderWinAmount() {
		return orderWinAmount;
	}
	public void setOrderWinAmount(String orderWinAmount) {
		this.orderWinAmount = orderWinAmount;
	}
	public String getOrderLostAmount() {
		return orderLostAmount;
	}
	public void setOrderLostAmount(String orderLostAmount) {
		this.orderLostAmount = orderLostAmount;
	}
	public String getOrderPendingAmount() {
		return orderPendingAmount;
	}
	public void setOrderPendingAmount(String orderPendingAmount) {
		this.orderPendingAmount = orderPendingAmount;
	}
	@Override
	public String toString() {
		return "QuoteRpt [engine=" + engine 
				+ ", orderWinQty=" + orderWinQty + ", orderLostQty="
				+ orderLostQty + ", orderPendingQty=" + orderPendingQty
				+ ", orderWinAmount=" + orderWinAmount + ", orderLostAmount="
				+ orderLostAmount + ", orderPendingAmount=" + orderPendingAmount
				+ "]";
	} 

}
