package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.ConsultantDao;
import com.lms.model.ConsultantMaster;
import com.lms.util.DbUtil;

public class ConsultantDaoImpl implements ConsultantDao {

	private Connection connection;
	public ConsultantDaoImpl()
	{
		connection = DbUtil.getDBConnection();
	}
	public List<ConsultantMaster> getConsultantDetailsByName(String cName) {
		List<ConsultantMaster> cMasters = new ArrayList<ConsultantMaster>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from consultant_master where STATUS='Active' and CONSULATNT_NAME='"+cName+"' ORDER BY CRETAED_DATE desc");
			while (rs.next()) {
				ConsultantMaster cMaster = new ConsultantMaster();
				cMaster.setCONSULATNT_NAME(rs.getString("cONSULATNT_NAME"));
				cMaster.setCONSULATNT_CONTACT_PERSON(rs.getString("cONSULATNT_CONTACT_PERSON"));
				cMaster.setCONSULATNT_CONTACT_MOBILE(rs.getString("cONSULATNT_CONTACT_MOBILE"));
				cMaster.setCONSULATNT_EMAIL(rs.getString("cONSULATNT_EMAIL"));
				cMaster.setCONSULATNT_ALTERNATE_MOBILE(rs.getString("cONSULATNT_ALTERNATE_MOBILE"));
				cMaster.setCRETAED_DATE(rs.getString("cRETAED_DATE"));
				cMaster.setCREATED_BY(rs.getString("cREATED_BY"));
				
				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}

	public List<ConsultantMaster> getConsultantName() {
		List<ConsultantMaster> cMasters = new ArrayList<ConsultantMaster>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from consultant_master where STATUS='Active'  ORDER BY CRETAED_DATE desc");
			while (rs.next()) {
				ConsultantMaster cMaster = new ConsultantMaster();
				cMaster.setCONSULATNT_NAME(rs.getString("cONSULATNT_NAME"));
				
				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	public String addConsultantDetails(ConsultantMaster consultant) {
		
			String status="";
			try {
				// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into consultant_master("
								+ "CONSULATNT_NAME, CONSULATNT_CONTACT_PERSON, CONSULATNT_CONTACT_MOBILE, CONSULATNT_EMAIL, CONSULATNT_ALTERNATE_MOBILE, CREATED_BY, CRETAED_DATE)"
								+ " values (?, ?, ?, ?,?, ?, ?)");

				preparedStatement.setString(1, consultant.getCONSULATNT_NAME());
				preparedStatement.setString(2, consultant.getCONSULATNT_CONTACT_PERSON());

				preparedStatement.setString(3, consultant.getCONSULATNT_CONTACT_MOBILE());
				preparedStatement.setString(4,  consultant.getCONSULATNT_EMAIL());
				preparedStatement.setString(5, consultant.getCONSULATNT_ALTERNATE_MOBILE());
				preparedStatement.setString(6, consultant.getCREATED_BY());
				preparedStatement.setString(7, consultant.getCRETAED_DATE());
				
				
				preparedStatement.executeUpdate();
				status="Done";

			} catch (SQLException e) {
				e.printStackTrace();
				status=e.getMessage();
			}	
			return status;
		
	}

}
