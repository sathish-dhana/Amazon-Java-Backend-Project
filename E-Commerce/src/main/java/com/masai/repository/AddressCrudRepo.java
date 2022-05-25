package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Address;
import com.masai.beans.Customer;
import com.masai.beans.Seller;
import com.masai.beans.User;

@Repository
public interface AddressCrudRepo extends JpaRepository<Address, Integer>{
	
	public List<Address> findByUser(User user);
	
	public List<Address> findByCity(String city);
	
	public List<Address> findByState(String city);
	
	public List<Address> findByPincode(String pincode);
	
	public List<Address> deleteAllByUser(User user);
	
}
