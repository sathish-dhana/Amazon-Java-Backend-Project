package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Seller;
import com.masai.exception.SellerAlreadyExistException;
import com.masai.exception.SellerNotFoundException;
import com.masai.repository.SellerCrudRepo;

import lombok.Data;

@Service
@Data
public class SellerService implements SellerServiceInterface {
	
	@Autowired
	private SellerCrudRepo sellerCrudRepo;
	
	@Override
	public Seller addSeller(Seller seller) {
		// TODO Auto-generated method stub
		
		Optional<Seller> checkSeller = sellerCrudRepo.findByUserName(seller.getUserName());
		Seller savedSeller = null;
		
		if (!checkSeller.isPresent()) {
			savedSeller = sellerCrudRepo.save(seller);
		} else {
			throw new SellerAlreadyExistException("Seller Already Exist, check name & password");
		}
		
		return savedSeller;
	}
	
	@Override
	public String removeSellerById(Integer sellerId) {
		// TODO Auto-generated method stub
		
		//checking if seller exist or not
		Optional<Seller> checkSeller = sellerCrudRepo.findById(sellerId);
		String message = "Not deleted";
		
		if (checkSeller.isPresent()) {
			sellerCrudRepo.deleteById(sellerId);
			message = "Deleted seller \nseller name : " + checkSeller.get().getUserName() + "\nId : " + checkSeller.get().getUserId();
		} else {
			throw new SellerNotFoundException("seller not found");
		}

		return message;
	}
	
	@Override
	public String removeSellerByName(String sellerName) {
		// TODO Auto-generated method stub
		
		//checking if seller exist or not
		Optional<Seller> checkSeller = sellerCrudRepo.findByUserName(sellerName);
		String message = "Not deleted";
		
		if (checkSeller.isPresent()) {
			sellerCrudRepo.deleteById(checkSeller.get().getUserId());
			message = "Deleted seller \nseller name : " + checkSeller.get().getUserName() + "\nId : " + checkSeller.get().getUserId();
		} else {
			throw new SellerNotFoundException("seller not found");
		}
		
		return message;
	}
	
	@Override
	public List<Seller> viewAllSeller() {
		// TODO Auto-generated method stub
		
		List<Seller> sellers = sellerCrudRepo.findAll();
		
		//if no seller found in database
		if (sellers.size() <= 0) {
			throw new SellerNotFoundException("No seller added");
		}
		
		return sellers;
	}
	

	@Override
	public Seller updateSeller(Seller seller) {
		// TODO Auto-generated method stub
		
		Optional<Seller> opt= sellerCrudRepo.findById(seller.getUserId());
		Seller savedSeller = null;
		
		if(opt.isPresent()) {
		//Student existingStudentObj= opt.get();
		savedSeller = sellerCrudRepo.save(seller);
		
		}else {
			throw new SellerNotFoundException("sellar not found");
		}
		return savedSeller;
	}
		
}
