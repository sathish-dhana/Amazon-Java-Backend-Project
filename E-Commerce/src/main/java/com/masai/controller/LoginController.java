package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.User;
import com.masai.beans.UserDTO;
import com.masai.service.LoginServiceInterface;

@RestController
@RequestMapping("/ecommerce/login")
public class LoginController {
	
	@Autowired
	LoginServiceInterface loginService;
	
	@PostMapping("/{type}")
	public ResponseEntity<User> loginUser(@RequestBody UserDTO loginInfo, @PathVariable String type) {
		User loggedInUser = loginService.login(loginInfo, type);
		return new ResponseEntity<User>(loggedInUser, HttpStatus.OK);
	}
	
}
