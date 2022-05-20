package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Customer;
import com.masai.beans.Seller;
import com.masai.service.SellerService;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/sellerPortal")
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	//To Ad seller
	@PostMapping("/seller")
	public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
		Seller addedSeller = sellerService.addSeller(seller);
		return new ResponseEntity(addedSeller, HttpStatus.CREATED);
	}
	
	//To delete seller
	@DeleteMapping("/seller/{sellerId}")
	public ResponseEntity<Seller> removeSeller(@RequestBody @PathVariable("sellerId") Integer sellerId) {
		String deleteSeller = sellerService.removeSellerById(sellerId);
		return new ResponseEntity(deleteSeller, HttpStatus.ACCEPTED);
	}
		
	//To list all user
	@GetMapping("/seller")
	public ResponseEntity<Seller> viewSellers() {
		List<Seller> sellerList = sellerService.viewAllSeller();
		return new ResponseEntity(sellerList, HttpStatus.FOUND);
	}
	
	@PutMapping("/seller")
	public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller) {
		Seller updatedseller = sellerService.updateSeller(seller);
		return new ResponseEntity(updatedseller, HttpStatus.ACCEPTED);
	}
	
}
