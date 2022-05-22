package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.beans.Address;
import com.masai.beans.Card;
import com.masai.beans.Customer;

public interface CardCrudRepo extends JpaRepository<Card, Integer>{
	
	public Optional<Card> findByCardNumber(String string);
}
