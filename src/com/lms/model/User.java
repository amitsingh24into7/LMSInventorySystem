package com.lms.model;

import java.util.Date;

public class User {

	private String USER_ID;
	private String EMAIL_ID;
	private String USERNAME;
	private String PASSWORD;
	private String MOBILENO;
	private String ALETERNATENO;
	private String ADDRESS1;
	private String ADDRESS2;
	private String ADDRESS3;
	private String DEPARTMENT;
	private String USER_STATUS;
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getEMAIL_ID() {
		return EMAIL_ID;
	}
	public void setEMAIL_ID(String eMAIL_ID) {
		EMAIL_ID = eMAIL_ID;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getMOBILENO() {
		return MOBILENO;
	}
	public void setMOBILENO(String mOBILENO) {
		MOBILENO = mOBILENO;
	}
	public String getALETERNATENO() {
		return ALETERNATENO;
	}
	public void setALETERNATENO(String aLETERNATENO) {
		ALETERNATENO = aLETERNATENO;
	}
	public String getADDRESS1() {
		return ADDRESS1;
	}
	public void setADDRESS1(String aDDRESS1) {
		ADDRESS1 = aDDRESS1;
	}
	public String getADDRESS2() {
		return ADDRESS2;
	}
	public void setADDRESS2(String aDDRESS2) {
		ADDRESS2 = aDDRESS2;
	}
	public String getADDRESS3() {
		return ADDRESS3;
	}
	public void setADDRESS3(String aDDRESS3) {
		ADDRESS3 = aDDRESS3;
	}
	public String getDEPARTMENT() {
		return DEPARTMENT;
	}
	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}
	public String getUSER_STATUS() {
		return USER_STATUS;
	}
	public void setUSER_STATUS(String uSER_STATUS) {
		USER_STATUS = uSER_STATUS;
	}
	@Override
	public String toString() {
		return "User [USER_ID=" + USER_ID + ", EMAIL_ID=" + EMAIL_ID
				+ ", USERNAME=" + USERNAME + ", PASSWORD=" + PASSWORD
				+ ", MOBILENO=" + MOBILENO + ", ALETERNATENO=" + ALETERNATENO
				+ ", ADDRESS1=" + ADDRESS1 + ", ADDRESS2=" + ADDRESS2
				+ ", ADDRESS3=" + ADDRESS3 + ", DEPARTMENT=" + DEPARTMENT
				+ ", USER_STATUS=" + USER_STATUS + "]";
	}
	
	
}
