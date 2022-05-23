package com.masai.service;

import java.util.List;


import com.masai.beans.Item;


public interface ItemServiceInterface {
	
	public Item addItem(Item item);
	public String removeItem(Item item);
	public Item updateItem(Item item);
}
