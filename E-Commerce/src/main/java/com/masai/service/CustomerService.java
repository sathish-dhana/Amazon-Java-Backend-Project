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
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Todo
	//Add methods to find customers by different parameters
}
