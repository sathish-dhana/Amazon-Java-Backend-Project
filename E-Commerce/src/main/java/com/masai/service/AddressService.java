package com.masai.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Address;
import com.masai.beans.Customer;
import com.masai.beans.Product;
import com.masai.beans.Seller;
import com.masai.beans.User;
import com.masai.exception.AddressAlreadyExistException;
import com.masai.exception.AddressNotFoundException;
import com.masai.exception.ProductNotFoundException;
import com.masai.exception.SellerAlreadyExistException;
import com.masai.exception.SellerNotFoundException;
import com.masai.exception.UserNotFoundException;
import com.masai.repository.AddressCrudRepo;

@Service
public class AddressService implements AddressServiceInterface {
	
	@Autowired
	private AddressCrudRepo addressCrudRepo;
	
	
	//-------------------------------------------------------------------------//
	//	1. To add Address to table
	//-------------------------------------------------------------------------//
	@Override
	public Address addAddress(Address address) {
		// TODO Auto-generated method stub

		Address savedAddress = null;

		savedAddress = addressCrudRepo.save(address);

		return savedAddress;
	}	
	
	
	//-------------------------------------------------------------------------//
	//	1. To list all the user by city
	//-------------------------------------------------------------------------//
	@Override
	public Set<User> listAllUserByCity(String city) {
		// TODO Auto-generated method stub
		List<Address> listAddress = addressCrudRepo.findByCity(city);
		
		//Use hash set to remove the duplicates
		Set<User> user = new HashSet();
		
		if (listAddress.size() > 0) {
			
			//Iterating to the list and adding the user
			for (Address address : listAddress) {
				user.add(address.getUser());
			}
			
		} else {
			throw new UserNotFoundException("No user Found in " + city);
		}
		return user;
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. To list all the user by state
	//-------------------------------------------------------------------------//
	@Override
	public Set<User> listAllUserByState(String state) {
		// TODO Auto-generated method stub
		List<Address> listAddress = addressCrudRepo.findByState(state);
		
		//Use hash set to remove the duplicates
		Set<User> user = new HashSet();
		
		if (listAddress.size() > 0) {
			
			//Iterating to the list and adding the user
			for (Address address : listAddress) {
				user.add(address.getUser());
			}
		} else {
			throw new AddressNotFoundException("No user Found in " + state);
		}
		return user;
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. To list all the user by pincode
	//-------------------------------------------------------------------------//
	@Override
	public Set<User> listAllUserByPincode(String pincode) {
		// TODO Auto-generated method stub
		List<Address> listAddress = addressCrudRepo.findByPincode(pincode);
		
		//Use hash set to remove the duplicates
		Set<User> user = new HashSet();
		
		if (listAddress.size() > 0) {
			//Iterating to the list and adding the user
			for (Address address : listAddress) {
				user.add(address.getUser());
			}
			
		} else {
			throw new AddressNotFoundException("No user Found in " + pincode);
		}
		return user;
	}
	
	
	//-------------------------------------------------------------------------//
	//  1. To save the address to table
	//	2. To provide Bi-directional linking
	//-------------------------------------------------------------------------//
	public Address persistCustomer(Customer customer, Address address) {
		
		//set the customer reference to the adsress
		address.setUser(customer);
		
		//save the address and return
		Address savedAddress = addressCrudRepo.save(address);
		
		return savedAddress;
	}
	
	
	//-------------------------------------------------------------------------//
	//  1. To delete the address to table
	//	2. To remove the Bi-directional linking
	//-------------------------------------------------------------------------//
	@Override
	public String deleteAddress(Integer addressId) {
		// TODO Auto-generated method stub
		Optional<Address> findAddress = addressCrudRepo.findById(addressId);
		
		//Change the user reference to Null to remove the bi-directional link
		findAddress.get().setUser(null);
		
		if (findAddress.isPresent()) {
			
			//delete the address
			addressCrudRepo.deleteById(addressId);
			
			return "Address with ID : " + addressId + " deleted.";
		} else {
			throw new AddressNotFoundException("No Address Found");
		}
		
	}
	
	
	//-------------------------------------------------------------------------//
	//  1. To check the address with given Id
	//-------------------------------------------------------------------------//
	@Override
	public boolean checkAddressId(int addressId) {
		Optional<Address> opt = addressCrudRepo.findById(addressId);
		return opt.isPresent();
	}

	
	//-------------------------------------------------------------------------//
	//  1. To get the address with given Id
	//-------------------------------------------------------------------------//
	@Override
	public Address getAddressById(int addressId) {
		Optional<Address> address = addressCrudRepo.findById(addressId);
		if(address.isEmpty()) {
			throw new AddressNotFoundException("Invalid address ID");
		}
		return address.get();
	}
	
	

}
