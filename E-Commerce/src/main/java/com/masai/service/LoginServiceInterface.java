package com.masai.service;

import com.masai.beans.Login;
import com.masai.beans.User;
import com.masai.beans.UserDTO;

public interface LoginServiceInterface {
	
	public User login(UserDTO userInfo, String UserType);
	
	public Login isTokenValid(String token);
	
	public String logout(String key);
	
}
