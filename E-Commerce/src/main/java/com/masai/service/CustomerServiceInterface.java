package com.masai.service;

import java.util.List;

import com.masai.beans.Customer;
import com.masai.beans.UserDTO;

public interface CustomerServiceInterface {
	public Customer addCustomer(UserDTO customer);
	public Customer removeCustomer(Customer customer);
	public List<Customer> viewAllCustomers();
	public Customer updateCustomer(Customer customer);
}
