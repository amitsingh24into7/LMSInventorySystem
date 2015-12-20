package com.lms.model;

public class IndentPaymentDetails {
	private int ID;
	private String LEAD_ID;
	private String ITEMTYPE;
	private String ADVANCE;
	private String CHEQUE_NO;
	private String CHEQUE_DATE;
	private String BANK_DETAILS;
	private String BALANCE;
	private String FORMS_TEXT;
	private String PAYMENT_TERMS1;
	private String PAYMENT_TERMS2;
	private String PAYMENT_TERMS3;
	private String PG_TEXT;
	private String LIQUIDATE_DAMAGE_TEXT;
	private String CREATED_BY;
	private String CREATED_DATE;
	private String MODIFIED_BY;
	private String MODIFIED_DATE;
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
	public String getITEMTYPE() {
		return ITEMTYPE;
	}
	public void setITEMTYPE(String iTEMTYPE) {
		ITEMTYPE = iTEMTYPE;
	}
	public String getADVANCE() {
		return ADVANCE;
	}
	public void setADVANCE(String aDVANCE) {
		ADVANCE = aDVANCE;
	}
	public String getCHEQUE_NO() {
		return CHEQUE_NO;
	}
	public void setCHEQUE_NO(String cHEQUE_NO) {
		CHEQUE_NO = cHEQUE_NO;
	}
	public String getCHEQUE_DATE() {
		return CHEQUE_DATE;
	}
	public void setCHEQUE_DATE(String cHEQUE_DATE) {
		CHEQUE_DATE = cHEQUE_DATE;
	}
	public String getBANK_DETAILS() {
		return BANK_DETAILS;
	}
	public void setBANK_DETAILS(String bANK_DETAILS) {
		BANK_DETAILS = bANK_DETAILS;
	}
	public String getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}
	public String getFORMS_TEXT() {
		return FORMS_TEXT;
	}
	public void setFORMS_TEXT(String fORMS_TEXT) {
		FORMS_TEXT = fORMS_TEXT;
	}
	public String getPAYMENT_TERMS1() {
		return PAYMENT_TERMS1;
	}
	public void setPAYMENT_TERMS1(String pAYMENT_TERMS1) {
		PAYMENT_TERMS1 = pAYMENT_TERMS1;
	}
	public String getPAYMENT_TERMS2() {
		return PAYMENT_TERMS2;
	}
	public void setPAYMENT_TERMS2(String pAYMENT_TERMS2) {
		PAYMENT_TERMS2 = pAYMENT_TERMS2;
	}
	public String getPAYMENT_TERMS3() {
		return PAYMENT_TERMS3;
	}
	public void setPAYMENT_TERMS3(String pAYMENT_TERMS3) {
		PAYMENT_TERMS3 = pAYMENT_TERMS3;
	}
	public String getPG_TEXT() {
		return PG_TEXT;
	}
	public void setPG_TEXT(String pG_TEXT) {
		PG_TEXT = pG_TEXT;
	}
	public String getLIQUIDATE_DAMAGE_TEXT() {
		return LIQUIDATE_DAMAGE_TEXT;
	}
	public void setLIQUIDATE_DAMAGE_TEXT(String lIQUIDATE_DAMAGE_TEXT) {
		LIQUIDATE_DAMAGE_TEXT = lIQUIDATE_DAMAGE_TEXT;
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
		return "IndentPaymentDetails [ID=" + ID + ", LEAD_ID=" + LEAD_ID
				+ ", ITEMTYPE=" + ITEMTYPE + ", ADVANCE=" + ADVANCE
				+ ", CHEQUE_NO=" + CHEQUE_NO + ", CHEQUE_DATE=" + CHEQUE_DATE
				+ ", BANK_DETAILS=" + BANK_DETAILS + ", BALANCE=" + BALANCE
				+ ", FORMS_TEXT=" + FORMS_TEXT + ", PAYMENT_TERMS1="
				+ PAYMENT_TERMS1 + ", PAYMENT_TERMS2=" + PAYMENT_TERMS2
				+ ", PAYMENT_TERMS3=" + PAYMENT_TERMS3 + ", PG_TEXT=" + PG_TEXT
				+ ", LIQUIDATE_DAMAGE_TEXT=" + LIQUIDATE_DAMAGE_TEXT
				+ ", CREATED_BY=" + CREATED_BY + ", CREATED_DATE="
				+ CREATED_DATE + ", MODIFIED_BY=" + MODIFIED_BY
				+ ", MODIFIED_DATE=" + MODIFIED_DATE + "]";
	}

}
