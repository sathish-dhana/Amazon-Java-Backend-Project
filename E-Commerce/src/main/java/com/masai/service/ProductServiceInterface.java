package com.masai.service;

import java.util.List;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;
import com.masai.beans.Seller;

public interface ProductServiceInterface {

	public Product addProduct(Seller seller, Product product);
	public Product addProduct(Product product);
	public List<Product> getAllProdcuts();

	public Product getProductById(Integer id);

	public List<Product> getProductsByCategory(ProductCategory cate);
	
	public Product reduceQuantity(Integer id, int quantityToReduce);
	
	public String deleteProduct(Integer productId);
}
