package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Cart;

@Repository
public interface CartCrudRepo extends JpaRepository<Cart, Integer>{
	
	public Cart findByCartId(int cartId);
}
