package com.lms.dao;

public interface LeadSequenceDao {
	
	public String getRunningSequence(String year,String projectType);
	
	public void updateSequenceNumber(String year,String projectType);
	public String getLeadNo(int year,String projectType,String shortState);
	public String getIndentNo(int year, String businessFile, String leadType,
			String shortState);
	

}
