package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Login;
import com.masai.beans.OrderDTO;
import com.masai.beans.Ordered;
import com.masai.beans.PlaceOrderDTO;
import com.masai.service.LoginServiceInterface;
import com.masai.service.OrderServiceInterface;

@RestController
@RequestMapping("/ecommerce/orderPortal")
public class OrderController {

	@Autowired
	private OrderServiceInterface orderServ;
	
	@Autowired
	private LoginServiceInterface loginService;
	
	//This endpoint gives the current status of the cart and prices 
	@GetMapping("/order")
	public ResponseEntity<OrderDTO> getOrders(@RequestParam String key){
		
		Login currentLogin = loginService.isTokenValid(key);
		
		Integer customerId = currentLogin.getUser().getUserId();
		
		OrderDTO order = orderServ.getOrderStatus(customerId);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<Ordered> placeOrder(@RequestParam String key, @RequestBody @Valid PlaceOrderDTO loginInfo) {
		Login currentLogin = loginService.isTokenValid(key);
		Integer customerId = currentLogin.getUser().getUserId();
		Ordered order = orderServ.placeOrder(customerId, loginInfo);
		return new ResponseEntity<Ordered>(order, HttpStatus.ACCEPTED);
	}
	
}
