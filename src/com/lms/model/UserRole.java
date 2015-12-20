package com.lms.model;

public class UserRole {

	
	private String USER_ID;
	private String USER_NAME;
	private String EMAIL_ID;
	private String ROLE_ID;
	private String ROLE_NAME;
	private String REGION_TYPE;
	private String BUSINESS_TYPE;
	private String EXE_STATE;
	private String EXE_STATE_SHORT;
	private String EXE_CITY;
	private String ROLE_STATUS;
	private String DEFAULT_ROLE;
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getEMAIL_ID() {
		return EMAIL_ID;
	}
	public void setEMAIL_ID(String eMAIL_ID) {
		EMAIL_ID = eMAIL_ID;
	}
	public String getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public String getROLE_NAME() {
		return ROLE_NAME;
	}
	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}
	public String getREGION_TYPE() {
		return REGION_TYPE;
	}
	public void setREGION_TYPE(String rEGION_TYPE) {
		REGION_TYPE = rEGION_TYPE;
	}
	public String getBUSINESS_TYPE() {
		return BUSINESS_TYPE;
	}
	public void setBUSINESS_TYPE(String bUSINESS_TYPE) {
		BUSINESS_TYPE = bUSINESS_TYPE;
	}
	public String getEXE_STATE() {
		return EXE_STATE;
	}
	public void setEXE_STATE(String eXE_STATE) {
		EXE_STATE = eXE_STATE;
	}
	public String getEXE_STATE_SHORT() {
		return EXE_STATE_SHORT;
	}
	public void setEXE_STATE_SHORT(String eXE_STATE_SHORT) {
		EXE_STATE_SHORT = eXE_STATE_SHORT;
	}
	public String getEXE_CITY() {
		return EXE_CITY;
	}
	public void setEXE_CITY(String eXE_CITY) {
		EXE_CITY = eXE_CITY;
	}
	public String getROLE_STATUS() {
		return ROLE_STATUS;
	}
	public void setROLE_STATUS(String rOLE_STATUS) {
		ROLE_STATUS = rOLE_STATUS;
	}
	public String getDEFAULT_ROLE() {
		return DEFAULT_ROLE;
	}
	public void setDEFAULT_ROLE(String dEFAULT_ROLE) {
		DEFAULT_ROLE = dEFAULT_ROLE;
	}
	@Override
	public String toString() {
		return "UserRole [USER_ID=" + USER_ID + ", USER_NAME=" + USER_NAME
				+ ", EMAIL_ID=" + EMAIL_ID + ", ROLE_ID=" + ROLE_ID
				+ ", ROLE_NAME=" + ROLE_NAME + ", REGION_TYPE=" + REGION_TYPE
				+ ", BUSINESS_TYPE=" + BUSINESS_TYPE + ", EXE_STATE="
				+ EXE_STATE + ", EXE_STATE_SHORT=" + EXE_STATE_SHORT
				+ ", EXE_CITY=" + EXE_CITY + ", ROLE_STATUS=" + ROLE_STATUS
				+ ", DEFAULT_ROLE=" + DEFAULT_ROLE + "]";
	}
	
	
}
