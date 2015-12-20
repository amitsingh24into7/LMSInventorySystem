package com.lms.model;

public class EngineStatus {
	
	private String engine;
	private String chance;
	private String qty;
	private String amt;
	private String region;
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getChance() {
		return chance;
	}
	public void setChance(String chance) {
		this.chance = chance;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "EngineStatus [engine=" + engine + ", chance=" + chance
				+ ", qty=" + qty + ", amt=" + amt + ", region=" + region + "]";
	}
	
	

}
