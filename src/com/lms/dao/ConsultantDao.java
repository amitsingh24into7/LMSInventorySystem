package com.lms.dao;

import java.util.List;

import com.lms.model.ConsultantMaster;


public interface ConsultantDao {
	public List<ConsultantMaster> getConsultantDetailsByName(String cName);
	public List<ConsultantMaster> getConsultantName();
	public String addConsultantDetails(ConsultantMaster consultant) ;

}
