package com.masai.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;
import com.masai.beans.Seller;
import com.masai.exception.ProductNotFoundException;
import com.masai.repository.ProductCrudRepo;

import lombok.Data;

@Data
@Service
public class ProductService implements ProductServiceInterface{

	@Autowired
	private ProductCrudRepo productRepo;
	
	
	//TO ADD PRODUCT
		@Override
		public Product addProduct(Product product) {
			
			Product savedProduct = productRepo.save(product);
			return savedProduct;
		}
	
	
	//TO ADD PRODUCT
	@Override
	public Product addProduct(Seller seller, Product product) {
		
		product.setSeller(seller);
		Product savedProduct = productRepo.save(product);
		return savedProduct;
		
	}

	//TO GET ALL PRODCUTS
	@Override
	public List<Product> getAllProdcuts() {
	
		List<Product> allProducts = productRepo.findAll();
		if(allProducts.isEmpty()) {
			throw new ProductNotFoundException("No product available");
		}
		return allProducts;
	}

	// TO GET PRODUCT BY ID
	@Override
	public Product getProductById(Integer id) {
		
		Product product = productRepo.findById(id).get();
		if(product == null)
			throw new ProductNotFoundException("No product found in the given id");
		return product;
	}

	//TO GET ALL PRODUCTS BY CATEGORY
	@Override
	public List<Product> getProductsByCategory(ProductCategory cate) {
		
		List<Product> productsCategory = productRepo.findByCategory(cate);
		if(productsCategory.isEmpty())
			throw new ProductNotFoundException("No product found in this category");
		
		return productsCategory;
	}

	//TO REDUCE QUANTITY AFTER PRODUCTS PURCHASED
	@Override
	public Product reduceQuantity(Integer id, int quantityToReduce) {
		
		Product updatedProduct = productRepo.findById(id).get();
		updatedProduct.setQuantity(updatedProduct.getQuantity() - quantityToReduce);
		return updatedProduct;
	}
	
	
	
}