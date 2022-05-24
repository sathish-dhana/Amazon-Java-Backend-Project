package com.masai.service;

import java.util.List;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;

public interface CartServiceInterface {
	
	public Cart  saveCart(Customer customer,Item item);
	public List<Item> getAllItem(Cart cart);
	public Cart alterCart(Customer customer, Item item);
	
}
