package com.masai.service;

import java.util.List;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.beans.ItemDTO;

public interface CartServiceInterface {
	
	public Cart  saveCart(Customer customer,Item item);
	
	public List<Item> getAllItem(Cart cart);
	
	//Test
	public List<Item> sendToOrder(int customerId);

	public Cart alterCart(Customer customer, ItemDTO newItem);
}
