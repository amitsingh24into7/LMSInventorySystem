package com.lms.model;

public class PmcMaster {
	private String PMC_NAME;
	private String PMC_CONTACT_PERSON;
	private String PMC_CONTACT_MOBILE;
	private String PMC_EMAIL;
	private String PMC_ALTERNATE_MOBILE;
	private String CREATED_BY;
	private String CRETAED_DATE;
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
	public String getPMC_CONTACT_MOBILE() {
		return PMC_CONTACT_MOBILE;
	}
	public void setPMC_CONTACT_MOBILE(String pMC_CONTACT_MOBILE) {
		PMC_CONTACT_MOBILE = pMC_CONTACT_MOBILE;
	}
	public String getPMC_EMAIL() {
		return PMC_EMAIL;
	}
	public void setPMC_EMAIL(String pMC_EMAIL) {
		PMC_EMAIL = pMC_EMAIL;
	}
	public String getPMC_ALTERNATE_MOBILE() {
		return PMC_ALTERNATE_MOBILE;
	}
	public void setPMC_ALTERNATE_MOBILE(String pMC_ALTERNATE_MOBILE) {
		PMC_ALTERNATE_MOBILE = pMC_ALTERNATE_MOBILE;
	}
	public String getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}
	public String getCRETAED_DATE() {
		return CRETAED_DATE;
	}
	public void setCRETAED_DATE(String cRETAED_DATE) {
		CRETAED_DATE = cRETAED_DATE;
	}
	@Override
	public String toString() {
		return "PmcMaster [PMC_NAME=" + PMC_NAME + ", PMC_CONTACT_PERSON="
				+ PMC_CONTACT_PERSON + ", PMC_CONTACT_MOBILE="
				+ PMC_CONTACT_MOBILE + ", PMC_EMAIL=" + PMC_EMAIL
				+ ", PMC_ALTERNATE_MOBILE=" + PMC_ALTERNATE_MOBILE
				+ ", CREATED_BY=" + CREATED_BY + ", CRETAED_DATE="
				+ CRETAED_DATE + "]";
	}
	

}
