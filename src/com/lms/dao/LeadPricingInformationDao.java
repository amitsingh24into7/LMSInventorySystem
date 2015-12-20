package com.lms.dao;

import java.util.List;

import com.lms.model.Lead_Pricing;

public interface LeadPricingInformationDao {
	public String addLeadPricingInfo(Lead_Pricing leadPricing);
	public String updateLeadPricingInfo(Lead_Pricing leadPricing);
	public List<Lead_Pricing> getLeadPricingInfo(String leadId,String itemType);
	public List<Lead_Pricing> getLeadPricingInfoByName(String leadId,String itemType,String Name);

}
