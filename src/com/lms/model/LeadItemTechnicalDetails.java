package com.lms.model;

public class LeadItemTechnicalDetails {
	private int ID;
	private int DTL_ID;
	private String LEAD_ID;
	private String BUSINESS_TYPE;
	private String FUEL_TANK;
	private String ENGINE_OPTIONAL;
	private String ALTERNATOR_OPTIONAL;
	private String PANEL_OPTIONAL;
	private String SOLOPARALLEL;
	private String TYPE_OF_PANEL;
	private String TESTING_PROCEDURE;
	private String DG_TESTING;
	private String TESTING_CHARGES_INCLUDED;
	private String CREATED_DATE;
	private String CREATED_BY;
	private String MODIFIED_BY;
	private String MODIFIED_DATE;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getDTL_ID() {
		return DTL_ID;
	}
	public void setDTL_ID(int dTL_ID) {
		DTL_ID = dTL_ID;
	}
	public String getLEAD_ID() {
		return LEAD_ID;
	}
	public void setLEAD_ID(String lEAD_ID) {
		LEAD_ID = lEAD_ID;
	}
	public String getBUSINESS_TYPE() {
		return BUSINESS_TYPE;
	}
	public void setBUSINESS_TYPE(String bUSINESS_TYPE) {
		BUSINESS_TYPE = bUSINESS_TYPE;
	}
	public String getFUEL_TANK() {
		return FUEL_TANK;
	}
	public void setFUEL_TANK(String fUEL_TANK) {
		FUEL_TANK = fUEL_TANK;
	}
	public String getENGINE_OPTIONAL() {
		return ENGINE_OPTIONAL;
	}
	public void setENGINE_OPTIONAL(String eNGINE_OPTIONAL) {
		ENGINE_OPTIONAL = eNGINE_OPTIONAL;
	}
	public String getALTERNATOR_OPTIONAL() {
		return ALTERNATOR_OPTIONAL;
	}
	public void setALTERNATOR_OPTIONAL(String aLTERNATOR_OPTIONAL) {
		ALTERNATOR_OPTIONAL = aLTERNATOR_OPTIONAL;
	}
	public String getPANEL_OPTIONAL() {
		return PANEL_OPTIONAL;
	}
	public void setPANEL_OPTIONAL(String pANEL_OPTIONAL) {
		PANEL_OPTIONAL = pANEL_OPTIONAL;
	}
	public String getSOLOPARALLEL() {
		return SOLOPARALLEL;
	}
	public void setSOLOPARALLEL(String sOLOPARALLEL) {
		SOLOPARALLEL = sOLOPARALLEL;
	}
	public String getTYPE_OF_PANEL() {
		return TYPE_OF_PANEL;
	}
	public void setTYPE_OF_PANEL(String tYPE_OF_PANEL) {
		TYPE_OF_PANEL = tYPE_OF_PANEL;
	}
	public String getTESTING_PROCEDURE() {
		return TESTING_PROCEDURE;
	}
	public void setTESTING_PROCEDURE(String tESTING_PROCEDURE) {
		TESTING_PROCEDURE = tESTING_PROCEDURE;
	}
	public String getDG_TESTING() {
		return DG_TESTING;
	}
	public void setDG_TESTING(String dG_TESTING) {
		DG_TESTING = dG_TESTING;
	}
	public String getTESTING_CHARGES_INCLUDED() {
		return TESTING_CHARGES_INCLUDED;
	}
	public void setTESTING_CHARGES_INCLUDED(String tESTING_CHARGES_INCLUDED) {
		TESTING_CHARGES_INCLUDED = tESTING_CHARGES_INCLUDED;
	}
	public String getCREATED_DATE() {
		return CREATED_DATE;
	}
	public void setCREATED_DATE(String cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}
	public String getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
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
		return "LeadItemTechnicalDetails [ID=" + ID + ", DTL_ID=" + DTL_ID
				+ ", LEAD_ID=" + LEAD_ID + ", BUSINESS_TYPE=" + BUSINESS_TYPE
				+ ", FUEL_TANK=" + FUEL_TANK + ", ENGINE_OPTIONAL="
				+ ENGINE_OPTIONAL + ", ALTERNATOR_OPTIONAL="
				+ ALTERNATOR_OPTIONAL + ", PANEL_OPTIONAL=" + PANEL_OPTIONAL
				+ ", SOLOPARALLEL=" + SOLOPARALLEL + ", TYPE_OF_PANEL="
				+ TYPE_OF_PANEL + ", TESTING_PROCEDURE=" + TESTING_PROCEDURE
				+ ", DG_TESTING=" + DG_TESTING + ", TESTING_CHARGES_INCLUDED="
				+ TESTING_CHARGES_INCLUDED + ", CREATED_DATE=" + CREATED_DATE
				+ ", CERATED_BY=" + CREATED_BY + ", MODIFIED_BY=" + MODIFIED_BY
				+ ", MODIFIED_DATE=" + MODIFIED_DATE + "]";
	}

}
