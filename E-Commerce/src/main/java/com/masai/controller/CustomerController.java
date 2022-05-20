package com.masai.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Customer;
import com.masai.beans.UserDTO;
import com.masai.service.CustomerServiceInterface;

@RestController
@RequestMapping("/ecommerce/customersPortal")
public class CustomerController {
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	
	//Able to add customers now. 
	//Table creation also working fine
	//TODO --- Handle exceptions and validation. 
	//Will add rest of the functionality tomorrow
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@RequestBody @Valid UserDTO customer, HttpSession session) {
		Customer newCustomer = customerService.addCustomer(customer);
		session.setAttribute("customerData", newCustomer);
		return new ResponseEntity(newCustomer, HttpStatus.CREATED);
	}

}
