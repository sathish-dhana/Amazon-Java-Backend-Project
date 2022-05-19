package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Customer;
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
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.addCustomer(customer);
		return new ResponseEntity(newCustomer, HttpStatus.CREATED);
	}

}
