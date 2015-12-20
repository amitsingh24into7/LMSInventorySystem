package com.lms.model;

public class Quote {
	private String OWNER_ID;
	private String OWNER_NAME;
	private String LEAD_CREATION_REGION;
	private String PROJECT_EXE_STATE;
	private String PROJECT_EXE_PLACE;
	private String CREATED_BY;
	private String CREATION_DATE;
	private String LEAD_ID;
	private String LEAD_STATUS;
	private String DTL_ENGINE_MAKE;
	private String DTL_QTY;
	private String DTL_AMOUNT;
	public String getOWNER_ID() {
		return OWNER_ID;
	}
	public void setOWNER_ID(String oWNER_ID) {
		OWNER_ID = oWNER_ID;
	}
	public String getOWNER_NAME() {
		return OWNER_NAME;
	}
	public void setOWNER_NAME(String oWNER_NAME) {
		OWNER_NAME = oWNER_NAME;
	}
	public String getLEAD_CREATION_REGION() {
		return LEAD_CREATION_REGION;
	}
	public void setLEAD_CREATION_REGION(String lEAD_CREATION_REGION) {
		LEAD_CREATION_REGION = lEAD_CREATION_REGION;
	}
	public String getPROJECT_EXE_STATE() {
		return PROJECT_EXE_STATE;
	}
	public void setPROJECT_EXE_STATE(String pROJECT_EXE_STATE) {
		PROJECT_EXE_STATE = pROJECT_EXE_STATE;
	}
	public String getPROJECT_EXE_PLACE() {
		return PROJECT_EXE_PLACE;
	}
	public void setPROJECT_EXE_PLACE(String pROJECT_EXE_PLACE) {
		PROJECT_EXE_PLACE = pROJECT_EXE_PLACE;
	}
	public String getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}
	public String getCREATION_DATE() {
		return CREATION_DATE;
	}
	public void setCREATION_DATE(String cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}
	public String getLEAD_ID() {
		return LEAD_ID;
	}
	public void setLEAD_ID(String lEAD_ID) {
		LEAD_ID = lEAD_ID;
	}
	public String getLEAD_STATUS() {
		return LEAD_STATUS;
	}
	public void setLEAD_STATUS(String lEAD_STATUS) {
		LEAD_STATUS = lEAD_STATUS;
	}
	public String getDTL_ENGINE_MAKE() {
		return DTL_ENGINE_MAKE;
	}
	public void setDTL_ENGINE_MAKE(String dTL_ENGINE_MAKE) {
		DTL_ENGINE_MAKE = dTL_ENGINE_MAKE;
	}
	public String getDTL_QTY() {
		return DTL_QTY;
	}
	public void setDTL_QTY(String dTL_QTY) {
		DTL_QTY = dTL_QTY;
	}
	public String getDTL_AMOUNT() {
		return DTL_AMOUNT;
	}
	public void setDTL_AMOUNT(String dTL_AMOUNT) {
		DTL_AMOUNT = dTL_AMOUNT;
	}
	@Override
	public String toString() {
		return "Quote [OWNER_ID=" + OWNER_ID + ", OWNER_NAME=" + OWNER_NAME
				+ ", LEAD_CREATION_REGION=" + LEAD_CREATION_REGION
				+ ", PROJECT_EXE_STATE=" + PROJECT_EXE_STATE
				+ ", PROJECT_EXE_PLACE=" + PROJECT_EXE_PLACE + ", CREATED_BY="
				+ CREATED_BY + ", CREATION_DATE=" + CREATION_DATE
				+ ", LEAD_ID=" + LEAD_ID + ", LEAD_STATUS=" + LEAD_STATUS
				+ ", DTL_ENGINE_MAKE=" + DTL_ENGINE_MAKE + ", DTL_QTY="
				+ DTL_QTY + ", DTL_AMOUNT=" + DTL_AMOUNT + "]";
	}

}
