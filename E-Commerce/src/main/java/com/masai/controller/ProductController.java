package com.masai.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Product;
import com.masai.service.ProductServiceInterface;

@RestController
@RequestMapping("/ecommerce")
public class ProductController {

	@Autowired
	private ProductServiceInterface productServ;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		
		Product productSaved = productServ.addProduct(product);
		return new ResponseEntity<>(productSaved,HttpStatus.CREATED);
	}
	
}
