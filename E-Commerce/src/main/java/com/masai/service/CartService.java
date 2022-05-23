package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.repository.CartCrudRepo;
import com.masai.repository.CustomerCrudRepo;

@Service
public class CartService implements CartServiceInterface {

	@Autowired
	private CartCrudRepo cartCrudRepo;
	
	@Autowired
	private CustomerCrudRepo customerCrudRepo;
	
	@Override
	public Cart saveCart(Cart cart) {
		// TODO Auto-generated method stub
		
		Cart newCart=cartCrudRepo.findByCartId(cart.getCartId());
		
		//newCart does not exist simply save to DB
		if(newCart==null) {
			Cart savedCart=cartCrudRepo.save(cart);
			return savedCart;
		}
		else {
			
			for(Item itm : cart.getItems()) {
				
				newCart.getItems().add(itm);
			}
			
			cartCrudRepo.save(newCart);
			return newCart;
		}
	}

	
	public Cart saveCart(int customerId,Item item) {
		
		
		
		Customer customer=customerCrudRepo.findByUserId(customerId);
		
		
		
		try {
			Integer cartId=customer.getCart().getCartId();	
			Cart cart=cartCrudRepo.findByCartId(cartId);
			cart.getItems().add(item);
			cart.setCartTotal(cart.getCartTotal()+(double)item.getItemPrice());
			return cartCrudRepo.save(cart);
		}
		catch(Exception e) {
			Cart savedCart=new Cart();
			savedCart.setCustomer(customer);
			savedCart.getItems().add(item);
			savedCart.setCartTotal((double)item.getItemPrice());
			return cartCrudRepo.save(savedCart);
		}
		
		
		
	}
	
		
	
	
}
