package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Customer;
import com.masai.beans.Seller;

@Repository
public interface SellerCrudRepo extends JpaRepository<Seller, Integer> {
	
	public Optional<Seller> findByUserName(String userName);
	
	public Optional<Seller> findByUserPassword(String userPassword);
	
	public Optional<Seller> findByUserNameAndUserPassword(String userName, String password);
}
