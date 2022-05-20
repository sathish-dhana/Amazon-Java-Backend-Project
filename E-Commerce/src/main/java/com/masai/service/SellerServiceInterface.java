package com.masai.service;

import java.util.List;

import com.masai.beans.Seller;

public interface SellerServiceInterface {
	public Seller addSeller(Seller seller);
	public boolean removeSellerById(Seller seller);
	public List<Seller> viewAllSeller();
	public Seller updateSeller(Seller seller);
}
