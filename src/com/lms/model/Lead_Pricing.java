package com.lms.model;

public class Lead_Pricing {

	private String LEAD_ID;
	private String  ITEMTYPE;
	private String  NAME;
	private String  VALUE;
	private String  CREATED_DATE;
	private String  CREATED_BY;
	private String MODIFIED_DATE;
	private String MODIFIED_BY;
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
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String vALUE) {
		VALUE = vALUE;
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
	@Override
	public String toString() {
		return "Lead_Pricing [LEAD_ID=" + LEAD_ID + ", ITEMTYPE=" + ITEMTYPE
				+ ", NAME=" + NAME + ", VALUE=" + VALUE + ", CREATED_DATE="
				+ CREATED_DATE + ", CREATED_BY=" + CREATED_BY
				+ ", MODIFIED_DATE=" + MODIFIED_DATE + ", MODIFIED_BY="
				+ MODIFIED_BY + "]";
	}
	

}
