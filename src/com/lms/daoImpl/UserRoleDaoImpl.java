package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.UserRoleDao;
import com.lms.model.UserRole;
import com.lms.util.DbUtil;

public class UserRoleDaoImpl implements UserRoleDao {

	private Connection connection;
	
	  
	public UserRoleDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello"+connection);
	}

	
	public List<UserRole> getDefaultUserOtherDetails(String userID) {
		// TODO Auto-generated method stub
		List<UserRole> users = new ArrayList<UserRole>();
		try {
			Statement statement = connection.createStatement();
			String sql="select * from user_role where user_id='"+userID+"' and  ROLE_STATUS='Active' and DEFAULT_ROLE='1'";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				UserRole user = new UserRole();
				
				user.setUSER_ID(rs.getString("uSER_ID"));
				user.setUSER_NAME(rs.getString("uSER_NAME"));
				user.setBUSINESS_TYPE(rs.getString("bUSINESS_TYPE"));
				user.setEMAIL_ID(rs.getString("eMAIL_ID"));
				user.setEXE_CITY(rs.getString("eXE_CITY"));
				user.setEXE_STATE(rs.getString("eXE_STATE"));
				user.setEXE_STATE_SHORT(rs.getString("eXE_STATE_SHORT"));
				user.setREGION_TYPE(rs.getString("rEGION_TYPE"));
				user.setROLE_ID(rs.getString("rOLE_ID"));
				user.setROLE_NAME(rs.getString("rOLE_NAME"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	public List<UserRole> getUserOtherDetails(String userID) {
		// TODO Auto-generated method stub
		List<UserRole> users = new ArrayList<UserRole>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from user_role where userId='"+userID+"' and  ROLE_STATUS='Active'");

			while (rs.next()) {
				UserRole user = new UserRole();
				
				user.setUSER_ID(rs.getString("uSER_ID"));
				user.setUSER_NAME(rs.getString("uSER_NAME"));
				user.setBUSINESS_TYPE(rs.getString("bUSINESS_TYPE"));
				user.setEMAIL_ID(rs.getString("eMAIL_ID"));
				user.setEXE_CITY(rs.getString("eXE_CITY"));
				user.setEXE_STATE(rs.getString("eXE_STATE"));
				user.setEXE_STATE_SHORT(rs.getString("eXE_STATE_SHORT"));
				user.setREGION_TYPE(rs.getString("rEGION_TYPE"));
				user.setROLE_ID(rs.getString("rOLE_ID"));
				user.setROLE_NAME(rs.getString("rOLE_NAME"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}


	public List<UserRole> getUsersByRole(String roleID) {
		List<UserRole> users = new ArrayList<UserRole>();
		try {
			Statement statement = connection.createStatement();
			String sql="select * from user_role where ROLE_ID='"+roleID+"' and  ROLE_STATUS='Active'";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				UserRole user = new UserRole();
				
				user.setUSER_ID(rs.getString("uSER_ID"));
				user.setUSER_NAME(rs.getString("uSER_NAME"));
				user.setBUSINESS_TYPE(rs.getString("bUSINESS_TYPE"));
				user.setEMAIL_ID(rs.getString("eMAIL_ID"));
				user.setEXE_CITY(rs.getString("eXE_CITY"));
				user.setEXE_STATE(rs.getString("eXE_STATE"));
				user.setEXE_STATE_SHORT(rs.getString("eXE_STATE_SHORT"));
				user.setREGION_TYPE(rs.getString("rEGION_TYPE"));
				user.setROLE_ID(rs.getString("rOLE_ID"));
				user.setROLE_NAME(rs.getString("rOLE_NAME"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

}
