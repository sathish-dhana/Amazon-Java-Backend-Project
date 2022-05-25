package com.masai.service;

import java.util.List;

import com.masai.beans.Address;
import com.masai.beans.Card;
import com.masai.beans.Customer;
import com.masai.beans.Login;
import com.masai.beans.Ordered;
import com.masai.beans.UserDTO;

public interface CustomerServiceInterface {
	
	public Customer addCustomer(Customer customer);
	
	public String removeCustomer(UserDTO userInfo);
	
	public List<Customer> viewAllCustomers();
	
	public Customer updateCustomer(UserDTO userInfo, Integer id);
	
	public Customer findByUsernameAndPassword(String username, String password);
	
	public Customer persistCustomer(Integer customerID, Login login);
	
	public Customer getCustomerById(Integer customerId);
	
	public Customer addCustomerCard(Integer customerId, Card card);
	
	public Customer addCustomerAddress(Integer customerId, Address address);
	
	public Customer removeCustomerAddress(Integer customerId, Integer addressId);
	
	public Customer addCustomerOrder(Integer customerId, Ordered order);
	
}
