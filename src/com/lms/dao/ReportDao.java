package com.lms.dao;

import java.util.List;

import com.lms.model.EngineStatus;
import com.lms.model.OrderBoard;
import com.lms.model.Project;
import com.lms.model.Quote;
import com.lms.model.ROrderLost;

public interface ReportDao {
	public List<Project> getProjectReport(String year);
	public List<OrderBoard> getOrderBoardDetails(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy);
	public List<Quote> getQuoteAnalysis(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy,String engine,String status);
	public List<ROrderLost> getLostOrders(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy);
	public List<EngineStatus> getEnquiryBank(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy,String project,String leadChance,String engine);

}
