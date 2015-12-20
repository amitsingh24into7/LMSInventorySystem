package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.LeadItemTechnicalDao;
import com.lms.model.LeadItemTechnicalDetails;
import com.lms.util.DbUtil;

public class LeadItemTechnicalDaoImpl implements LeadItemTechnicalDao {

	private Connection connection;

	public LeadItemTechnicalDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}

	public String addLeadItemtechicalDetails(LeadItemTechnicalDetails leadTechnical) {

		String message="";
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			
			String sql="insert into lead_item_technical_details(DTL_ID, LEAD_ID,"
					+ " BUSINESS_TYPE, FUEL_TANK, ENGINE_OPTIONAL, ALTERNATOR_OPTIONAL, PANEL_OPTIONAL,"
					+ " SOLOPARALLEL, TYPE_OF_PANEL, TESTING_PROCEDURE, DG_TESTING, TESTING_CHARGES_INCLUDED, "
					+ "CREATED_DATE, CREATED_BY) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println(sql);
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setInt(1,leadTechnical.getDTL_ID() );
			preparedStatement.setString(2,leadTechnical.getLEAD_ID());
			List<LeadItemTechnicalDetails> ls=getTechnicalDetails(leadTechnical.getLEAD_ID(),leadTechnical.getDTL_ID()+"");
			if(ls.size()>0)
			{
				leadTechnical.setMODIFIED_BY(leadTechnical.getCREATED_BY());
				leadTechnical.setMODIFIED_DATE(leadTechnical.getCREATED_DATE());
				updateLeadItemtechicalDetails(leadTechnical);
				message="Done";
			}
			else
			{
					
			preparedStatement.setString(3,leadTechnical.getBUSINESS_TYPE());
			preparedStatement.setString(4,leadTechnical.getFUEL_TANK());
			preparedStatement.setString(5,leadTechnical.getENGINE_OPTIONAL());
			preparedStatement.setString(6,leadTechnical.getALTERNATOR_OPTIONAL());
			preparedStatement.setString(7,leadTechnical.getPANEL_OPTIONAL());
			preparedStatement.setString(8,leadTechnical.getSOLOPARALLEL());
			preparedStatement.setString(9,leadTechnical.getTYPE_OF_PANEL());
			preparedStatement.setString(10,leadTechnical.getTESTING_PROCEDURE());
			preparedStatement.setString(11,leadTechnical.getDG_TESTING());
			preparedStatement.setString(12,leadTechnical.getTESTING_CHARGES_INCLUDED());
			preparedStatement.setString(13, leadTechnical.getCREATED_DATE());
			preparedStatement.setString(14, leadTechnical.getCREATED_BY());
			System.out.println(preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			message="Done";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		return message;
	}

	public String updateLeadItemtechicalDetails(LeadItemTechnicalDetails leadTechnical) {

		String message="";
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			
			String sql="update lead_item_technical_details set "
					+ " BUSINESS_TYPE=?, FUEL_TANK=?, ENGINE_OPTIONAL=?, ALTERNATOR_OPTIONAL=?, PANEL_OPTIONAL=?,"
					+ " SOLOPARALLEL=?, TYPE_OF_PANEL=?, TESTING_PROCEDURE=?, DG_TESTING=?, TESTING_CHARGES_INCLUDED=?, "
					+ " MODIFIED_DATE=?, MODIFIED_BY=?  where DTL_ID=? and  LEAD_ID=?";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			
			preparedStatement.setString(1,leadTechnical.getBUSINESS_TYPE());
			preparedStatement.setString(2,leadTechnical.getFUEL_TANK());
			preparedStatement.setString(3,leadTechnical.getENGINE_OPTIONAL());
			preparedStatement.setString(4,leadTechnical.getALTERNATOR_OPTIONAL());
			preparedStatement.setString(5,leadTechnical.getPANEL_OPTIONAL());
			preparedStatement.setString(6,leadTechnical.getSOLOPARALLEL());
			preparedStatement.setString(7,leadTechnical.getTYPE_OF_PANEL());
			preparedStatement.setString(8,leadTechnical.getTESTING_PROCEDURE());
			preparedStatement.setString(9,leadTechnical.getDG_TESTING());
			preparedStatement.setString(10,leadTechnical.getTESTING_CHARGES_INCLUDED());
			
			preparedStatement.setString(11, leadTechnical.getMODIFIED_DATE());
			preparedStatement.setString(12, leadTechnical.getMODIFIED_BY());
			
			preparedStatement.setInt(13,leadTechnical.getDTL_ID() );
			preparedStatement.setString(14,leadTechnical.getLEAD_ID());
			
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			message="Done";

		} catch (SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		return message;
	}

	public List<LeadItemTechnicalDetails> getTechnicalDetails(String leadID,
			String itemId) {

		List<LeadItemTechnicalDetails> itemTechnicals = new ArrayList<LeadItemTechnicalDetails>();
		try {
			Statement statement = connection.createStatement();
			String sql="select ID, DTL_ID, LEAD_ID, BUSINESS_TYPE, FUEL_TANK, ENGINE_OPTIONAL, ALTERNATOR_OPTIONAL,"
					+ " PANEL_OPTIONAL, SOLOPARALLEL, TYPE_OF_PANEL, TESTING_PROCEDURE, DG_TESTING,"
					+ " TESTING_CHARGES_INCLUDED,cast(CREATED_DATE as date) as CREATED_DATE,"
					+ " CREATED_BY, MODIFIED_BY,cast(MODIFIED_DATE as date) as MODIFIED_DATE from lead_item_technical_details	 where LEAD_ID='"
					+ leadID + "' and DTL_ID='"+itemId+"' order by CREATED_DATE desc";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				LeadItemTechnicalDetails technicalInfo = new LeadItemTechnicalDetails();
				technicalInfo.setID(rs.getInt("iD"));
				technicalInfo.setDTL_ID(rs.getInt("dTL_ID"));
				technicalInfo.setLEAD_ID(rs.getString("lEAD_ID"));
				technicalInfo.setBUSINESS_TYPE(rs.getString("bUSINESS_TYPE"));
				technicalInfo.setFUEL_TANK(rs.getString("fUEL_TANK"));
				technicalInfo.setENGINE_OPTIONAL(rs.getString("eNGINE_OPTIONAL"));
				technicalInfo.setALTERNATOR_OPTIONAL(rs.getString("aLTERNATOR_OPTIONAL"));
				technicalInfo.setPANEL_OPTIONAL(rs.getString("pANEL_OPTIONAL"));
				technicalInfo.setSOLOPARALLEL(rs.getString("sOLOPARALLEL"));
				technicalInfo.setTYPE_OF_PANEL(rs.getString("tYPE_OF_PANEL"));
				technicalInfo.setTESTING_PROCEDURE(rs.getString("tESTING_PROCEDURE"));
				technicalInfo.setDG_TESTING(rs.getString("dG_TESTING"));
				technicalInfo.setTESTING_CHARGES_INCLUDED(rs.getString("tESTING_CHARGES_INCLUDED"));
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

	public List<LeadItemTechnicalDetails> getTechnicalInformation(String leadID) {

		List<LeadItemTechnicalDetails> itemTechnicals = new ArrayList<LeadItemTechnicalDetails>();
		try {
			Statement statement = connection.createStatement();
			String sql="select ID, DTL_ID, LEAD_ID, BUSINESS_TYPE, FUEL_TANK, ENGINE_OPTIONAL, ALTERNATOR_OPTIONAL,"
					+ " PANEL_OPTIONAL, SOLOPARALLEL, TYPE_OF_PANEL, TESTING_PROCEDURE, DG_TESTING,"
					+ " TESTING_CHARGES_INCLUDED,cast(CREATED_DATE as date) as CREATED_DATE,"
					+ " CREATED_BY, MODIFIED_BY,cast(MODIFIED_DATE as date) as MODIFIED_DATE from lead_item_technical_details	 where LEAD_ID='"
					+ leadID + "' order by CREATED_DATE desc";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				LeadItemTechnicalDetails technicalInfo = new LeadItemTechnicalDetails();
				technicalInfo.setID(rs.getInt("iD"));
				technicalInfo.setDTL_ID(rs.getInt("dTL_ID"));
				technicalInfo.setLEAD_ID(rs.getString("lEAD_ID"));
				technicalInfo.setBUSINESS_TYPE(rs.getString("bUSINESS_TYPE"));
				technicalInfo.setFUEL_TANK(rs.getString("fUEL_TANK"));
				technicalInfo.setENGINE_OPTIONAL(rs.getString("eNGINE_OPTIONAL"));
				technicalInfo.setALTERNATOR_OPTIONAL(rs.getString("aLTERNATOR_OPTIONAL"));
				technicalInfo.setPANEL_OPTIONAL(rs.getString("pANEL_OPTIONAL"));
				technicalInfo.setSOLOPARALLEL(rs.getString("sOLOPARALLEL"));
				technicalInfo.setTYPE_OF_PANEL(rs.getString("tYPE_OF_PANEL"));
				technicalInfo.setTESTING_PROCEDURE(rs.getString("tESTING_PROCEDURE"));
				technicalInfo.setDG_TESTING(rs.getString("dG_TESTING"));
				technicalInfo.setTESTING_CHARGES_INCLUDED(rs.getString("tESTING_CHARGES_INCLUDED"));
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
