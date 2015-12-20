package com.lms.dao;

import java.util.List;

import com.lms.model.LeadItemDetails;



public interface LeadItemDetailsDao {
	public void addLeadItems(LeadItemDetails leadItems);
	public List<LeadItemDetails> getLeadItemsByLeadID(String leadID);
	public String getTotalLeadAmount(String leadID);
	
	public String deleteLeadItem(int id);
	public void updateLeadItemsLeadID(String budleadID,String leadID,String createdDate,String createdBy);
	public void updateLeadItems(LeadItemDetails leadItems);
	
	public List<String> getDistinctEngineNamefromLeaadDetails();

}
