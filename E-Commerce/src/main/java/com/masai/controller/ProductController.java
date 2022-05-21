package com.masai.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;
import com.masai.service.ProductServiceInterface;

@RestController
@RequestMapping("/ecommerce/productsPortal")
public class ProductController {

	@Autowired
	private ProductServiceInterface productServ;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
		
		Product productSaved = productServ.addProduct(product);
		return new ResponseEntity<>(productSaved,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		List<Product> allProducts = productServ.getAllProdcuts();
		return new ResponseEntity<List<Product>>(allProducts,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id ){
		
		Product product = productServ.getProductById(id);
		
		return new ResponseEntity<>(product,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/category/{cate}")
	public ResponseEntity<List<Product>> getProdcutByCategory(@PathVariable("cate") ProductCategory cate ){
	
		List<Product> allProdcutsCategory = productServ.getProductsByCategory(cate);
		
		return new ResponseEntity<List<Product>>(allProdcutsCategory, HttpStatus.ACCEPTED);		
	}
	
}
