package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Customer;
import com.masai.beans.Product;

@Repository
public interface CustomerCrudRepo extends JpaRepository<Customer, Integer> {
	
	public Optional<Customer> findByUserName(String username);
	
	public Optional<Customer> findByUserNameAndUserPassword(String userName, String password);
	
	public Customer findByUserId(int customerId);
	
}
