package com.lms.dao;

import java.util.List;

import com.lms.model.UserRole;

public interface UserRoleDao {
	
	public List<UserRole> getUserOtherDetails(String userID);
	public List<UserRole> getDefaultUserOtherDetails(String userID);
	public List<UserRole> getUsersByRole(String roleID);
	
	

}
