package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.PMCDao;
import com.lms.model.PmcMaster;
import com.lms.util.DbUtil;

public class PMCDaoImpl implements PMCDao {

	private Connection connection;
	public PMCDaoImpl()
	{
		connection = DbUtil.getDBConnection();
	}
	public List<PmcMaster> getPMCDetailsByName(String cName) {
		List<PmcMaster> pmcMasters = new ArrayList<PmcMaster>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from pmc_master where STATUS='Active' and PMC_NAME='"+cName+"' ORDER BY CRETAED_DATE desc");
			while (rs.next()) {
				PmcMaster pmcMaster = new PmcMaster();
				pmcMaster.setPMC_NAME(rs.getString("pMC_NAME"));
				pmcMaster.setPMC_CONTACT_PERSON(rs.getString("pMC_CONTACT_PERSON"));
				pmcMaster.setPMC_CONTACT_MOBILE(rs.getString("pMC_CONTACT_MOBILE"));
				pmcMaster.setPMC_EMAIL(rs.getString("pMC_EMAIL"));
				pmcMaster.setPMC_ALTERNATE_MOBILE(rs.getString("pMC_ALTERNATE_MOBILE"));
				pmcMaster.setCREATED_BY(rs.getString("cREATED_BY"));
				pmcMaster.setCRETAED_DATE(rs.getString("cRETAED_DATE"));
				
				pmcMasters.add(pmcMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pmcMasters;
	}

	public List<PmcMaster> getPMCName() {
		List<PmcMaster> pmcMasters = new ArrayList<PmcMaster>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select PMC_NAME from pmc_master where STATUS='Active'  ORDER BY CRETAED_DATE desc");
			while (rs.next()) {
				PmcMaster pmcMaster = new PmcMaster();
				pmcMaster.setPMC_NAME(rs.getString("pMC_NAME"));
				pmcMasters.add(pmcMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pmcMasters;
	}
	public String addPMCDetails(PmcMaster pmc) {
		String status="";
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into pmc_master("
							+ "PMC_NAME, PMC_CONTACT_PERSON, PMC_CONTACT_MOBILE, PMC_EMAIL, PMC_ALTERNATE_MOBILE, CREATED_BY, CRETAED_DATE)"
							+ " values (?, ?, ?, ?,?, ?, ?)");

			preparedStatement.setString(1, pmc.getPMC_NAME());
			preparedStatement.setString(2, pmc.getPMC_CONTACT_PERSON());

			preparedStatement.setString(3, pmc.getPMC_CONTACT_MOBILE());
			preparedStatement.setString(4, pmc.getPMC_EMAIL());
			preparedStatement.setString(5, pmc.getPMC_ALTERNATE_MOBILE());
			preparedStatement.setString(6, pmc.getCREATED_BY());
			preparedStatement.setString(7, pmc.getCRETAED_DATE());
			
			
			preparedStatement.executeUpdate();
			status="Done";

		} catch (SQLException e) {
			e.printStackTrace();
			status=e.getMessage();
		}	
		return status;
	}

}
