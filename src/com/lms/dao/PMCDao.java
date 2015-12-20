package com.lms.dao;

import java.util.List;

import com.lms.model.PmcMaster;

public interface PMCDao {
	public List<PmcMaster> getPMCDetailsByName(String cName);
	public String addPMCDetails(PmcMaster pmc);
	public List<PmcMaster> getPMCName();
	

}
