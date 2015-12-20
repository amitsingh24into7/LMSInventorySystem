package com.lms.model;

public class Comments {

	
	private String DETAILS;
	private String CREATED_BY;
	private String CRETAED_DATE;
	private String LEAD_ID;
	private String TYPES;
	
	public String getTYPES() {
		return TYPES;
	}
	public void setTYPES(String tYPES) {
		TYPES = tYPES;
	}
	public String getDETAILS() {
		return DETAILS;
	}
	public void setDETAILS(String dETAILS) {
		DETAILS = dETAILS;
	}
	public String getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}
	public String getCRETAED_DATE() {
		return CRETAED_DATE;
	}
	public void setCRETAED_DATE(String cRETAED_DATE) {
		CRETAED_DATE = cRETAED_DATE;
	}
	public String getLEAD_ID() {
		return LEAD_ID;
	}
	public void setLEAD_ID(String lEAD_ID) {
		LEAD_ID = lEAD_ID;
	}
	@Override
	public String toString() {
		return "Comments [DETAILS=" + DETAILS + ", CREATED_BY=" + CREATED_BY
				+ ", CRETAED_DATE=" + CRETAED_DATE + ", LEAD_ID=" + LEAD_ID
				+ ", TYPES=" + TYPES + "]";
	}
	
}
