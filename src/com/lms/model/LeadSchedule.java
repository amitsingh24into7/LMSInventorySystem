package com.lms.model;

public class LeadSchedule {
	private int SCHD_ID;
	  private int DTL_ID;
	  private String LEAD_ID;
	  private String INDENT_NO;
	  private String SCHD_DELIVERY_DATE;
	  private String SCHD_PROJECT_TEAM;
	  private String SCHD_QTY;
	  private String SCH_CREATED_BY;
	  private String SCHD_CRETAED_DATE;
	  private String SCHD_MODIFIED_BY;
	  private String SCHD_MODIFIED_DATE ;
	  private String  SCHD_REMARKS;
	public int getSCHD_ID() {
		return SCHD_ID;
	}
	public void setSCHD_ID(int sCHD_ID) {
		SCHD_ID = sCHD_ID;
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
	public String getINDENT_NO() {
		return INDENT_NO;
	}
	public void setINDENT_NO(String iNDENT_NO) {
		INDENT_NO = iNDENT_NO;
	}
	public String getSCHD_DELIVERY_DATE() {
		return SCHD_DELIVERY_DATE;
	}
	public void setSCHD_DELIVERY_DATE(String sCHD_DELIVERY_DATE) {
		SCHD_DELIVERY_DATE = sCHD_DELIVERY_DATE;
	}
	public String getSCHD_PROJECT_TEAM() {
		return SCHD_PROJECT_TEAM;
	}
	public void setSCHD_PROJECT_TEAM(String sCHD_PROJECT_TEAM) {
		SCHD_PROJECT_TEAM = sCHD_PROJECT_TEAM;
	}
	public String getSCHD_QTY() {
		return SCHD_QTY;
	}
	public void setSCHD_QTY(String sCHD_QTY) {
		SCHD_QTY = sCHD_QTY;
	}
	public String getSCH_CREATED_BY() {
		return SCH_CREATED_BY;
	}
	public void setSCH_CREATED_BY(String sCH_CREATED_BY) {
		SCH_CREATED_BY = sCH_CREATED_BY;
	}
	public String getSCHD_CRETAED_DATE() {
		return SCHD_CRETAED_DATE;
	}
	public void setSCHD_CRETAED_DATE(String sCHD_CRETAED_DATE) {
		SCHD_CRETAED_DATE = sCHD_CRETAED_DATE;
	}
	public String getSCHD_MODIFIED_BY() {
		return SCHD_MODIFIED_BY;
	}
	public void setSCHD_MODIFIED_BY(String sCHD_MODIFIED_BY) {
		SCHD_MODIFIED_BY = sCHD_MODIFIED_BY;
	}
	public String getSCHD_MODIFIED_DATE() {
		return SCHD_MODIFIED_DATE;
	}
	public void setSCHD_MODIFIED_DATE(String sCHD_MODIFIED_DATE) {
		SCHD_MODIFIED_DATE = sCHD_MODIFIED_DATE;
	}
	public String getSCHD_REMARKS() {
		return SCHD_REMARKS;
	}
	public void setSCHD_REMARKS(String sCHD_REMARKS) {
		SCHD_REMARKS = sCHD_REMARKS;
	}
	@Override
	public String toString() {
		return "LeadSchedule [SCHD_ID=" + SCHD_ID + ", DTL_ID=" + DTL_ID
				+ ", LEAD_ID=" + LEAD_ID + ", INDENT_NO=" + INDENT_NO
				+ ", SCHD_DELIVERY_DATE=" + SCHD_DELIVERY_DATE
				+ ", SCHD_PROJECT_TEAM=" + SCHD_PROJECT_TEAM + ", SCHD_QTY="
				+ SCHD_QTY + ", SCH_CREATED_BY=" + SCH_CREATED_BY
				+ ", SCHD_CRETAED_DATE=" + SCHD_CRETAED_DATE
				+ ", SCHD_MODIFIED_BY=" + SCHD_MODIFIED_BY
				+ ", SCHD_MODIFIED_DATE=" + SCHD_MODIFIED_DATE
				+ ", SCHD_REMARKS=" + SCHD_REMARKS + "]";
	}
	

}
