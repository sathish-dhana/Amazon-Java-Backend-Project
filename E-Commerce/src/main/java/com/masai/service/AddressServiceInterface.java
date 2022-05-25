package com.masai.service;

import java.util.List;
import java.util.Set;

import com.masai.beans.Address;
import com.masai.beans.Customer;
import com.masai.beans.Seller;
import com.masai.beans.User;

public interface AddressServiceInterface {
	
	public Address addAddress(Address address);	
	
	public Set<User> listAllUserByCity(String city);
	
	public Set<User> listAllUserByState(String state);
	
	public Set<User> listAllUserByPincode(String pincode);
	
	public Address persistCustomer(Customer customer, Address address);
	
	public String deleteAddress(Integer addressId);
	
	public boolean checkAddressId(int userId);
	
	public Address getAddressById(int addressId);
}
