package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.beans.Item;
import com.masai.beans.Product;
import com.masai.beans.ProductStatus;
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
		
	
		Optional<Product> optProductCheck=productService.getProductRepo().findById(item.getProduct().getProductId());
	
		if(optProductCheck.isPresent()) {
			
			Product productCheck=optProductCheck.get();
			if(productCheck.getQuantity()>=item.getRequiredQuantity() && productCheck.getProductStatus() == ProductStatus.AVAILABLE) {
				
				//Setting the item Price
				item.setItemPrice(productCheck.getPrice()*item.getRequiredQuantity());
					
				//Setting the product
				item.setProduct(productCheck);
					
				//saving item to DB
				Item itemSaved=itemCrudRepo.save(item);
				return itemSaved;
			}
			else {
					throw new ProductNotFoundException("Product quantity is not enough");
			}
		}
		else {
			throw new ProductNotFoundException("Product does not exist");
		}
	}
			
			
	

	@Override
	public String removeItem(Item item) {
		// TODO Auto-generated method stub
		
		Optional<Item> opt=itemCrudRepo.findById(item.getItemId());
		
		if(opt.isPresent()) {
			itemCrudRepo.deleteById(item.getItemId());
		}
		else {
			throw new ProductNotFoundException("Product does not exist in your cart");
		}
		
		return "Product with "+item.getItemId()+" is deleted from your cart" ;
	}

	

	@Override
	public Item updateItem(Item item) {
		// TODO Auto-generated method stub
		
		return null;
	}




	@Override
	public Item addItem(Product product, int quantity) {
		// TODO Auto-generated method stub
		
		Item item=new Item();
		item.setItemPrice(product.getPrice()*quantity);
		item.setProduct(product);
		item.setRequiredQuantity(quantity);
		
		return addItem(item);
	}

	
	

}
