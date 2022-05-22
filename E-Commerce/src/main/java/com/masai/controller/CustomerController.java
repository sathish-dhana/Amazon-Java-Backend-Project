package com.masai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Address;
import com.masai.beans.Card;
import com.masai.beans.Customer;
import com.masai.beans.Login;
import com.masai.beans.UserDTO;
import com.masai.service.AddressServiceInterface;
import com.masai.service.CardServiceInteface;
import com.masai.service.CustomerServiceInterface;
import com.masai.service.LoginServiceInterface;

@RestController
@RequestMapping("/ecommerce/customersPortal")
public class CustomerController {
	
	@Autowired
	private CustomerServiceInterface customerService;
	

	@Autowired 
	private LoginServiceInterface loginService;

	@Autowired
	private AddressServiceInterface addressService;

	
	
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
	
	// Handle		 --> /ecommerce/customersPortal/customer/{id}
	// What is does? --> Updates the fields provided in the userInfo (any field except userId can be updated)
	// Request Type? --> Put Request	
	// Input 		 --> UserDTO object (All fields allowed) and Id in the path variable 
	/*
	 * @PutMapping("/customer/{id}") public ResponseEntity<String>
	 * updateCustomer(@RequestBody @Valid UserDTO userInfo, @PathVariable Integer
	 * id) { Customer updatedCustomer = customerService.updateCustomer(userInfo,
	 * id); return new ResponseEntity(updatedCustomer, HttpStatus.OK); }
	 */
	
	@PutMapping("/customer")
	public ResponseEntity<String> updateCustomerUsingAPI(@RequestBody @Valid UserDTO userInfo, @RequestParam String key) {
		Login currentLogin = loginService.isTokenValid(key);
		Customer updatedCustomer = customerService.updateCustomer(userInfo, currentLogin.getUser().getUserId());
		return new ResponseEntity(updatedCustomer, HttpStatus.OK);
	}
	
	// Handle		 --> /ecommerce/customersPortal/customer/{customerId}
	// What is does? --> Adds a new customer card details
	// Request Type? --> POST Request
	// Input 		 --> Card Object
	@PostMapping("/customer/{customerId}")
	public ResponseEntity<Customer> addCustomerCardDetails(@PathVariable("customerId") @Valid Integer customerId, @RequestBody Card card) {
		Customer getCustomer = customerService.addCustomerAddress(customerId, card);		
		return new ResponseEntity(getCustomer, HttpStatus.CREATED);
	}
	
	@PostMapping("/customer/addAddress/{customerId}")
	public ResponseEntity<Customer> addCustomerAddress(@PathVariable("customerId") @Valid Integer customerId, @RequestBody Address address) {
		Customer getCustomer = customerService.getCustomerById(customerId);
		Address saveaddress = addressService.addAddress(address);
		getCustomer.getAddresses().add(saveaddress);
		return new ResponseEntity(getCustomer, HttpStatus.CREATED);
	}

}
