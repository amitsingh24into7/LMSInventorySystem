package com.lms.dao;

import java.util.List;

import com.lms.model.LeadItemTechnicalDetails;

public interface LeadItemTechnicalDao {
	public String addLeadItemtechicalDetails(LeadItemTechnicalDetails leadTechnical);
	public String updateLeadItemtechicalDetails(LeadItemTechnicalDetails leadTechnical);
	public List<LeadItemTechnicalDetails> getTechnicalDetails(String Lead_ID,String itemId);
	public List<LeadItemTechnicalDetails> getTechnicalInformation(String leadID);

}
