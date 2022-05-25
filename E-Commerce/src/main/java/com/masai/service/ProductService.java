package com.masai.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;
import com.masai.beans.ProductDTO;
import com.masai.beans.ProductStatus;
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
	
		List<Product> allAvaillableProducts = productRepo.findAllByProductStatus(ProductStatus.AVAILABLE);
		List<Product> allOutOfStockProducts = productRepo.findAllByProductStatus(ProductStatus.OUT_OF_STOCK);
		
		allAvaillableProducts.addAll(allOutOfStockProducts);
		
		if(allAvaillableProducts.isEmpty()) {
			throw new ProductNotFoundException("No product available");
		}
		return allAvaillableProducts;
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
	public synchronized Product reduceQuantity(Integer id, int quantityToReduce) {
		
		Product updatedProduct = productRepo.findById(id).get();
		
		updatedProduct.setQuantity(updatedProduct.getQuantity() - quantityToReduce);
		
		if(updatedProduct.getQuantity() == 0) {
			updatedProduct.setProductStatus(ProductStatus.OUT_OF_STOCK);
		}
		
		productRepo.save(updatedProduct);
		return updatedProduct;
	}

	
	//TO update the product status if the seller dont want to post
	@Override
	public String updateProductStatus(Integer productId) {
		// TODO Auto-generated method stub
		Optional<Product> findproduct = productRepo.findById(productId);
		
		if (findproduct.isPresent()) {
			
			//checked if the product exist and change its status
			findproduct.get().setQuantity(0);
			findproduct.get().setProductStatus(ProductStatus.UNAVAILABLE);
			productRepo.save(findproduct.get());
			
			return "product with ID : " + productId + " product status updated.";
		} else {
			throw new ProductNotFoundException("No product found in the given id");
		}
		
	}

	
	//update the product detail here to maintain bi-directional mapping
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
			throw new ProductNotFoundException("No product found with the given id");
		}
	}

	
	//To update the product status to Out Of Stock
	@Override
	public Product updateProductStatusToOutOfStock(Integer productId) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepo.findById(productId);
		
		if(product.isPresent()) {
			product.get().setProductStatus(ProductStatus.OUT_OF_STOCK);
			return product.get();
		} else {
			throw new ProductNotFoundException("No product found with the given id");
		}
			
		
	}

	
	//To update the product status to Un-Availlable
	@Override
	public Product updateProductStatusToUnAvaillable(Integer productId) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepo.findById(productId);
		
		if(product.isPresent()) {
			product.get().setProductStatus(ProductStatus.UNAVAILABLE);
			return product.get();
		} else {
			throw new ProductNotFoundException("No product found with the given id");
		}
	}

	
	//To update the product status to Availlable
	@Override
	public Product updateProductStatusToAvaillable(Integer productId) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepo.findById(productId);
		
		if(product.isPresent()) {
			product.get().setProductStatus(ProductStatus.AVAILABLE);
			return product.get();
		} else {
			throw new ProductNotFoundException("No product found with the given id");
		}
	}
		
}