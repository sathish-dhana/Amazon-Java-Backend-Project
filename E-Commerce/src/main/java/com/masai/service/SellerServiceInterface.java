package com.masai.service;

import java.util.List;

import com.masai.beans.Seller;

public interface SellerServiceInterface {
	public Seller addSeller(Seller seller);
	public String removeSellerById(Integer sellerId);
	public List<Seller> viewAllSeller();
	public Seller updateSeller(Seller seller);
}
