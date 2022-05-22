package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;

@Repository
public interface ProductCrudRepo extends JpaRepository<Product, Integer>{

	
	public List<Product> findByCategory(ProductCategory category);
	
	public Product findByProductName(String productName);
}
