package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lms.dao.LeadSequenceDao;
import com.lms.util.DbUtil;

public class LeadSequenceDaoImpl implements LeadSequenceDao {

	private Connection connection;

	public LeadSequenceDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}

	public String getLeadNo(int year, String projectType, String shortState) {
		String leadId = "";
		String runningSequence = getRunningSequence(year + "", projectType);
		updateSequenceNumber(year + "", projectType);
		leadId = (year % 100) + projectType +shortState+ runningSequence;
		return leadId;
	}

	public String getIndentNo(int year, String businessFile, String leadType,
			String shortState) {

		String indentId = "";
		String runningSequence = getRunningSequence(year + "", "IN"
				+ businessFile);
		updateSequenceNumber(year + "", "IN" + businessFile);
		indentId = (year % 100) +businessFile+leadType+ shortState 
				+ runningSequence;
		return indentId;
	}

	public String getRunningSequence(String year, String projectType) {
		String runningSequence = "";
		try {
			Statement statement = connection.createStatement();
			String sql="select SEQUENCE_NO from lead_sequence where RUNNING_YEAR='"
					+ year + "' and PROJECT_TYPE='" + projectType + "'";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				runningSequence = rs.getString("SEQUENCE_NO");

			}
		} catch (SQLException e) {
			runningSequence = e.getMessage();
			e.printStackTrace();
		}
		return runningSequence;
	}

	public void updateSequenceNumber(String year, String projectType) {
		// TODO Auto-generated method stub
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID

			String runningSeq = getRunningSequence(year, projectType);
			System.out.println("Running"+runningSeq);
			int rn = Integer.parseInt(runningSeq);
			rn = rn + 1;
			runningSeq = rn + "";

			if (runningSeq.length() == 1) {
				runningSeq = "000" + runningSeq;
			} else if (runningSeq.length() == 2) {
				runningSeq = "00" + runningSeq;
			} else if (runningSeq.length() == 3) {
				runningSeq = "0" + runningSeq;
			} else {
				runningSeq = runningSeq;
			}
			System.out.println("Hello" + runningSeq);
			PreparedStatement preparedStatement = connection
					.prepareStatement("update lead_sequence set SEQUENCE_NO=? where RUNNING_YEAR=? and PROJECT_TYPE=?");

			preparedStatement.setString(1, runningSeq);
			preparedStatement.setString(2, year);
			preparedStatement.setString(3, projectType);

			preparedStatement.toString();
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
