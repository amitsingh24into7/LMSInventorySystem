package com.lms.dao;

import java.util.List;

import com.lms.model.Customer;

public interface CustomerDao {
	public List<Customer> getCustomerDetails();
	public void addCustomer(Customer customer);
	public void addMasterCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public List<Customer> getCustomerDetailsByLeadID(String leadID);
	public List<Customer> getCustomerDetailsByCustomerName(String CUSTOMER_NAME);
	
	public List<Customer> getCustomerNames(String CUSTOMER_NAME);
	public void updateCustomerLeadID(String budleadID,String leadID,String createdDate,String createdBy);
	public List<Customer> getAllCustomerNames();
	
	
	
	
	
	


}
