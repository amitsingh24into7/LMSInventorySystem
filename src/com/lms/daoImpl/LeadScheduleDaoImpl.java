package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.LeadScheduleDao;
import com.lms.model.LeadSchedule;
import com.lms.util.DbUtil;

public class LeadScheduleDaoImpl implements LeadScheduleDao {

	private Connection connection;

	public LeadScheduleDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}

	public void addLeadSchedule(LeadSchedule leadSchedule) {
		// TODO Auto-generated method stub
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into LEAD_SCHEDULE("
							+ "DTL_ID, LEAD_ID, SCHD_DELIVERY_DATE, SCHD_PROJECT_TEAM, SCHD_QTY, SCH_CREATED_BY, SCHD_CRETAED_DATE,SCHD_REMARKS)"
							+ " values (?, ?, ?, ?,?, ?, ?,?)");

			preparedStatement.setInt(1, leadSchedule.getDTL_ID());
			preparedStatement.setString(2, leadSchedule.getLEAD_ID());
			preparedStatement
					.setString(3, leadSchedule.getSCHD_DELIVERY_DATE());
			preparedStatement.setString(4, leadSchedule.getSCHD_PROJECT_TEAM());
			preparedStatement.setString(5, leadSchedule.getSCHD_QTY());
			preparedStatement.setString(6, leadSchedule.getSCH_CREATED_BY());
			preparedStatement.setString(7, leadSchedule.getSCHD_CRETAED_DATE());
			preparedStatement.setString(8, leadSchedule.getSCHD_REMARKS());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateIndentNo(int schdid, String indentNo, String userName,
			String CurDate) {
		// TODO Auto-generated method stub
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("update LEAD_SCHEDULE set INDENT_NO=?,SCHD_MODIFIED_BY=?,SCHD_MODIFIED_DATE=? where SCHD_ID=?");

			preparedStatement.setString(1, indentNo);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, CurDate);
			preparedStatement.setInt(4, schdid);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<LeadSchedule> getScheudleByLeadID(String leadID) {
		List<LeadSchedule> leadSchedules = new ArrayList<LeadSchedule>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select SCHD_ID, DTL_ID, LEAD_ID, INDENT_NO, SCHD_DELIVERY_DATE, SCHD_PROJECT_TEAM, SCHD_QTY, SCHD_REMARKS, SCH_CREATED_BY, SCHD_CRETAED_DATE, SCHD_MODIFIED_BY, SCHD_MODIFIED_DATE from lead_schedule where LEAD_ID='"
							+ leadID + "' order by SCHD_CRETAED_DATE desc");
			while (rs.next()) {
				LeadSchedule leadSchd = new LeadSchedule();
				leadSchd.setDTL_ID(rs.getInt("dTL_ID"));
				leadSchd.setLEAD_ID(rs.getString("lEAD_ID"));
				leadSchd.setINDENT_NO(rs.getString("iNDENT_NO"));
				leadSchd.setSCHD_DELIVERY_DATE(rs
						.getString("sCHD_DELIVERY_DATE"));
				leadSchd.setSCHD_PROJECT_TEAM(rs.getString("sCHD_PROJECT_TEAM"));
				leadSchd.setSCHD_QTY(rs.getString("sCHD_QTY"));
				leadSchd.setSCH_CREATED_BY(rs.getString("sCH_CREATED_BY"));
				leadSchd.setSCHD_CRETAED_DATE(rs.getString("sCHD_CRETAED_DATE"));
				leadSchd.setSCHD_REMARKS(rs.getString("sCHD_REMARKS"));

				leadSchedules.add(leadSchd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return leadSchedules;
	}

	public List<LeadSchedule> getScheudleByItem(String leadID, int itemID) {
		List<LeadSchedule> leadSchedules = new ArrayList<LeadSchedule>();
		try {
			Statement statement = connection.createStatement();
			String sql="select SCHD_ID, DTL_ID, LEAD_ID, INDENT_NO, SCHD_DELIVERY_DATE, SCHD_PROJECT_TEAM, SCHD_QTY, SCHD_REMARKS, SCH_CREATED_BY, cast(SCHD_CRETAED_DATE as date) as SCHD_CRETAED_DATE, SCHD_MODIFIED_BY, SCHD_MODIFIED_DATE from lead_schedule where LEAD_ID='"
					+ leadID + "' and DTL_ID='" + itemID + "' order by SCHD_CRETAED_DATE desc";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				LeadSchedule leadSchd = new LeadSchedule();
				leadSchd.setSCHD_ID(rs.getInt("sCHD_ID"));
				leadSchd.setDTL_ID(rs.getInt("dTL_ID"));
				leadSchd.setLEAD_ID(rs.getString("lEAD_ID"));
				leadSchd.setINDENT_NO(rs.getString("iNDENT_NO"));
				leadSchd.setSCHD_DELIVERY_DATE(rs
						.getString("sCHD_DELIVERY_DATE"));
				leadSchd.setSCHD_PROJECT_TEAM(rs.getString("sCHD_PROJECT_TEAM"));
				leadSchd.setSCHD_REMARKS(rs.getString("sCHD_REMARKS"));
				
				leadSchd.setSCHD_QTY(rs.getString("sCHD_QTY"));
				leadSchd.setSCH_CREATED_BY(rs.getString("sCH_CREATED_BY"));
				leadSchd.setSCHD_CRETAED_DATE(rs.getString("sCHD_CRETAED_DATE"));

				leadSchedules.add(leadSchd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return leadSchedules;
	}
	
	public String getQty(String leadID,int itemID)
	{
		String qty="";
		try {
			Statement statement = connection.createStatement();
			String sql="select COALESCE(SUM(SCHD_QTY),0) as addedQty from lead_schedule where LEAD_ID='"
					+ leadID + "' and DTL_ID='" + itemID + "'";
			System.out.println(sql);
			
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				
				qty=rs.getString("addedQty");
							}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qty;
	}

	public void updateLeadScheduleLeadID(String budleadID,String leadID,String createdDate,String createdBy) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("update LEAD_SCHEDULE set LEAD_ID=?,SCHD_MODIFIED_BY=?,SCHD_MODIFIED_DATE=? where LEAD_ID=?");

			preparedStatement.setString(1, leadID);
			preparedStatement.setString(2, createdBy);
			preparedStatement.setString(3, createdDate);
			preparedStatement.setString(4, budleadID);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateLeadSchedule(LeadSchedule leadSchedule) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			String sql="update LEAD_SCHEDULE set SCHD_DELIVERY_DATE=?,SCHD_QTY=?,"
					+ "SCHD_REMARKS=?,SCHD_MODIFIED_BY=?,SCHD_MODIFIED_DATE=? where SCHD_ID=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1, leadSchedule.getSCHD_DELIVERY_DATE());
			preparedStatement.setString(2, leadSchedule.getSCHD_QTY());
			preparedStatement.setString(3, leadSchedule.getSCHD_REMARKS());
			preparedStatement.setString(4, leadSchedule.getSCHD_MODIFIED_BY());
			preparedStatement.setString(5, leadSchedule.getSCHD_MODIFIED_DATE());
			preparedStatement.setInt(6, leadSchedule.getSCHD_ID());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
