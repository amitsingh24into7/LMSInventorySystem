package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.UserDao;
import com.lms.model.User;
import com.lms.util.DbUtil;

public class UserDaoImpl implements UserDao {

	private Connection connection;
	
	  
	public UserDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello"+connection);
	}

	
	
	
	

	public List<User> getAllUsers(String status) {
		List<User> users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from stg_mtr_user where USER_STATUS='"+status+"'");
			while (rs.next()) {
				User user = new User();
				user.setUSER_ID(rs.getString("uSER_ID"));
				user.setUSERNAME(rs.getString("uSERNAME"));
				user.setEMAIL_ID(rs.getString("eMAIL_ID"));
				user.setMOBILENO(rs.getString("mOBILENO"));
				user.setUSER_STATUS(rs.getString("uSER_STATUS"));
				user.setALETERNATENO(rs.getString("aLETERNATENO"));
				user.setADDRESS1(rs.getString("aDDRESS1"));
				user.setADDRESS2(rs.getString("aDDRESS2"));
				user.setADDRESS3(rs.getString("aDDRESS3"));
				user.setDEPARTMENT(rs.getString("dEPARTMENT"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public User getUserById(String userId,String status) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from stg_mtr_user where user_id=? and user_status=?");
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, status);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				
				user.setUSER_ID(rs.getString("uSER_ID"));
				user.setUSERNAME(rs.getString("uSERNAME"));
				user.setEMAIL_ID(rs.getString("eMAIL_ID"));
				user.setMOBILENO(rs.getString("mOBILENO"));
				user.setUSER_STATUS(rs.getString("uSER_STATUS"));
				user.setALETERNATENO(rs.getString("aLETERNATENO"));
				user.setADDRESS1(rs.getString("aDDRESS1"));
				user.setADDRESS2(rs.getString("aDDRESS2"));
				user.setADDRESS3(rs.getString("aDDRESS3"));
				user.setDEPARTMENT(rs.getString("dEPARTMENT"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	public String validateUser(String email,String password)
	{
		String msg="";
		try {
			String sql="select USER_ID from stg_mtr_user where USER_ID=? and PASSWORD=? and USER_STATUS='Active'";
			System.out.println(sql);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				msg="valid";
			}
			else
			{
				msg="invalid";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	public String getPassword(String email)
	{
		String msg="";
		try {
			String sql="select password from stg_mtr_user where USER_ID=? and USER_STATUS='Active'";
			System.out.println(sql);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			
			System.out.println(preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				msg=rs.getString("password");
			}
			else
			{
				msg="invalid";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
}
