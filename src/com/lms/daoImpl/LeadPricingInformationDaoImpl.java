package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.LeadPricingInformationDao;
import com.lms.model.LeadItemTechnicalDetails;
import com.lms.model.Lead_Pricing;
import com.lms.util.DbUtil;

public class LeadPricingInformationDaoImpl implements LeadPricingInformationDao {

	
	private Connection connection;

	public LeadPricingInformationDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}

	

	public String addLeadPricingInfo(Lead_Pricing leadPricing) {
		
		String message="";
		
		List ls=getLeadPricingInfoByName(leadPricing.getLEAD_ID(),leadPricing.getITEMTYPE(),leadPricing.getNAME());
		if(ls.size()>0)
		{
			leadPricing.setMODIFIED_BY(leadPricing.getCREATED_BY());
			leadPricing.setMODIFIED_DATE(leadPricing.getCREATED_DATE());
			
			message=updateLeadPricingInfo(leadPricing);
		}
		else
		{
			try {
				// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
				
				String sql="insert into lead_pricing_information("
						+ " LEAD_ID, ITEMTYPE, NAME, VALUE, CREATED_DATE, CREATED_BY )"
						+ " values(?,?,?,?,?,?)";
				
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);
	
				preparedStatement.setString(1,leadPricing.getLEAD_ID());
				preparedStatement.setString(2,leadPricing.getITEMTYPE());
				preparedStatement.setString(3,leadPricing.getNAME());
				preparedStatement.setString(4,leadPricing.getVALUE());
				preparedStatement.setString(5,leadPricing.getCREATED_DATE());
				preparedStatement.setString(6,leadPricing.getCREATED_BY());
				
				
				preparedStatement.executeUpdate();
				message="Done";
	
			} catch (SQLException e) {
				e.printStackTrace();
				message=e.getMessage();
			}
		}
		return message;
	}

	public String updateLeadPricingInfo(Lead_Pricing leadPricing) {
		// TODO Auto-generated method stub
		String message="";
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			
			String sql="update lead_pricing_information set VALUE=?, MODIFIED_DATE=?, MODIFIED_BY=? "
					+ " where LEAD_ID=? and ITEMTYPE=? and NAME= ?";
					
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			
			preparedStatement.setString(1,leadPricing.getVALUE());
			preparedStatement.setString(2,leadPricing.getMODIFIED_DATE());
			preparedStatement.setString(3,leadPricing.getMODIFIED_BY());
			preparedStatement.setString(4,leadPricing.getLEAD_ID());
			preparedStatement.setString(5,leadPricing.getITEMTYPE());
			preparedStatement.setString(6,leadPricing.getNAME());
			
			
			preparedStatement.executeUpdate();
			message="Done";

		} catch (SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		return message;
	}

	public List<Lead_Pricing> getLeadPricingInfo(String leadId,
			String itemType) {
		List<Lead_Pricing> itemTechnicals = new ArrayList<Lead_Pricing>();
		try {
			Statement statement = connection.createStatement();
			String sql="select LEAD_ID, ITEMTYPE, NAME, VALUE, cast(CREATED_DATE as date) as CREATED_DATE, CREATED_BY, cast(MODIFIED_DATE as date) as MODIFIED_DATE, MODIFIED_BY from lead_pricing_information"
					+ " where LEAD_ID='"+leadId+"' AND ITEMTYPE='"+itemType+"'  order by CREATED_DATE desc";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				Lead_Pricing technicalInfo = new Lead_Pricing();
				technicalInfo.setLEAD_ID(rs.getString("lEAD_ID"));
				technicalInfo.setITEMTYPE(rs.getString("iTEMTYPE"));
				technicalInfo.setNAME(rs.getString("nAME"));
				technicalInfo.setVALUE(rs.getString("vALUE"));
				technicalInfo.setCREATED_BY(rs.getString("cREATED_BY"));
				technicalInfo.setCREATED_DATE(rs.getString("cREATED_DATE"));
				technicalInfo.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				technicalInfo.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));
				
				itemTechnicals.add(technicalInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itemTechnicals;
		
	}
	public List<Lead_Pricing> getLeadPricingInfoByName(String leadId,String itemType,String Name)
	{
		List<Lead_Pricing> itemTechnicals = new ArrayList<Lead_Pricing>();
		try {
			Statement statement = connection.createStatement();
			String sql="select LEAD_ID, ITEMTYPE, NAME, VALUE, cast(CREATED_DATE as date) as CREATED_DATE, CREATED_BY, cast(MODIFIED_DATE as date) as MODIFIED_DATE, MODIFIED_BY from lead_pricing_information"
					+ " where LEAD_ID='"+leadId+"' AND ITEMTYPE='"+itemType+"' AND NAME='"+Name+"'  order by CREATED_DATE desc";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				Lead_Pricing technicalInfo = new Lead_Pricing();
				technicalInfo.setLEAD_ID(rs.getString("lEAD_ID"));
				technicalInfo.setITEMTYPE(rs.getString("iTEMTYPE"));
				technicalInfo.setNAME(rs.getString("nAME"));
				technicalInfo.setVALUE(rs.getString("vALUE"));
				technicalInfo.setCREATED_BY(rs.getString("cREATED_BY"));
				technicalInfo.setCREATED_DATE(rs.getString("cREATED_DATE"));
				technicalInfo.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				technicalInfo.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));
				
				itemTechnicals.add(technicalInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itemTechnicals;
		
	}

}
