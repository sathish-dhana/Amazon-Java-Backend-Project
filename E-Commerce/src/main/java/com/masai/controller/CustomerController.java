package com.masai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	// Handle		 --> /ecommerce/customersPortal/customer
	// What is does? --> Adds a new customer
	// Request Type? --> POST Request
	// Input 		 --> Customer object (All fields can be null except --userName-- and --userPassword--
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer, HttpSession session) {
		Customer newCustomer = customerService.addCustomer(customer);
		session.setAttribute("customerData", newCustomer);
		return new ResponseEntity(newCustomer, HttpStatus.CREATED);
	}
	
	
	// Handle		 --> /ecommerce/customersPortal/customers
	// What is does? --> Returns a list of all the customers
	// Request Type? --> Get Request
	// Input 		 --> None
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewCustomers() {
		List<Customer> customers = customerService.viewAllCustomers();
		return new ResponseEntity(customers, HttpStatus.OK);
	}
	
	// Handle		 --> /ecommerce/customersPortal/customer
	// What is does? --> Deletes the user provided if the id and password matches
	// Request Type? --> Post Request
	// Input 		 --> UserDTO object which compromises of userName and userPassword
	@DeleteMapping("/customer")
	public ResponseEntity<String> deleteCustomer(@RequestBody UserDTO userInfo) {
		String status = customerService.removeCustomer(userInfo);
		return new ResponseEntity(status, HttpStatus.OK);
	}

}
