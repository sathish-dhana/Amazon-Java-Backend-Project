package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.beans.User;
import com.masai.repository.CartCrudRepo;
import com.masai.repository.CustomerCrudRepo;

@Service
public class CartService implements CartServiceInterface {

	@Autowired
	private CartCrudRepo cartCrudRepo;
	
	@Autowired
	private CustomerCrudRepo customerCrudRepo;
	
	@Override
	public Cart saveCart(Customer customer,Item item) {
		
		
			Integer cartId=(customer.getCart()).getCartId();	
			Cart cart=cartCrudRepo.findByCartId(cartId);
			cart.getItems().add(item);

			cart.setCartTotal((cart.getCartTotal()==null) ? 0+(double)item.getItemPrice():cart.getCartTotal().doubleValue() +(double)item.getItemPrice());

			return cartCrudRepo.save(cart);
	
	}
	
	
	
}
