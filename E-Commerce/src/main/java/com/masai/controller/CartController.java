package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.beans.Login;
import com.masai.beans.User;
import com.masai.exception.LoginFailedException;
import com.masai.repository.CustomerCrudRepo;
import com.masai.repository.LoginCrudRepo;
import com.masai.service.CartService;
import com.masai.service.ItemServiceInterface;
import com.masai.service.LoginService;

@RestController
@RequestMapping("/ecommerce/customersPortal")
public class CartController {
	
	@Autowired
	private CustomerCrudRepo customerCrudRepo;
	
	@Autowired
	private LoginCrudRepo loginRepo;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemServiceInterface itemService;
	
	@PostMapping(value="/cart")
	public ResponseEntity<Cart> addToCart(@RequestParam("token") String token,@RequestBody Item item) {
		
			Login loggedUser=loginService.isTokenValid(token);
			
			
			Customer customer=customerCrudRepo.findByUserId(loggedUser.getUser().getUserId());
			Item savedItem=itemService.addItem(item);
			Cart savedCart=cartService.saveCart(customer, savedItem);
			return new ResponseEntity<>(savedCart, HttpStatus.ACCEPTED);
			

		
	}
}
