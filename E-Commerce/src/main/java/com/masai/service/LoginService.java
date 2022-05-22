package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Customer;
import com.masai.beans.User;
import com.masai.beans.UserDTO;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.LoginFailedException;

@Service
public class LoginService implements LoginServiceInterface {
	
	@Autowired
	CustomerServiceInterface customerService;
	
	@Autowired
	SellerServiceInterface sellerService;

	@Override
	public User login(UserDTO loginInfo, String userType) {
		if(userType.equalsIgnoreCase("customer")) {
			try {
				User customer = customerService.findByUsernameAndPassword(loginInfo.getUserName(), loginInfo.getUserPassword());
				return customer;
			} catch (CustomerNotFoundException error) {
				throw new LoginFailedException(error.getMessage());
			}
		} else if (userType.equalsIgnoreCase("seller")) {
			//TODO COLLOBORATE WITH DHANA
		} else {
			throw new LoginFailedException("Mention the correct path. Only ecommerce/login/customer and ecommerce/login/seller is allowed.");
		}
		return null;
	}
	
//	public boolean isTokenValid(String token, String userType) {
//		if(userType.equalsIgnoreCase("customer")) {
//			try {
//				User customer = customerService.findByUsernameAndPassword(loginInfo.getUserName(), loginInfo.getUserPassword());
//				return customer;
//			} catch (CustomerNotFoundException error) {
//				throw new LoginFailedException(error.getMessage());
//			}
//		}
//	}

}
