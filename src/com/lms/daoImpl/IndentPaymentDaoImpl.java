package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.IndentPaymentDao;
import com.lms.model.IndentPaymentDetails;
import com.lms.model.LeadItemTechnicalDetails;
import com.lms.util.DbUtil;

public class IndentPaymentDaoImpl implements IndentPaymentDao {

	private Connection connection;
	public IndentPaymentDaoImpl() {
		// TODO Auto-generated constructor stub
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
		
	}

	public String addIndentPaymentDetails(IndentPaymentDetails indentPayment) {
		String message="";
		
		List ls=getIndentPaymentDetailsByType(indentPayment.getLEAD_ID(),indentPayment.getITEMTYPE());
		if(ls.size()>0)
		{
			indentPayment.setMODIFIED_BY(indentPayment.getCREATED_BY());
			indentPayment.setMODIFIED_DATE(indentPayment.getCREATED_DATE());
			message=updateIndentPaymentDetails(indentPayment);
			
		}
		else
		{
		
			
			try {
				// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
				
				String sql="insert into indent_payment_details(LEAD_ID, ITEMTYPE, ADVANCE, CHEQUE_NO, CHEQUE_DATE, "
						+ "BANK_DETAILS, BALANCE, FORMS_TEXT, PAYMENT_TERMS1, PAYMENT_TERMS2,"
						+ " PAYMENT_TERMS3, PG_TEXT, LIQUIDATE_DAMAGE_TEXT, CREATED_BY,"
						+ " CREATED_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				System.out.println(sql);
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);
	
				preparedStatement.setString(1,indentPayment.getLEAD_ID());
				preparedStatement.setString(2,indentPayment.getITEMTYPE());
				preparedStatement.setString(3,indentPayment.getADVANCE());
				preparedStatement.setString(4,indentPayment.getCHEQUE_NO());
				preparedStatement.setString(5,indentPayment.getCHEQUE_DATE());
				preparedStatement.setString(6,indentPayment.getBANK_DETAILS());
				preparedStatement.setString(7,indentPayment.getBALANCE());
				preparedStatement.setString(8,indentPayment.getFORMS_TEXT());
				preparedStatement.setString(9,indentPayment.getPAYMENT_TERMS1());
				preparedStatement.setString(10,indentPayment.getPAYMENT_TERMS2());
				preparedStatement.setString(11,indentPayment.getPAYMENT_TERMS3());
				preparedStatement.setString(12,indentPayment.getPG_TEXT());
				preparedStatement.setString(13,indentPayment.getLIQUIDATE_DAMAGE_TEXT());
				preparedStatement.setString(14,indentPayment.getCREATED_BY());
				preparedStatement.setString(15,indentPayment.getCREATED_DATE());
	
	
				
				preparedStatement.executeUpdate();
				message="Done";
	
			} catch (SQLException e) {
				e.printStackTrace();
				message=e.getMessage();
			}
		}
		return message;
	}

	public String updateIndentPaymentDetails(IndentPaymentDetails indentPayment) {
		// TODO Auto-generated method stub
		String message="";
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			
			String sql="update indent_payment_details set  ITEMTYPE=?, ADVANCE=?, CHEQUE_NO=?, CHEQUE_DATE=?, "
					+ "BANK_DETAILS=?, BALANCE=?, FORMS_TEXT=?, "
					+ "PAYMENT_TERMS1=?, PAYMENT_TERMS2=?, PAYMENT_TERMS3=?, PG_TEXT=?,"
					+ " LIQUIDATE_DAMAGE_TEXT=?,  MODIFIED_BY=?, MODIFIED_DATE=? where   LEAD_ID=?";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			
			preparedStatement.setString(1,indentPayment.getITEMTYPE());
			preparedStatement.setString(2,indentPayment.getADVANCE());
			preparedStatement.setString(3,indentPayment.getCHEQUE_NO());
			preparedStatement.setString(4,indentPayment.getCHEQUE_DATE());
			preparedStatement.setString(5,indentPayment.getBANK_DETAILS());
			preparedStatement.setString(6,indentPayment.getBALANCE());
			preparedStatement.setString(7,indentPayment.getFORMS_TEXT());
			preparedStatement.setString(8,indentPayment.getPAYMENT_TERMS1());
			preparedStatement.setString(9,indentPayment.getPAYMENT_TERMS2());
			preparedStatement.setString(10,indentPayment.getPAYMENT_TERMS3());
			preparedStatement.setString(11,indentPayment.getPG_TEXT());
			preparedStatement.setString(12,indentPayment.getLIQUIDATE_DAMAGE_TEXT());
			preparedStatement.setString(13,indentPayment.getCREATED_BY());
			preparedStatement.setString(14,indentPayment.getCREATED_DATE());
			preparedStatement.setString(15,indentPayment.getLEAD_ID());

			
			preparedStatement.executeUpdate();
			message="Done";

		} catch (SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		return message;
	}

	public List<IndentPaymentDetails> getIndentPaymentDetails(String leadID) {
		List<IndentPaymentDetails> itemTechnicals = new ArrayList<IndentPaymentDetails>();
		try {
			Statement statement = connection.createStatement();
			String sql="select ID, LEAD_ID, ITEMTYPE, ADVANCE, CHEQUE_NO, CHEQUE_DATE,"
					+ " BANK_DETAILS, BALANCE, FORMS_TEXT, PAYMENT_TERMS1, PAYMENT_TERMS2, "
					+ "PAYMENT_TERMS3, PG_TEXT, LIQUIDATE_DAMAGE_TEXT,"
					+ " CREATED_BY, cast(CREATED_DATE as date) as CREATED_DATE, "
					+ "MODIFIED_BY, cast(MODIFIED_DATE as date) as MODIFIED_DATE "
					+ "from  indent_payment_details	 where LEAD_ID='"
					+ leadID +"' order by CREATED_DATE desc";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				IndentPaymentDetails indentPayments = new IndentPaymentDetails();
				indentPayments.setID(rs.getInt("iD"));
				indentPayments.setLEAD_ID(rs.getString("lEAD_ID"));
				indentPayments.setITEMTYPE(rs.getString("iTEMTYPE"));
				indentPayments.setADVANCE(rs.getString("aDVANCE"));
				indentPayments.setCHEQUE_NO(rs.getString("cHEQUE_NO"));
				indentPayments.setCHEQUE_DATE(rs.getString("cHEQUE_DATE"));
				indentPayments.setBANK_DETAILS(rs.getString("bANK_DETAILS"));
				indentPayments.setBALANCE(rs.getString("bALANCE"));
				indentPayments.setFORMS_TEXT(rs.getString("fORMS_TEXT"));
				indentPayments.setPAYMENT_TERMS1(rs.getString("pAYMENT_TERMS1"));
				indentPayments.setPAYMENT_TERMS2(rs.getString("pAYMENT_TERMS2"));
				indentPayments.setPAYMENT_TERMS3(rs.getString("pAYMENT_TERMS3"));
				indentPayments.setPG_TEXT(rs.getString("pG_TEXT"));
				indentPayments.setLIQUIDATE_DAMAGE_TEXT(rs.getString("lIQUIDATE_DAMAGE_TEXT"));
				indentPayments.setCREATED_BY(rs.getString("cREATED_BY"));
				indentPayments.setCREATED_DATE(rs.getString("cREATED_DATE"));
				indentPayments.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				indentPayments.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));
				itemTechnicals.add(indentPayments);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itemTechnicals;
	}
	public List<IndentPaymentDetails> getIndentPaymentDetailsByType(String leadID,String type) {
		List<IndentPaymentDetails> itemTechnicals = new ArrayList<IndentPaymentDetails>();
		try {
			Statement statement = connection.createStatement();
			String sql="select ID, LEAD_ID, ITEMTYPE, ADVANCE, CHEQUE_NO, CHEQUE_DATE,"
					+ " BANK_DETAILS, BALANCE, FORMS_TEXT, PAYMENT_TERMS1, PAYMENT_TERMS2, "
					+ "PAYMENT_TERMS3, PG_TEXT, LIQUIDATE_DAMAGE_TEXT,"
					+ " CREATED_BY, cast(CREATED_DATE as date) as CREATED_DATE, "
					+ "MODIFIED_BY, cast(MODIFIED_DATE as date) as MODIFIED_DATE "
					+ "from  indent_payment_details	 where LEAD_ID='"
					+ leadID +"' and ITEMTYPE='"+type+"' order by CREATED_DATE desc";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				IndentPaymentDetails indentPayments = new IndentPaymentDetails();
				indentPayments.setID(rs.getInt("iD"));
				indentPayments.setLEAD_ID(rs.getString("lEAD_ID"));
				indentPayments.setITEMTYPE(rs.getString("iTEMTYPE"));
				indentPayments.setADVANCE(rs.getString("aDVANCE"));
				indentPayments.setCHEQUE_NO(rs.getString("cHEQUE_NO"));
				indentPayments.setCHEQUE_DATE(rs.getString("cHEQUE_DATE"));
				indentPayments.setBANK_DETAILS(rs.getString("bANK_DETAILS"));
				indentPayments.setBALANCE(rs.getString("bALANCE"));
				indentPayments.setFORMS_TEXT(rs.getString("fORMS_TEXT"));
				indentPayments.setPAYMENT_TERMS1(rs.getString("pAYMENT_TERMS1"));
				indentPayments.setPAYMENT_TERMS2(rs.getString("pAYMENT_TERMS2"));
				indentPayments.setPAYMENT_TERMS3(rs.getString("pAYMENT_TERMS3"));
				indentPayments.setPG_TEXT(rs.getString("pG_TEXT"));
				indentPayments.setLIQUIDATE_DAMAGE_TEXT(rs.getString("lIQUIDATE_DAMAGE_TEXT"));
				indentPayments.setCREATED_BY(rs.getString("cREATED_BY"));
				indentPayments.setCREATED_DATE(rs.getString("cREATED_DATE"));
				indentPayments.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				indentPayments.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));
				itemTechnicals.add(indentPayments);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itemTechnicals;
	}
}
