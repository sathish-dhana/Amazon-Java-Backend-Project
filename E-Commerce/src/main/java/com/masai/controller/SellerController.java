package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Customer;
import com.masai.beans.Seller;
import com.masai.service.SellerService;

@RestController
@RequestMapping("/ecommerce/sellerPortal")
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/seller")
	public ResponseEntity<Seller> addCustomer(@RequestBody Seller seller) {
		Seller addedSeller = sellerService.addSeller(seller);
		return new ResponseEntity(addedSeller, HttpStatus.CREATED);
	}
	
	
}
