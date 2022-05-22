package com.masai.service;

import java.util.List;

import com.masai.beans.Product;
import com.masai.beans.Seller;
import com.masai.beans.UserDTO;

public interface SellerServiceInterface {
	public Seller addSeller(Seller seller);
	public String removeSeller(UserDTO userInfo);
	public String removeSellerById(Integer sellerId);
//	public String removeSellerByName(String sellerName);
	public List<Seller> viewAllSeller();
	public Seller updateSeller(UserDTO sellerInfo, Integer id);
	public Seller addProducts(Integer sellerId, Product product);
//	public Seller addProductsList(Integer sellerId, List<Product> product);
}
