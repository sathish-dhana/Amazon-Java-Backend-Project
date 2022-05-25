package com.masai.service;

import java.util.List;

import com.masai.beans.Address;
import com.masai.beans.Login;
import com.masai.beans.Product;
import com.masai.beans.ProductDTO;
import com.masai.beans.Seller;
import com.masai.beans.UserDTO;

public interface SellerServiceInterface {
	
	public Seller addSeller(Seller seller);
	
	public String removeSeller(UserDTO userInfo);
	
	public Seller updateSeller(UserDTO sellerInfo, Integer id);
	
	public Seller addProducts(Integer sellerId, Product product);
	
	public Seller findByUsernameAndPassword(String username, String password);
	
	public Seller persistSeller(Integer sellerId, Login login);
	
	public Seller addSellerAddress(Integer sellerId, Address address);
	
	public Seller updateProducts(Integer sellerId, Integer productId, ProductDTO product);
	
	public Seller updateProductStatus(Integer sellerId, Integer productId);
	
	public Seller removeSellerAddress(Integer sellerId, Integer addressId);
	
	public List<Product> viewAllProductsBySeller(Integer sellerId);
	
	public List<Seller> viewAllSeller();
	
}
