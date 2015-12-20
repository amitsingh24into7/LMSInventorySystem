package com.lms.model;

public class OrderBoard {
	private String LEAD_CREATION_REGION;
	private String PROJECT_EXE_STATE;
	private String PROJECT_EXE_PLACE;
	private String CREATED_BY;
	private String CREATION_DATE;
	private String PROJECT_REFERENCE;
	private String LEAD_ID;
	private String DG_ED_VALUE;
	private String LOW_SIDE_SUPPLY_BASIC;
	private String LOW_SIDE_LABOUR_BASIC;
	private String LOW_SIDE_MARGIN;
	private String DTL_RATING;
	private String DTL_ENGINE_MAKE;
	private String DTL_QTY;
	private String OWNER_ID;
	private String OWNER_NAME;
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
	public String getPROJECT_REFERENCE() {
		return PROJECT_REFERENCE;
	}
	public void setPROJECT_REFERENCE(String pROJECT_REFERENCE) {
		PROJECT_REFERENCE = pROJECT_REFERENCE;
	}
	public String getLEAD_ID() {
		return LEAD_ID;
	}
	public void setLEAD_ID(String lEAD_ID) {
		LEAD_ID = lEAD_ID;
	}
	public String getDG_ED_VALUE() {
		return DG_ED_VALUE;
	}
	public void setDG_ED_VALUE(String dG_ED_VALUE) {
		DG_ED_VALUE = dG_ED_VALUE;
	}
	public String getLOW_SIDE_SUPPLY_BASIC() {
		return LOW_SIDE_SUPPLY_BASIC;
	}
	public void setLOW_SIDE_SUPPLY_BASIC(String lOW_SIDE_SUPPLY_BASIC) {
		LOW_SIDE_SUPPLY_BASIC = lOW_SIDE_SUPPLY_BASIC;
	}
	public String getLOW_SIDE_LABOUR_BASIC() {
		return LOW_SIDE_LABOUR_BASIC;
	}
	public void setLOW_SIDE_LABOUR_BASIC(String lOW_SIDE_LABOUR_BASIC) {
		LOW_SIDE_LABOUR_BASIC = lOW_SIDE_LABOUR_BASIC;
	}
	public String getLOW_SIDE_MARGIN() {
		return LOW_SIDE_MARGIN;
	}
	public void setLOW_SIDE_MARGIN(String lOW_SIDE_MARGIN) {
		LOW_SIDE_MARGIN = lOW_SIDE_MARGIN;
	}
	public String getDTL_RATING() {
		return DTL_RATING;
	}
	public void setDTL_RATING(String dTL_RATING) {
		DTL_RATING = dTL_RATING;
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
	@Override
	public String toString() {
		return "OrderBoard [LEAD_CREATION_REGION=" + LEAD_CREATION_REGION
				+ ", PROJECT_EXE_STATE=" + PROJECT_EXE_STATE
				+ ", PROJECT_EXE_PLACE=" + PROJECT_EXE_PLACE + ", CREATED_BY="
				+ CREATED_BY + ", CREATION_DATE=" + CREATION_DATE
				+ ", PROJECT_REFERENCE=" + PROJECT_REFERENCE + ", LEAD_ID="
				+ LEAD_ID + ", DG_ED_VALUE=" + DG_ED_VALUE
				+ ", LOW_SIDE_SUPPLY_BASIC=" + LOW_SIDE_SUPPLY_BASIC
				+ ", LOW_SIDE_LABOUR_BASIC=" + LOW_SIDE_LABOUR_BASIC
				+ ", LOW_SIDE_MARGIN=" + LOW_SIDE_MARGIN + ", DTL_RATING="
				+ DTL_RATING + ", DTL_ENGINE_MAKE=" + DTL_ENGINE_MAKE
				+ ", DTL_QTY=" + DTL_QTY + ", OWNER_ID=" + OWNER_ID
				+ ", OWNER_NAME=" + OWNER_NAME + "]";
	}

}
