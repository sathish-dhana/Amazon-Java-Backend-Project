package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.exception.ProductQuantityNotEnoughException;
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
	
	@Autowired
	private ProductServiceInterface productService;
	
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


	//WORKING FINE
	//WHAT IT DOES IS..  IT REMOVES THE ITEM FROM THE USER CART BUT KEEPS IT IN THE 
	//DATABASE.
	//TODO CHECK QUANTITY BEFORE PLACING ORDER
	@Override
	public List<Item> sendToOrder(int customerId) throws ProductQuantityNotEnoughException{
		
		Integer cartId = customerCrudRepo.findByUserId(customerId).getCart().getCartId();
		
		Cart cart = cartCrudRepo.findByCartId(cartId);
		
		List<Item> orders = new ArrayList<>(cart.getItems());
		
		//Checking if the quantity of ALL the products is enough to proceed.
		//If not, throw Not enough quantity exception with proper message.
		for(Item item: orders) {
			if(item.getProduct().getQuantity() < item.getRequiredQuantity()) {
				throw new ProductQuantityNotEnoughException("Only " + item.getProduct().getQuantity() + " units of " + item.getProduct().getProductName() + " are available. Required quantity is: " + item.getRequiredQuantity() + ". Cannot Proceed with purchase.");
			} 
		}
		
		//If the quantity if enough, we reduce the quantity
		for(Item item: orders) {
			productService.reduceQuantity(item.getProduct().getProductId(), item.getRequiredQuantity());
		}
		
		cart.getItems().clear();
		
		cartCrudRepo.save(cart);
		
		return orders;
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
