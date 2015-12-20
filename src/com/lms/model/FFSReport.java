package com.lms.model;

public class FFSReport {
	
	private String PROJECT_TYPE;
	private String ENGINE_NAME;
	
	private String FIN_YEAR;
	private String STOCK_AS_ON;
	private String TRANSIT;
	private String MODIFIED_DATE;
	private String MODIFIED_BY;

	
	private String m1qty;
	private String m2qty;
	private String m3qty;
	private String m4qty;
	private String m5qty;
	private String m6qty;
	private String m7qty;
	private String m8qty;
	private String m9qty;
	private String m10qty;
	private String m11qty;
	private String m12qty;
	private String totalFStock;
	private String totalStock;
	private String exe_pending_order;
	private String pending_order_onhold;
	private String totalPendingorder;
	private String leads;
	
	public String getPROJECT_TYPE() {
		return PROJECT_TYPE;
	}
	public void setPROJECT_TYPE(String pROJECT_TYPE) {
		PROJECT_TYPE = pROJECT_TYPE;
	}
	public String getENGINE_NAME() {
		return ENGINE_NAME;
	}
	public void setENGINE_NAME(String eNGINE_NAME) {
		ENGINE_NAME = eNGINE_NAME;
	}
	public String getFIN_YEAR() {
		return FIN_YEAR;
	}
	public void setFIN_YEAR(String fIN_YEAR) {
		FIN_YEAR = fIN_YEAR;
	}
	public String getSTOCK_AS_ON() {
		return STOCK_AS_ON;
	}
	public void setSTOCK_AS_ON(String sTOCK_AS_ON) {
		STOCK_AS_ON = sTOCK_AS_ON;
	}
	public String getTRANSIT() {
		return TRANSIT;
	}
	public void setTRANSIT(String tRANSIT) {
		TRANSIT = tRANSIT;
	}
	public String getMODIFIED_DATE() {
		return MODIFIED_DATE;
	}
	public void setMODIFIED_DATE(String mODIFIED_DATE) {
		MODIFIED_DATE = mODIFIED_DATE;
	}
	public String getMODIFIED_BY() {
		return MODIFIED_BY;
	}
	public void setMODIFIED_BY(String mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
	}
	public String getM1qty() {
		return m1qty;
	}
	public void setM1qty(String m1qty) {
		this.m1qty = m1qty;
	}
	public String getM2qty() {
		return m2qty;
	}
	public void setM2qty(String m2qty) {
		this.m2qty = m2qty;
	}
	public String getM3qty() {
		return m3qty;
	}
	public void setM3qty(String m3qty) {
		this.m3qty = m3qty;
	}
	public String getM4qty() {
		return m4qty;
	}
	public void setM4qty(String m4qty) {
		this.m4qty = m4qty;
	}
	public String getM5qty() {
		return m5qty;
	}
	public void setM5qty(String m5qty) {
		this.m5qty = m5qty;
	}
	public String getM6qty() {
		return m6qty;
	}
	public void setM6qty(String m6qty) {
		this.m6qty = m6qty;
	}
	public String getM7qty() {
		return m7qty;
	}
	public void setM7qty(String m7qty) {
		this.m7qty = m7qty;
	}
	public String getM8qty() {
		return m8qty;
	}
	public void setM8qty(String m8qty) {
		this.m8qty = m8qty;
	}
	public String getM9qty() {
		return m9qty;
	}
	public void setM9qty(String m9qty) {
		this.m9qty = m9qty;
	}
	public String getM10qty() {
		return m10qty;
	}
	public void setM10qty(String m10qty) {
		this.m10qty = m10qty;
	}
	public String getM11qty() {
		return m11qty;
	}
	public void setM11qty(String m11qty) {
		this.m11qty = m11qty;
	}
	public String getM12qty() {
		return m12qty;
	}
	public void setM12qty(String m12qty) {
		this.m12qty = m12qty;
	}
	public String getTotalFStock() {
		return totalFStock;
	}
	public void setTotalFStock(String totalFStock) {
		this.totalFStock = totalFStock;
	}
	public String getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(String totalStock) {
		this.totalStock = totalStock;
	}
	public String getExe_pending_order() {
		return exe_pending_order;
	}
	public void setExe_pending_order(String exe_pending_order) {
		this.exe_pending_order = exe_pending_order;
	}
	public String getPending_order_onhold() {
		return pending_order_onhold;
	}
	public void setPending_order_onhold(String pending_order_onhold) {
		this.pending_order_onhold = pending_order_onhold;
	}
	public String getTotalPendingorder() {
		return totalPendingorder;
	}
	public void setTotalPendingorder(String totalPendingorder) {
		this.totalPendingorder = totalPendingorder;
	}
	public String getLeads() {
		return leads;
	}
	public void setLeads(String leads) {
		this.leads = leads;
	}
	@Override
	public String toString() {
		return "FFSReport [ PROJECT_TYPE=" + PROJECT_TYPE
				+ ", ENGINE_NAME=" + ENGINE_NAME + ", FIN_YEAR=" + FIN_YEAR
				+ ", STOCK_AS_ON=" + STOCK_AS_ON + ", TRANSIT=" + TRANSIT
				+ ", MODIFIED_DATE=" + MODIFIED_DATE + ", MODIFIED_BY="
				+ MODIFIED_BY + ", m1qty=" + m1qty + ", m2qty=" + m2qty
				+ ", m3qty=" + m3qty + ", m4qty=" + m4qty + ", m5qty=" + m5qty
				+ ", m6qty=" + m6qty + ", m7qty=" + m7qty + ", m8qty=" + m8qty
				+ ", m9qty=" + m9qty + ", m10qty=" + m10qty + ", m11qty="
				+ m11qty + ", m12qty=" + m12qty + ", totalFStock="
				+ totalFStock + ", totalStock=" + totalStock
				+ ", exe_pending_order=" + exe_pending_order
				+ ", pending_order_onhold=" + pending_order_onhold
				+ ", totalPendingorder=" + totalPendingorder + ", leads="
				+ leads + "]";
	}
	
}
