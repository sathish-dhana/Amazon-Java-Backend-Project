package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Item;
import com.masai.beans.Product;
import com.masai.exception.ProductNotFoundException;
import com.masai.repository.ItemCrudRepo;

@Service
public class ItemService implements ItemServiceInterface{

	@Autowired
	private ItemCrudRepo itemCrudRepo;

	@Autowired
	private ProductService productService;
	
	@Override
	public Item addItem(Item item) {
		
		
		if(item.getProduct().getQuantity()>=item.getQuantity()) {
			
			Item itemSaved=itemCrudRepo.save(item);
			return itemSaved;
			
		}
	
			throw new ProductNotFoundException("Product does not exist");
		
		
	}

	@Override
	public String removeItem(Item item) {
		// TODO Auto-generated method stub
		
		Optional<Item> opt=itemCrudRepo.findById(item.getItemId());
		
		if(opt.isPresent()) {
			itemCrudRepo.deleteById(item.getItemId());
		}
		else {
			throw new ProductNotFoundException("Product does not exist");
		}
		
		return "Product with "+item.getItemId()+" is deleted" ;
	}

	

	@Override
	public Item updateItem(Item item) {
		// TODO Auto-generated method stub
		
		return null;
	}

	
	

}
