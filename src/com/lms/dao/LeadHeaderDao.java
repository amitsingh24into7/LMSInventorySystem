package com.lms.dao;

import java.util.List;






import com.lms.model.LeadHeader;

public interface LeadHeaderDao {
	
	
	
	public List<LeadHeader> getLeadHeadDataDetails(String status,String from_date,String to_date,String shortState);
	public List<LeadHeader> getIndentDataDetails(String from_date,String to_date,String userState);
	public List<LeadHeader> getBudgetLeadHeadDataDetails(String from_date,String to_date,String userState);
	
	public List<LeadHeader> getLeadHeadData(String leadID);
	public List<LeadHeader> getLeadHeadDetailsData(String fromDate,String toDate,String region,String state,String city,String roleId,String createdBy);
	public List<LeadHeader> getBudgetLeadHeadDetailsData(String fromDate,String toDate,String region,String state,String city,String roleId,String createdBy);
	public List<LeadHeader> getIndentDetailsData(String fromDate,String toDate,String region,String state,String city,String roleId,String createdBy);
	public void addLeadHeader(LeadHeader lHeader);
	public void updateLeadHeader(LeadHeader lHeader);
	public void updateLeadCostDetails(LeadHeader lHeader);
	public List<LeadHeader> getLeadCostDetails(String leadID);
	public void updateLeadCustomerInformation(LeadHeader lHeader);
	public void updateLeadStatus(LeadHeader lHeader);
	
	
	public String getLeadCount(String userid,String role,String state,String city,String region,String status);
	
	public String getMonthLeadCount(String userid,String role,String state,String city,String region,String status,String start_date,String end_date);
	public String getMonthLeadAmount(String userid,String role,String state,String city,String region,String status,String start_date,String end_date);
	
	public List<String> getMonthWiseLeadCountTillCurrentMonth(String userid,String role,String state,String city,String region,String status,String start_month);
	
	public String getLeadAmount(String userid,String role,String state,String city,String region,String status);
	
	public List<String> getCompetitions(String leadID,String competition);
	
	public void updateIndent(LeadHeader lHeader);

	public void updateLeadHeadLeadID(String budleadID,String leadID,String createdDate,String createdBy);
	
	
	
}
