package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Login;
import com.masai.beans.LoginStatus;
import com.masai.beans.User;
import com.masai.beans.UserDTO;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.InvalidLoginKeyException;
import com.masai.exception.LoginFailedException;
import com.masai.repository.LoginCrudRepo;

@Service
public class LoginService implements LoginServiceInterface {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SellerServiceInterface sellerService;
	
	@Autowired
	private LoginCrudRepo loginRepo;

	@Override
	public User login(UserDTO loginInfo, String userType) {
		if(userType.equalsIgnoreCase("customer")) {
			try {
				User customer = customerService.findByUsernameAndPassword(loginInfo.getUserName(), loginInfo.getUserPassword());
		
				Login newLogin = null;
				
				if(customer.getLogin() == null) {
					newLogin = new Login();
				} else {
					newLogin = customer.getLogin();
					newLogin.newLogin();
				}
				
				loginRepo.save(newLogin);
				newLogin.setUser(customer);
				return customerService.persistCustomer(customer.getUserId(), newLogin);

			} catch (CustomerNotFoundException error) {
				throw new LoginFailedException(error.getMessage());
			}
		} else if (userType.equalsIgnoreCase("seller")) {
			//TODO COLLOBORATE WITH DHANA
				try {
				User seller = sellerService.findByUsernameAndPassword(loginInfo.getUserName(), loginInfo.getUserPassword());
				
				Login newLogin = null;
				
				if(seller.getLogin() == null) {
					newLogin = new Login();
				} else {
					newLogin = seller.getLogin();
					newLogin.newLogin();
				}
				
				loginRepo.save(newLogin);
				newLogin.setUser(seller);
				return sellerService.persistSeller(seller.getUserId(), newLogin);
	
			} catch (CustomerNotFoundException error) {
				throw new LoginFailedException(error.getMessage());
			}
			
			
			
		} else {
			throw new LoginFailedException("Mention the correct path. Only ecommerce/login/customer and ecommerce/login/seller is allowed.");
		}
	}
	
	
	//Method to logout user
	public String logout(String key) {
		
		//Getting the Login object using the key
		Optional<Login> opt = loginRepo.findByApiKey(key);
		
		if(opt.isPresent()) {
			
			//Getting the login object
			Login currentLogin = opt.get();
			
			//If the status is already logged_out we return
			if(currentLogin.getStatus() == LoginStatus.LOGGED_OUT) {
				return "User is already logged out";
			}
			
			//Calling the revoke login function as request by the user
			currentLogin.revokeLogin();
			
			//Persisting in the database
			loginRepo.save(currentLogin);
		} else {
			//Exception thrown if the key is not existing in the database
			throw new InvalidLoginKeyException("Invalid Login Key!");
		}
		
		//Message for successfull logout
		return "User logged out successfully!";
	}
	
	@Override
	public Login isTokenValid(String apiKey) {
		
		Optional<Login> opt = loginRepo.findByApiKey(apiKey);
		
		if(opt.isPresent()) {
			
			Login currentLogin = opt.get();
			
			LocalDateTime expiry = currentLogin.getKeyExpiryDate();
			
			if(currentLogin.getStatus() == LoginStatus.LOGGED_IN && LocalDateTime.now().isBefore(expiry)) {
				return currentLogin;
			} else {

				currentLogin.revokeLogin();
				loginRepo.save(currentLogin);
				throw new InvalidLoginKeyException("Login Key has expired please login again to generate a new key!");
			}
			
		} else {
			throw new InvalidLoginKeyException("Invalid Login Key!");
		}
	}

}
