package com.masai.service;

import com.masai.beans.Cart;
import com.masai.beans.Item;

public interface CartServiceInterface {
	
	public Cart  saveCart(Cart cart);
	public Cart  saveCart(int customerId,Item item);
	
}
