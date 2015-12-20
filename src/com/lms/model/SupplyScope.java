package com.lms.model;

public class SupplyScope {

	private String LEAD_ID;
	private String FACTORY_NAME;
	private String BRANCH_NAME;
	private String FACTORY_SUPPLY_SCOPE;
	private String BRANCH_SUPPLY_SCOPE;
	private String WARRANTY_DETAILS;
	private String OPERATOR_REQUIRED;
	private String CREATED_BY;
	private String CREATED_DATE;
	private String MODIFIED_BY;
	private String MODIFIED_DATE;

	public String getLEAD_ID() {
		return LEAD_ID;
	}

	public void setLEAD_ID(String lEAD_ID) {
		LEAD_ID = lEAD_ID;
	}

	public String getFACTORY_NAME() {
		return FACTORY_NAME;
	}

	public void setFACTORY_NAME(String fACTORY_NAME) {
		FACTORY_NAME = fACTORY_NAME;
	}

	public String getBRANCH_NAME() {
		return BRANCH_NAME;
	}

	public void setBRANCH_NAME(String bRANCH_NAME) {
		BRANCH_NAME = bRANCH_NAME;
	}

	public String getFACTORY_SUPPLY_SCOPE() {
		return FACTORY_SUPPLY_SCOPE;
	}

	public void setFACTORY_SUPPLY_SCOPE(String fACTORY_SUPPLY_SCOPE) {
		FACTORY_SUPPLY_SCOPE = fACTORY_SUPPLY_SCOPE;
	}

	public String getBRANCH_SUPPLY_SCOPE() {
		return BRANCH_SUPPLY_SCOPE;
	}

	public void setBRANCH_SUPPLY_SCOPE(String bRANCH_SUPPLY_SCOPE) {
		BRANCH_SUPPLY_SCOPE = bRANCH_SUPPLY_SCOPE;
	}

	public String getWARRANTY_DETAILS() {
		return WARRANTY_DETAILS;
	}

	public void setWARRANTY_DETAILS(String wARRANTY_DETAILS) {
		WARRANTY_DETAILS = wARRANTY_DETAILS;
	}

	public String getOPERATOR_REQUIRED() {
		return OPERATOR_REQUIRED;
	}

	public void setOPERATOR_REQUIRED(String oPERATOR_REQUIRED) {
		OPERATOR_REQUIRED = oPERATOR_REQUIRED;
	}

	public String getCREATED_BY() {
		return CREATED_BY;
	}

	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}

	public String getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(String cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public String getMODIFIED_BY() {
		return MODIFIED_BY;
	}

	public void setMODIFIED_BY(String mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
	}

	public String getMODIFIED_DATE() {
		return MODIFIED_DATE;
	}

	public void setMODIFIED_DATE(String mODIFIED_DATE) {
		MODIFIED_DATE = mODIFIED_DATE;
	}

	@Override
	public String toString() {
		return "SupplyScope [LEAD_ID=" + LEAD_ID + ", FACTORY_NAME="
				+ FACTORY_NAME + ", BRANCH_NAME=" + BRANCH_NAME
				+ ", FACTORY_SUPPLY_SCOPE=" + FACTORY_SUPPLY_SCOPE
				+ ", BRANCH_SUPPLY_SCOPE=" + BRANCH_SUPPLY_SCOPE
				+ ", WARRANTY_DETAILS=" + WARRANTY_DETAILS
				+ ", OPERATOR_REQUIRED=" + OPERATOR_REQUIRED + ", CREATED_BY="
				+ CREATED_BY + ", CREATED_DATE=" + CREATED_DATE
				+ ", MODIFIED_BY=" + MODIFIED_BY + ", MODIFIED_DATE="
				+ MODIFIED_DATE + "]";
	}

}
