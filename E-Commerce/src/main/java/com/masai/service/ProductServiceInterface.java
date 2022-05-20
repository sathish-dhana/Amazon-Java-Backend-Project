package com.masai.service;

import java.util.List;

import com.masai.beans.Product;

public interface ProductServiceInterface {

	public Product addProduct(Product product);

	public List<Product> getAllProdcuts();

	public Product getProductById(Integer id);

}
