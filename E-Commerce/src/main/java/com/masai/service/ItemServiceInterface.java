package com.masai.service;

import com.masai.beans.Item;
import com.masai.beans.Product;


public interface ItemServiceInterface {
	
	public Item addItem(Item item);
	
	public String removeItem(Item item);
	
	public Item updateItem(Item item);
	
	public Item addItem(Product product,int quantity);
}
