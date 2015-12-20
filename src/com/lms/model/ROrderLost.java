package com.lms.model;

public class ROrderLost {
	private String PROJECT_REFERENCE;
	private String DTL_ENGINE_MAKE;
	private String DTL_RATING;
	private String DTL_QTY;
	private String DTL_AMOUNT;
	private String COMPETITIONS;
	private String DETAILS;
	public String getPROJECT_REFERENCE() {
		return PROJECT_REFERENCE;
	}
	public void setPROJECT_REFERENCE(String pROJECT_REFERENCE) {
		PROJECT_REFERENCE = pROJECT_REFERENCE;
	}
	public String getDTL_ENGINE_MAKE() {
		return DTL_ENGINE_MAKE;
	}
	public void setDTL_ENGINE_MAKE(String dTL_ENGINE_MAKE) {
		DTL_ENGINE_MAKE = dTL_ENGINE_MAKE;
	}
	public String getDTL_RATING() {
		return DTL_RATING;
	}
	public void setDTL_RATING(String dTL_RATING) {
		DTL_RATING = dTL_RATING;
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
	public String getCOMPETITIONS() {
		return COMPETITIONS;
	}
	public void setCOMPETITIONS(String cOMPETITIONS) {
		COMPETITIONS = cOMPETITIONS;
	}
	public String getDETAILS() {
		return DETAILS;
	}
	public void setDETAILS(String dETAILS) {
		DETAILS = dETAILS;
	}
	@Override
	public String toString() {
		return "ROrderLost [PROJECT_REFERENCE=" + PROJECT_REFERENCE
				+ ", DTL_ENGINE_MAKE=" + DTL_ENGINE_MAKE + ", DTL_RATING="
				+ DTL_RATING + ", DTL_QTY=" + DTL_QTY + ", DTL_AMOUNT="
				+ DTL_AMOUNT + ", COMPETITIONS=" + COMPETITIONS + ", DETAILS="
				+ DETAILS + "]";
	}
	
	

}
