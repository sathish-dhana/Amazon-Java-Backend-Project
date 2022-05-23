package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;
import com.masai.beans.ProductDTO;
import com.masai.beans.Seller;
import com.masai.exception.ProductNotFoundException;
import com.masai.repository.ProductCrudRepo;

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


	@Override
	public String deleteProduct(Integer productId) {
		// TODO Auto-generated method stub
		Optional<Product> findproduct = productRepo.findById(productId);
		findproduct.get().setSeller(null);
		
		if (findproduct.isPresent()) {
			productRepo.deleteById(productId);
			return "product with ID : " + productId + " deleted.";
		} else {
			throw new ProductNotFoundException("No product found in the given id");
		}
		
	}


	@Override
	public String updateProduct(Integer productId, ProductDTO product) {
		// TODO Auto-generated method stub
		Optional<Product> findproduct = productRepo.findById(productId);
		
		if(product.getProductName() != null) {
			findproduct.get().setProductName(product.getProductName());
		}
		
		//Updating the First Name
		if(product.getDescription() != null) {
			findproduct.get().setDescription(product.getDescription());
		}
		
		//Updating the Last Name
		if(product.getPrice() != null) {
			findproduct.get().setPrice(product.getPrice());
		}
		
		//Updating the Mobile Number
		if(product.getQuantity() != null) {
			findproduct.get().setQuantity(product.getQuantity());
		}
		
		//Updating the Mobile Number
		if(product.getCategory() != null) {
			findproduct.get().setCategory(product.getCategory());
		}
		
		if (findproduct.isPresent()) {
			productRepo.save(findproduct.get());
			return "product with ID : " + productId + " updated.";
		} else {
			throw new ProductNotFoundException("No product found in the given id");
		}
	}
	
	
	
}