package com.masai.service;

import java.util.List;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;
import com.masai.beans.ProductDTO;
import com.masai.beans.Seller;

public interface ProductServiceInterface {

	public Product addProduct(Seller seller, Product product);
	
	public Product addProduct(Product product);
	
	public Product getProductById(Integer id);
	
	public Product reduceQuantity(Integer id, int quantityToReduce);
	
	public String updateProductStatus(Integer productId);
	
	public String updateProduct(Integer productId, ProductDTO product);
	
	public Product updateProductStatusToOutOfStock(Integer productId);
	
	public Product updateProductStatusToUnAvaillable(Integer productId);
	
	public Product updateProductStatusToAvaillable(Integer productId);
	
	public List<Product> getAllProdcuts();
	
	public List<Product> getProductsByCategory(ProductCategory cate);
	
}
