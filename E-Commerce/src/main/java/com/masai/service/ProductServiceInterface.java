package com.masai.service;

import java.util.List;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;

public interface ProductServiceInterface {

	public Product addProduct(Product product);

	public List<Product> getAllProdcuts();

	public Product getProductById(Integer id);

	public List<Product> getProductsByCategory(ProductCategory cate);
	
	public Product reduceQuantity(Integer id, int quantityToReduce);
}
