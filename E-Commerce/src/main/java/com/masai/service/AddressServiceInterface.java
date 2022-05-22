package com.masai.service;

import java.util.List;
import java.util.Set;

import com.masai.beans.Address;
import com.masai.beans.Customer;
import com.masai.beans.Seller;
import com.masai.beans.User;

public interface AddressServiceInterface {
	
	//need to clarify doubt with team
	
	public Address addAddress(Address address);
	public String removeAddressByAddressId(Integer addressId);
	public String removeAllAddressOfUser(User user);
	
	
	public Address updateAddress(Address address);
	public Address showAddressById(Integer addressId);
	
	public Set<User> listAllUserByCity(String city);
	public Set<User> listAllUserByState(String state);
}
