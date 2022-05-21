package com.masai.service;

import com.masai.beans.User;
import com.masai.beans.UserDTO;

public interface LoginServiceInterface {
	public User login(UserDTO userInfo, String UserType);
}
