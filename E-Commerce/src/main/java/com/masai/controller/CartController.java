package com.masai.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.exception.CustomerNotFoundException;
import com.masai.repository.CustomerCrudRepo;
import com.masai.service.CartService;
import com.masai.service.ItemServiceInterface;

@RestController
public class CartController {
	
	@Autowired
	private CustomerCrudRepo customerCrudRepo;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemServiceInterface itemService;
	
	@PostMapping(value="/{customerId}/cart")
	public ResponseEntity<Cart> addToCart(@PathVariable int customerId,@RequestBody Item item) {
		
		Optional<Customer> optCustomer=customerCrudRepo.findById(customerId);
		
		if(optCustomer.isPresent()) {
				
				
					
					Item savedItem=itemService.addItem(item);
					Cart savedCart=cartService.saveCart(customerId, savedItem);
					return new ResponseEntity<>(savedCart, HttpStatus.ACCEPTED);
				
		}
		else {
		
			throw new CustomerNotFoundException("Customer Does Not Exist");
		}
		
	}
}
