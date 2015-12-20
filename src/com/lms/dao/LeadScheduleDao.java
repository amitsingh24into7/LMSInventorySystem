package com.lms.dao;

import java.util.List;



import com.lms.model.LeadSchedule;

public interface LeadScheduleDao {
	public void addLeadSchedule(LeadSchedule attachments);
	
	public void updateLeadSchedule(LeadSchedule leadSchedule);
	
	public List<LeadSchedule> getScheudleByLeadID(String leadID);
	public List<LeadSchedule> getScheudleByItem(String leadID,int itemID);
	public String getQty(String leadID,int itemID);
	public void updateLeadScheduleLeadID(String budleadID,String leadID,String createdDate,String createdBy);
}
