package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.beans.User;
import com.masai.exception.ProductNotFoundException;
import com.masai.repository.CartCrudRepo;
import com.masai.repository.CustomerCrudRepo;
import com.masai.repository.ItemCrudRepo;

@Service
public class CartService implements CartServiceInterface {

	@Autowired
	private CartCrudRepo cartCrudRepo;
	
	@Autowired
	private CustomerCrudRepo customerCrudRepo;
	
	@Autowired
	private ItemCrudRepo itemCrudRepo;
	@Override
	public Cart saveCart(Customer customer,Item item) {
		
		
			Integer cartId=(customer.getCart()).getCartId();	
			Cart cart=cartCrudRepo.findByCartId(cartId);
			cart.getItems().add(item);

			cart.setCartTotal((cart.getCartTotal()==null) ? 0+(double)item.getItemPrice():cart.getCartTotal().doubleValue() +(double)item.getItemPrice());

			return cartCrudRepo.save(cart);
	
	}


	@Override
	public List<Item> getAllItem(Cart cart) {
		// TODO Auto-generated method stub
		Optional<Cart> optCart=cartCrudRepo.findById(cart.getCartId());
		
		return optCart.get().getItems();	
	}


	@Override
	public Cart alterCart(Customer customer, Item item) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public Cart alterCart(Customer customer, Item item) {
//		// TODO Auto-generated method stub
//		
//		Optional<Item> optItem=itemCrudRepo.findById(item.getItemId());
//		
//		if(optItem.isPresent()) {
//			
//		}
//		else {
//			throw new ProductNotFoundException("Product Does not found in your cart");
//		}
//	}
	
	
	
}
