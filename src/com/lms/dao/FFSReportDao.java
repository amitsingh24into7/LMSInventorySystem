package com.lms.dao;

import java.util.List;

import com.lms.model.EngineStock;
import com.lms.model.FFSReport;

public interface FFSReportDao {
	public String addFFSData(FFSReport engineStock);
	public List<FFSReport> getAllFFSReport(String projectType,String engineName);
	public String updateAllFFS(FFSReport engineStock);
	public String updatePendingandTotalStock();
	
	

}
