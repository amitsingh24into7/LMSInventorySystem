package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.CommentsDao;
import com.lms.model.Comments;
import com.lms.util.DbUtil;

public class CommentsDaoImpl implements CommentsDao {

	private Connection connection;

	public CommentsDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}

	public void addComments(Comments comments) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into comments("
							+ "DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID,TYPES)"
							+ " values (?, ?, ?, ?,?)");

			preparedStatement.setString(1, comments.getDETAILS());
			preparedStatement.setString(2, comments.getCREATED_BY());

			preparedStatement.setString(3, comments.getCRETAED_DATE());
			preparedStatement.setString(4, comments.getLEAD_ID());
			preparedStatement.setString(5, comments.getTYPES());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	public List<Comments> getCommentsByLeadID(String leadID) {
		// TODO Auto-generated method stub
		List<Comments> comments = new ArrayList<Comments>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select DETAILS,TYPES, CREATED_BY, cast(CRETAED_DATE as date) as CRETAED_DATE, LEAD_ID from comments where LEAD_ID='"
							+ leadID + "' order by CRETAED_DATE desc");
			while (rs.next()) {
				Comments comment = new Comments();
				
				comment.setLEAD_ID(rs.getString("lEAD_ID"));
				comment.setDETAILS(rs.getString("dETAILS"));
				comment.setTYPES(rs.getString("tYPES"));
				comment.setCREATED_BY(rs.getString("cREATED_BY"));
				comment.setCRETAED_DATE(rs.getString("cRETAED_DATE"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}

	public void updateCommentsLeadID(String budleadID,String leadID,String createdDate,String createdBy) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("update comments set LEAD_ID=? where LEAD_ID=?");

			preparedStatement.setString(1, leadID);
			
			preparedStatement.setString(2, budleadID);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
