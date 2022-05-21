package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Customer;
import com.masai.beans.UserDTO;
import com.masai.exception.CustomerAlreadyExistsException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.repository.CustomerCrudRepo;

@Service
public class CustomerService implements CustomerServiceInterface {
	
	@Autowired
	private CustomerCrudRepo customerCrudRepo;
	
	@Override
	public Customer addCustomer(Customer customer) {
		Optional<Customer> opt = customerCrudRepo.findByUserName(customer.getUserName());
		
		if(opt.isEmpty()) {
			Customer savedCustomer = customerCrudRepo.save(customer);
			return savedCustomer;
		} else {
			throw new CustomerAlreadyExistsException("Customer with the given username already exists.");
		}
	}

	@Override
	public String removeCustomer(UserDTO userInfo) {
		
		Optional<Customer> customer = customerCrudRepo.findByUserName(userInfo.getUserName());
		
		if(customer.isPresent() && customer.get().getUserPassword().equals(userInfo.getUserPassword())) {
			
			customerCrudRepo.delete(customer.get());
			
		} else {
			
			throw new CustomerNotFoundException("username/password is wrong. Please provide the correct details to perform this operation");
			
		}
		
		return "Successfully deleted " + userInfo.getUserName() + "'s Account from the database";
		
	}

	@Override
	public List<Customer> viewAllCustomers() {
		List<Customer> customers = customerCrudRepo.findAll();
		if(customers.size() == 0) {
			throw new CustomerNotFoundException("No Customers registered on the portal!");
		}
		
		return customers;
	}

	@Override
	public Customer updateCustomer(UserDTO customerInfo, Integer id) {
		
		Optional<Customer> opt = customerCrudRepo.findById(id);
		
		if(opt.isPresent()) {
			Customer customer = opt.get();
			
			//Updating the email
			if(customerInfo.getEmail() != null) {
				customer.setEmail(customerInfo.getEmail());
			}
			
			//Updating the First Name
			if(customerInfo.getFirstName() != null) {
				customer.setFirstName(customerInfo.getFirstName());
			}
			
			//Updating the Last Name
			if(customerInfo.getLastName() != null) {
				customer.setLastName(customerInfo.getLastName());
			}
			
			//Updating the Mobile Number
			if(customerInfo.getMobileNumber() != null) {
				customer.setMobileNumber(customerInfo.getMobileNumber());
			}
			
			//Updating the User Name
			if(customerInfo.getUserName() != null) {
				customer.setUserName(customerInfo.getUserName());
			}
			
			//Updating the User Password
			if(customerInfo.getUserPassword() != null) {
				customer.setUserPassword(customerInfo.getUserPassword());
			}
			
			customerCrudRepo.save(customer);
			return customer;
		} else {
			throw new CustomerNotFoundException("No customer exists with the given id!");
		}

	}
	
	//Todo
	//Add methods to find customers by different parameters
}
