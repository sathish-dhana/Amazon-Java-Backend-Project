package com.masai.service;

import java.util.List;

import com.masai.beans.Product;
import com.masai.beans.Seller;

public interface SellerServiceInterface {
	public Seller addSeller(Seller seller);
	public String removeSellerById(Integer sellerId);
	public String removeSellerByName(String sellerName);
	public List<Seller> viewAllSeller();
	public Seller updateSeller(Seller seller);
	public Seller addProducts(Integer sellerId, Product product);
//	public Seller updateSellerName(String sellerName);
}
