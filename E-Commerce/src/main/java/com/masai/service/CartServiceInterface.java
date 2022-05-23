package com.masai.service;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;

public interface CartServiceInterface {
	
	public Cart  saveCart(Customer customer,Item item);
	
}
