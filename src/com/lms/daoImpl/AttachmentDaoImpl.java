package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.AttachmentDao;
import com.lms.model.Attachment;
import com.lms.util.DbUtil;

public class AttachmentDaoImpl implements AttachmentDao{

	
	private Connection connection;

	public AttachmentDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}

	public void addAttachments(Attachment attachments) {
		// TODO Auto-generated method stub
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into document("
							+ "DOC_NAME, DOC_URL, DOC_TYPE, DOC_CONTENT_TYPE, DOC_VERSION, LEAD_ID, DOC_CREATED_BY, DOC_CREATED_DATE)"
							+ " values (?, ?, ?, ?,?, ?, ?, ?)");

			preparedStatement.setString(1, attachments.getDOC_NAME());
			preparedStatement.setString(2, attachments.getDOC_URL());

			preparedStatement.setString(3, attachments.getDOC_TYPE());
			preparedStatement.setString(4, attachments.getDOC_CONTENT_TYPE());
			preparedStatement.setString(5, attachments.getDOC_VERSION());
			preparedStatement.setString(6, attachments.getLEAD_ID());
			preparedStatement.setString(7, attachments.getDOC_CREATED_BY());
			preparedStatement.setString(8, attachments.getDOC_CREATED_DATE());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public List<Attachment> getCommentsAttachmentsByLeadID(String leadID) {
		// TODO Auto-generated method stub
		List<Attachment> attachments = new ArrayList<Attachment>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select DOC_ID, DOC_NAME, DOC_URL, DOC_TYPE, DOC_CONTENT_TYPE, DOC_VERSION, LEAD_ID, DOC_CREATED_BY, cast(DOC_CREATED_DATE as date) as DOC_CREATED_DATE from document where LEAD_ID='"
							+ leadID + "' and DOC_TYPE='COMMENTS' ORDER BY DOC_CREATED_DATE desc");
			while (rs.next()) {
				Attachment attachment = new Attachment();
				attachment.setDOC_NAME(rs.getString("dOC_NAME"));
				attachment.setDOC_TYPE(rs.getString("dOC_TYPE"));
				attachment.setDOC_URL(rs.getString("dOC_URL"));
				attachment.setDOC_VERSION(rs.getString("dOC_VERSION"));
				attachment.setDOC_CONTENT_TYPE(rs.getString("dOC_CONTENT_TYPE"));
				attachment.setLEAD_ID(rs.getString("lEAD_ID"));
				attachment.setDOC_CREATED_BY(rs.getString("dOC_CREATED_BY"));
				attachment.setDOC_CREATED_DATE(rs.getString("dOC_CREATED_DATE"));
				
				attachments.add(attachment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attachments;
	}

	public List<Attachment> getCostSheet(String leadID) {
		List<Attachment> attachments = new ArrayList<Attachment>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from document where LEAD_ID='"
							+ leadID + "' and DOC_TYPE='COSTSHEET' ORDER BY DOC_CREATED_DATE desc");
			while (rs.next()) {
				Attachment attachment = new Attachment();
				attachment.setDOC_NAME(rs.getString("dOC_NAME"));
				attachment.setDOC_TYPE(rs.getString("dOC_TYPE"));
				attachment.setDOC_URL(rs.getString("dOC_URL"));
				attachment.setDOC_VERSION(rs.getString("dOC_VERSION"));
				attachment.setDOC_CONTENT_TYPE(rs.getString("dOC_CONTENT_TYPE"));
				attachment.setLEAD_ID(rs.getString("lEAD_ID"));
				attachment.setDOC_CREATED_BY(rs.getString("dOC_CREATED_BY"));
				attachment.setDOC_CREATED_DATE(rs.getString("dOC_CREATED_DATE"));
				
				attachments.add(attachment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attachments;
	}

	public List<Attachment> getOtherAttachement(String leadID) {
		List<Attachment> attachments = new ArrayList<Attachment>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT DOC_ID, DOC_NAME, DOC_URL, DOC_TYPE, DOC_CONTENT_TYPE, DOC_VERSION, LEAD_ID, DOC_CREATED_BY, cast(DOC_CREATED_DATE as date) as DOC_CREATED_DATE from document where LEAD_ID='"
							+ leadID + "' and DOC_TYPE='COMMENTS' ORDER BY DOC_CREATED_DATE desc");
			while (rs.next()) {
				Attachment attachment = new Attachment();
				attachment.setDOC_NAME(rs.getString("dOC_NAME"));
				attachment.setDOC_TYPE(rs.getString("dOC_TYPE"));
				attachment.setDOC_URL(rs.getString("dOC_URL"));
				attachment.setDOC_VERSION(rs.getString("dOC_VERSION"));
				attachment.setDOC_CONTENT_TYPE(rs.getString("dOC_CONTENT_TYPE"));
				attachment.setLEAD_ID(rs.getString("lEAD_ID"));
				attachment.setDOC_CREATED_BY(rs.getString("dOC_CREATED_BY"));
				attachment.setDOC_CREATED_DATE(rs.getString("dOC_CREATED_DATE"));
				
				attachments.add(attachment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attachments;
	}

	public void updateAttachmentLeadID(String budleadID,String leadID,String createdDate,String createdBy) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("update document set LEAD_ID=? where LEAD_ID=?");

			preparedStatement.setString(1, leadID);
			
			preparedStatement.setString(2, budleadID);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
