package com.lms.dao;

import java.util.List;

import com.lms.model.User;

public interface UserDao {
	public List<User> getAllUsers(String status);

	public User getUserById(String userId,String status);
	
	public String validateUser(String email,String password);
	public String getPassword(String email);
}
