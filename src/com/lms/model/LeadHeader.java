package com.lms.model;

public class LeadHeader {
	private int ID;
	private String LEAD_ID;
	
	private String OWNER_ID;
	private String PROJECT_EXE_PLACE;
	private String CREATION_DATE;


	private String TRANSACTION_TYPE;
	private String ENQUIRY_TYPE;
	private String PROJECT_TYPE;
	

	private String LEAD_BUDG;

	private String PROJECT_EXE_STATE;
	private String LEAD_CREATION_PLACE;
	private String LEAD_CREATION_STATE;
	private String LEAD_CRETION_SHORT_STATE;
	private String LEAD_CREATION_REGION;
	private String LEAD_EXECUTION_DATE;
	private String DG_DELIVERY_DATE;
	private String PROJECT_CLOUSURE_DATE;
	

	private String CREATED_BY;
	private String MODIFIED_DATE;
	private String MODIFIED_BY;

	private String OWNER_NAME;
	private String PROJECT_REFERENCE;
	private String DG_TYPE;

	private String SEGMENT;
	private String COMPETITIONS;
	private String LEAD_CHANCES;
	private String LEAD_STATUS;
	private String CONSULTANT_PERSON_NAME;
	private String CONSULTANT_NAME;
	private String CONSULTANT_MOBILE_NO;

	private String CUSTOMER_NAME;
	private String CUSTOMER_ADDRESS;
	private String CUSTOMER_ALTERNATE_NO;
	private String CUSTOMER_EMAIL_ADDRESS;
	private String CUSTOMER_MOBILENO;
	private String END_PERSON_CUSTOMER_NAME;
	private String END_PERSON_CUSTOMER_CONTACTNO;
	private String PMC_NAME;
	private String PMC_CONTACT_PERSON;
	private String PMC_CONTACT_NO;
	private String PMC_CONTACT_ALT_NO;
	private String PMC_EMAIL;
	
	private double LOW_SIDE_SUPPLY_BASIC;
	private double LOW_SIDE_LABOUR_BASIC;
	private double LOW_SIDE_MARGIN;
	private double PROJECT_MARGIN;
	private String DG_ED_VALUE;
	private double PROJECT_TOTAL;
	private String INDENT_NO;
	
	private String DG_BASIC_MARGIN;
	private String DG_SELLING_VALUE;
	private String LOW_SIDE_SELLING_VALUE;
	private String PROJECT_SELLING_VALUE;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLEAD_ID() {
		return LEAD_ID;
	}
	public void setLEAD_ID(String lEAD_ID) {
		LEAD_ID = lEAD_ID;
	}
	public String getOWNER_ID() {
		return OWNER_ID;
	}
	public void setOWNER_ID(String oWNER_ID) {
		OWNER_ID = oWNER_ID;
	}
	public String getPROJECT_EXE_PLACE() {
		return PROJECT_EXE_PLACE;
	}
	public void setPROJECT_EXE_PLACE(String pROJECT_EXE_PLACE) {
		PROJECT_EXE_PLACE = pROJECT_EXE_PLACE;
	}
	public String getCREATION_DATE() {
		return CREATION_DATE;
	}
	public void setCREATION_DATE(String cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}
	public String getTRANSACTION_TYPE() {
		return TRANSACTION_TYPE;
	}
	public void setTRANSACTION_TYPE(String tRANSACTION_TYPE) {
		TRANSACTION_TYPE = tRANSACTION_TYPE;
	}
	public String getENQUIRY_TYPE() {
		return ENQUIRY_TYPE;
	}
	public void setENQUIRY_TYPE(String eNQUIRY_TYPE) {
		ENQUIRY_TYPE = eNQUIRY_TYPE;
	}
	public String getPROJECT_TYPE() {
		return PROJECT_TYPE;
	}
	public void setPROJECT_TYPE(String pROJECT_TYPE) {
		PROJECT_TYPE = pROJECT_TYPE;
	}
	public String getLEAD_BUDG() {
		return LEAD_BUDG;
	}
	public void setLEAD_BUDG(String lEAD_BUDG) {
		LEAD_BUDG = lEAD_BUDG;
	}
	public String getPROJECT_EXE_STATE() {
		return PROJECT_EXE_STATE;
	}
	public void setPROJECT_EXE_STATE(String pROJECT_EXE_STATE) {
		PROJECT_EXE_STATE = pROJECT_EXE_STATE;
	}
	public String getLEAD_CREATION_PLACE() {
		return LEAD_CREATION_PLACE;
	}
	public void setLEAD_CREATION_PLACE(String lEAD_CREATION_PLACE) {
		LEAD_CREATION_PLACE = lEAD_CREATION_PLACE;
	}
	public String getLEAD_CREATION_STATE() {
		return LEAD_CREATION_STATE;
	}
	public void setLEAD_CREATION_STATE(String lEAD_CREATION_STATE) {
		LEAD_CREATION_STATE = lEAD_CREATION_STATE;
	}
	public String getLEAD_CRETION_SHORT_STATE() {
		return LEAD_CRETION_SHORT_STATE;
	}
	public void setLEAD_CRETION_SHORT_STATE(String lEAD_CRETION_SHORT_STATE) {
		LEAD_CRETION_SHORT_STATE = lEAD_CRETION_SHORT_STATE;
	}
	public String getLEAD_CREATION_REGION() {
		return LEAD_CREATION_REGION;
	}
	public void setLEAD_CREATION_REGION(String lEAD_CREATION_REGION) {
		LEAD_CREATION_REGION = lEAD_CREATION_REGION;
	}
	public String getLEAD_EXECUTION_DATE() {
		return LEAD_EXECUTION_DATE;
	}
	public void setLEAD_EXECUTION_DATE(String lEAD_EXECUTION_DATE) {
		LEAD_EXECUTION_DATE = lEAD_EXECUTION_DATE;
	}
	public String getDG_DELIVERY_DATE() {
		return DG_DELIVERY_DATE;
	}
	public void setDG_DELIVERY_DATE(String dG_DELIVERY_DATE) {
		DG_DELIVERY_DATE = dG_DELIVERY_DATE;
	}
	public String getPROJECT_CLOUSURE_DATE() {
		return PROJECT_CLOUSURE_DATE;
	}
	public void setPROJECT_CLOUSURE_DATE(String pROJECT_CLOUSURE_DATE) {
		PROJECT_CLOUSURE_DATE = pROJECT_CLOUSURE_DATE;
	}
	public String getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
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
	public String getOWNER_NAME() {
		return OWNER_NAME;
	}
	public void setOWNER_NAME(String oWNER_NAME) {
		OWNER_NAME = oWNER_NAME;
	}
	public String getPROJECT_REFERENCE() {
		return PROJECT_REFERENCE;
	}
	public void setPROJECT_REFERENCE(String pROJECT_REFERENCE) {
		PROJECT_REFERENCE = pROJECT_REFERENCE;
	}
	public String getDG_TYPE() {
		return DG_TYPE;
	}
	public void setDG_TYPE(String dG_TYPE) {
		DG_TYPE = dG_TYPE;
	}
	public String getSEGMENT() {
		return SEGMENT;
	}
	public void setSEGMENT(String sEGMENT) {
		SEGMENT = sEGMENT;
	}
	public String getCOMPETITIONS() {
		return COMPETITIONS;
	}
	public void setCOMPETITIONS(String cOMPETITIONS) {
		COMPETITIONS = cOMPETITIONS;
	}
	public String getLEAD_CHANCES() {
		return LEAD_CHANCES;
	}
	public void setLEAD_CHANCES(String lEAD_CHANCES) {
		LEAD_CHANCES = lEAD_CHANCES;
	}
	public String getLEAD_STATUS() {
		return LEAD_STATUS;
	}
	public void setLEAD_STATUS(String lEAD_STATUS) {
		LEAD_STATUS = lEAD_STATUS;
	}
	public String getCONSULTANT_PERSON_NAME() {
		return CONSULTANT_PERSON_NAME;
	}
	public void setCONSULTANT_PERSON_NAME(String cONSULTANT_PERSON_NAME) {
		CONSULTANT_PERSON_NAME = cONSULTANT_PERSON_NAME;
	}
	public String getCONSULTANT_NAME() {
		return CONSULTANT_NAME;
	}
	public void setCONSULTANT_NAME(String cONSULTANT_NAME) {
		CONSULTANT_NAME = cONSULTANT_NAME;
	}
	public String getCONSULTANT_MOBILE_NO() {
		return CONSULTANT_MOBILE_NO;
	}
	public void setCONSULTANT_MOBILE_NO(String cONSULTANT_MOBILE_NO) {
		CONSULTANT_MOBILE_NO = cONSULTANT_MOBILE_NO;
	}
	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}
	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
	}
	public String getCUSTOMER_ADDRESS() {
		return CUSTOMER_ADDRESS;
	}
	public void setCUSTOMER_ADDRESS(String cUSTOMER_ADDRESS) {
		CUSTOMER_ADDRESS = cUSTOMER_ADDRESS;
	}
	public String getCUSTOMER_ALTERNATE_NO() {
		return CUSTOMER_ALTERNATE_NO;
	}
	public void setCUSTOMER_ALTERNATE_NO(String cUSTOMER_ALTERNATE_NO) {
		CUSTOMER_ALTERNATE_NO = cUSTOMER_ALTERNATE_NO;
	}
	public String getCUSTOMER_EMAIL_ADDRESS() {
		return CUSTOMER_EMAIL_ADDRESS;
	}
	public void setCUSTOMER_EMAIL_ADDRESS(String cUSTOMER_EMAIL_ADDRESS) {
		CUSTOMER_EMAIL_ADDRESS = cUSTOMER_EMAIL_ADDRESS;
	}
	public String getCUSTOMER_MOBILENO() {
		return CUSTOMER_MOBILENO;
	}
	public void setCUSTOMER_MOBILENO(String cUSTOMER_MOBILENO) {
		CUSTOMER_MOBILENO = cUSTOMER_MOBILENO;
	}
	public String getEND_PERSON_CUSTOMER_NAME() {
		return END_PERSON_CUSTOMER_NAME;
	}
	public void setEND_PERSON_CUSTOMER_NAME(String eND_PERSON_CUSTOMER_NAME) {
		END_PERSON_CUSTOMER_NAME = eND_PERSON_CUSTOMER_NAME;
	}
	public String getEND_PERSON_CUSTOMER_CONTACTNO() {
		return END_PERSON_CUSTOMER_CONTACTNO;
	}
	public void setEND_PERSON_CUSTOMER_CONTACTNO(
			String eND_PERSON_CUSTOMER_CONTACTNO) {
		END_PERSON_CUSTOMER_CONTACTNO = eND_PERSON_CUSTOMER_CONTACTNO;
	}
	public String getPMC_NAME() {
		return PMC_NAME;
	}
	public void setPMC_NAME(String pMC_NAME) {
		PMC_NAME = pMC_NAME;
	}
	public String getPMC_CONTACT_PERSON() {
		return PMC_CONTACT_PERSON;
	}
	public void setPMC_CONTACT_PERSON(String pMC_CONTACT_PERSON) {
		PMC_CONTACT_PERSON = pMC_CONTACT_PERSON;
	}
	public String getPMC_CONTACT_NO() {
		return PMC_CONTACT_NO;
	}
	public void setPMC_CONTACT_NO(String pMC_CONTACT_NO) {
		PMC_CONTACT_NO = pMC_CONTACT_NO;
	}
	public String getPMC_CONTACT_ALT_NO() {
		return PMC_CONTACT_ALT_NO;
	}
	public void setPMC_CONTACT_ALT_NO(String pMC_CONTACT_ALT_NO) {
		PMC_CONTACT_ALT_NO = pMC_CONTACT_ALT_NO;
	}
	public String getPMC_EMAIL() {
		return PMC_EMAIL;
	}
	public void setPMC_EMAIL(String pMC_EMAIL) {
		PMC_EMAIL = pMC_EMAIL;
	}
	public double getLOW_SIDE_SUPPLY_BASIC() {
		return LOW_SIDE_SUPPLY_BASIC;
	}
	public void setLOW_SIDE_SUPPLY_BASIC(double lOW_SIDE_SUPPLY_BASIC) {
		LOW_SIDE_SUPPLY_BASIC = lOW_SIDE_SUPPLY_BASIC;
	}
	public double getLOW_SIDE_LABOUR_BASIC() {
		return LOW_SIDE_LABOUR_BASIC;
	}
	public void setLOW_SIDE_LABOUR_BASIC(double lOW_SIDE_LABOUR_BASIC) {
		LOW_SIDE_LABOUR_BASIC = lOW_SIDE_LABOUR_BASIC;
	}
	public double getLOW_SIDE_MARGIN() {
		return LOW_SIDE_MARGIN;
	}
	public void setLOW_SIDE_MARGIN(double lOW_SIDE_MARGIN) {
		LOW_SIDE_MARGIN = lOW_SIDE_MARGIN;
	}
	public double getPROJECT_MARGIN() {
		return PROJECT_MARGIN;
	}
	public void setPROJECT_MARGIN(double pROJECT_MARGIN) {
		PROJECT_MARGIN = pROJECT_MARGIN;
	}
	public String getDG_ED_VALUE() {
		return DG_ED_VALUE;
	}
	public void setDG_ED_VALUE(String dG_ED_VALUE) {
		DG_ED_VALUE = dG_ED_VALUE;
	}
	public double getPROJECT_TOTAL() {
		return PROJECT_TOTAL;
	}
	public void setPROJECT_TOTAL(double pROJECT_TOTAL) {
		PROJECT_TOTAL = pROJECT_TOTAL;
	}
	public String getINDENT_NO() {
		return INDENT_NO;
	}
	public void setINDENT_NO(String iNDENT_NO) {
		INDENT_NO = iNDENT_NO;
	}
	public String getDG_BASIC_MARGIN() {
		return DG_BASIC_MARGIN;
	}
	public void setDG_BASIC_MARGIN(String dG_BASIC_MARGIN) {
		DG_BASIC_MARGIN = dG_BASIC_MARGIN;
	}
	public String getDG_SELLING_VALUE() {
		return DG_SELLING_VALUE;
	}
	public void setDG_SELLING_VALUE(String dG_SELLING_VALUE) {
		DG_SELLING_VALUE = dG_SELLING_VALUE;
	}
	public String getLOW_SIDE_SELLING_VALUE() {
		return LOW_SIDE_SELLING_VALUE;
	}
	public void setLOW_SIDE_SELLING_VALUE(String lOW_SIDE_SELLING_VALUE) {
		LOW_SIDE_SELLING_VALUE = lOW_SIDE_SELLING_VALUE;
	}
	public String getPROJECT_SELLING_VALUE() {
		return PROJECT_SELLING_VALUE;
	}
	public void setPROJECT_SELLING_VALUE(String pROJECT_SELLING_VALUE) {
		PROJECT_SELLING_VALUE = pROJECT_SELLING_VALUE;
	}
	@Override
	public String toString() {
		return "LeadHeader [ID=" + ID + ", LEAD_ID=" + LEAD_ID + ", OWNER_ID="
				+ OWNER_ID + ", PROJECT_EXE_PLACE=" + PROJECT_EXE_PLACE
				+ ", CREATION_DATE=" + CREATION_DATE + ", TRANSACTION_TYPE="
				+ TRANSACTION_TYPE + ", ENQUIRY_TYPE=" + ENQUIRY_TYPE
				+ ", PROJECT_TYPE=" + PROJECT_TYPE + ", LEAD_BUDG=" + LEAD_BUDG
				+ ", PROJECT_EXE_STATE=" + PROJECT_EXE_STATE
				+ ", LEAD_CREATION_PLACE=" + LEAD_CREATION_PLACE
				+ ", LEAD_CREATION_STATE=" + LEAD_CREATION_STATE
				+ ", LEAD_CRETION_SHORT_STATE=" + LEAD_CRETION_SHORT_STATE
				+ ", LEAD_CREATION_REGION=" + LEAD_CREATION_REGION
				+ ", LEAD_EXECUTION_DATE=" + LEAD_EXECUTION_DATE
				+ ", DG_DELIVERY_DATE=" + DG_DELIVERY_DATE
				+ ", PROJECT_CLOUSURE_DATE=" + PROJECT_CLOUSURE_DATE
				+ ", CREATED_BY=" + CREATED_BY + ", MODIFIED_DATE="
				+ MODIFIED_DATE + ", MODIFIED_BY=" + MODIFIED_BY
				+ ", OWNER_NAME=" + OWNER_NAME + ", PROJECT_REFERENCE="
				+ PROJECT_REFERENCE + ", DG_TYPE=" + DG_TYPE + ", SEGMENT="
				+ SEGMENT + ", COMPETITIONS=" + COMPETITIONS
				+ ", LEAD_CHANCES=" + LEAD_CHANCES + ", LEAD_STATUS="
				+ LEAD_STATUS + ", CONSULTANT_PERSON_NAME="
				+ CONSULTANT_PERSON_NAME + ", CONSULTANT_NAME="
				+ CONSULTANT_NAME + ", CONSULTANT_MOBILE_NO="
				+ CONSULTANT_MOBILE_NO + ", CUSTOMER_NAME=" + CUSTOMER_NAME
				+ ", CUSTOMER_ADDRESS=" + CUSTOMER_ADDRESS
				+ ", CUSTOMER_ALTERNATE_NO=" + CUSTOMER_ALTERNATE_NO
				+ ", CUSTOMER_EMAIL_ADDRESS=" + CUSTOMER_EMAIL_ADDRESS
				+ ", CUSTOMER_MOBILENO=" + CUSTOMER_MOBILENO
				+ ", END_PERSON_CUSTOMER_NAME=" + END_PERSON_CUSTOMER_NAME
				+ ", END_PERSON_CUSTOMER_CONTACTNO="
				+ END_PERSON_CUSTOMER_CONTACTNO + ", PMC_NAME=" + PMC_NAME
				+ ", PMC_CONTACT_PERSON=" + PMC_CONTACT_PERSON
				+ ", PMC_CONTACT_NO=" + PMC_CONTACT_NO
				+ ", PMC_CONTACT_ALT_NO=" + PMC_CONTACT_ALT_NO + ", PMC_EMAIL="
				+ PMC_EMAIL + ", LOW_SIDE_SUPPLY_BASIC="
				+ LOW_SIDE_SUPPLY_BASIC + ", LOW_SIDE_LABOUR_BASIC="
				+ LOW_SIDE_LABOUR_BASIC + ", LOW_SIDE_MARGIN="
				+ LOW_SIDE_MARGIN + ", PROJECT_MARGIN=" + PROJECT_MARGIN
				+ ", DG_ED_VALUE=" + DG_ED_VALUE + ", PROJECT_TOTAL="
				+ PROJECT_TOTAL + ", INDENT_NO=" + INDENT_NO
				+ ", DG_BASIC_MARGIN=" + DG_BASIC_MARGIN
				+ ", DG_SELLING_VALUE=" + DG_SELLING_VALUE
				+ ", LOW_SIDE_SELLING_VALUE=" + LOW_SIDE_SELLING_VALUE
				+ ", PROJECT_SELLING_VALUE=" + PROJECT_SELLING_VALUE + "]";
	}

	
}
