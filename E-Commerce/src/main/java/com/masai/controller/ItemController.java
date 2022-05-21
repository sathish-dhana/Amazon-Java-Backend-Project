package com.masai.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Item;
import com.masai.beans.Product;
import com.masai.exception.ProductNotFoundException;
import com.masai.repository.ProductCrudRepo;
import com.masai.service.ItemServiceInterface;
import com.masai.service.ProductService;
import com.masai.service.ProductServiceInterface;

@RestController
public class ItemController {

	@Autowired
	private ItemServiceInterface itemService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/item")
	public ResponseEntity<Item> addItem(@RequestBody Item item){
		
		Optional<Product> productCheck=productService.getProductRepo().findById(item.getProduct().getProductId());
		
		if(productCheck.isPresent()) {
			
			//Setting the item Price
			item.setPrice(item.getProduct().getPrice()*item.getQuantity());
			
			
			Item newItem=itemService.addItem(item);
			return new ResponseEntity<>(newItem,HttpStatus.ACCEPTED);
		}
		else {
			throw new ProductNotFoundException("Product does not exist");
		}
	}
}
