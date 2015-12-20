package com.lms.dao;

import java.util.List;

import com.lms.model.LeadItemTechnicalDetails;
import com.lms.model.SupplyScope;

public interface SupplyScopeDao {
	public String addSupplyScopeDetails(SupplyScope supplyScope);
	public String updateSupplyScopeDetails(SupplyScope leadTechnical);
	public List<SupplyScope> getSupplyScopeDetails(String leadID);
}
