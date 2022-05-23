package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Login;
import com.masai.beans.OrderDTO;
import com.masai.service.LoginService;
import com.masai.service.LoginServiceInterface;
import com.masai.service.OrderService;
import com.masai.service.OrderServiceInterface;

@RestController
@RequestMapping("/ecommerce/orderPortal")
public class OrderController {

	@Autowired
	private OrderServiceInterface orderServ;
	
	@Autowired
	private LoginServiceInterface loginService;
	
	@GetMapping("/order")
	public ResponseEntity<OrderDTO> getOrders(@RequestParam String key){
		
		Login currentLogin = loginService.isTokenValid(key);
		
		Integer customerId = currentLogin.getUser().getUserId();
		
		OrderDTO order = orderServ.getAllOrders(customerId);
		
		return null;
		
	}
	
	
	
}
