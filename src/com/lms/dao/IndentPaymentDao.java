package com.lms.dao;

import java.util.List;

import com.lms.model.IndentPaymentDetails;

public interface IndentPaymentDao {

	public String addIndentPaymentDetails(IndentPaymentDetails indentPayment);
	public String updateIndentPaymentDetails(IndentPaymentDetails indentPayment);
	public List<IndentPaymentDetails> getIndentPaymentDetails(String leadID);
	public List<IndentPaymentDetails> getIndentPaymentDetailsByType(String leadID,String type);
}
