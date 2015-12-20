package com.lms.model;

public class LeadSequence {
	private String RUNNING_YEAR;
	private String PROJECT_TYPE;
	private String SEQUENCE_NO;
	public String getRUNNING_YEAR() {
		return RUNNING_YEAR;
	}
	public void setRUNNING_YEAR(String rUNNING_YEAR) {
		RUNNING_YEAR = rUNNING_YEAR;
	}
	public String getPROJECT_TYPE() {
		return PROJECT_TYPE;
	}
	public void setPROJECT_TYPE(String pROJECT_TYPE) {
		PROJECT_TYPE = pROJECT_TYPE;
	}
	public String getSEQUENCE_NO() {
		return SEQUENCE_NO;
	}
	public void setSEQUENCE_NO(String sEQUENCE_NO) {
		SEQUENCE_NO = sEQUENCE_NO;
	}
	@Override
	public String toString() {
		return "LEAD_SEQUENCE [RUNNING_YEAR=" + RUNNING_YEAR
				+ ", PROJECT_TYPE=" + PROJECT_TYPE + ", SEQUENCE_NO="
				+ SEQUENCE_NO + "]";
	}
	

}
