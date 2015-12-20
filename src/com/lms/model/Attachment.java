package com.lms.model;

public class Attachment {

	
	private String DOC_NAME;
	private String DOC_URL;
	private String DOC_TYPE;
	private String DOC_CONTENT_TYPE;
	private String DOC_VERSION;
	private String LEAD_ID;
	private String DOC_CREATED_BY;
	private String DOC_CREATED_DATE;
	
	public String getDOC_NAME() {
		return DOC_NAME;
	}
	public void setDOC_NAME(String dOC_NAME) {
		DOC_NAME = dOC_NAME;
	}
	public String getDOC_URL() {
		return DOC_URL;
	}
	public void setDOC_URL(String dOC_URL) {
		DOC_URL = dOC_URL;
	}
	public String getDOC_TYPE() {
		return DOC_TYPE;
	}
	public void setDOC_TYPE(String dOC_TYPE) {
		DOC_TYPE = dOC_TYPE;
	}
	public String getDOC_CONTENT_TYPE() {
		return DOC_CONTENT_TYPE;
	}
	public void setDOC_CONTENT_TYPE(String dOC_CONTENT_TYPE) {
		DOC_CONTENT_TYPE = dOC_CONTENT_TYPE;
	}
	public String getDOC_VERSION() {
		return DOC_VERSION;
	}
	public void setDOC_VERSION(String dOC_VERSION) {
		DOC_VERSION = dOC_VERSION;
	}
	public String getLEAD_ID() {
		return LEAD_ID;
	}
	public void setLEAD_ID(String lEAD_ID) {
		LEAD_ID = lEAD_ID;
	}
	public String getDOC_CREATED_BY() {
		return DOC_CREATED_BY;
	}
	public void setDOC_CREATED_BY(String dOC_CREATED_BY) {
		DOC_CREATED_BY = dOC_CREATED_BY;
	}
	public String getDOC_CREATED_DATE() {
		return DOC_CREATED_DATE;
	}
	public void setDOC_CREATED_DATE(String dOC_CREATED_DATE) {
		DOC_CREATED_DATE = dOC_CREATED_DATE;
	}
	@Override
	public String toString() {
		return "Attachment [ DOC_NAME=" + DOC_NAME
				+ ", DOC_URL=" + DOC_URL + ", DOC_TYPE=" + DOC_TYPE
				+ ", DOC_CONTENT_TYPE=" + DOC_CONTENT_TYPE + ", DOC_VERSION="
				+ DOC_VERSION + ", LEAD_ID=" + LEAD_ID + ", DOC_CREATED_BY="
				+ DOC_CREATED_BY + ", DOC_CREATED_DATE=" + DOC_CREATED_DATE
				+ "]";
	}
	

}
