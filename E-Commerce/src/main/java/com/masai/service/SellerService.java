package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Seller;
import com.masai.exception.SellerNotFoundException;
import com.masai.repository.SellerCrudRepo;

@Service
public class SellerService implements SellerServiceInterface {
	
	@Autowired
	private SellerCrudRepo sellerCrudRepo;
	
	
	@Override
	public Seller addSeller(Seller seller) {
		// TODO Auto-generated method stub
		Seller savedSeller = sellerCrudRepo.save(seller);
		return savedSeller;
	}

	@Override
	public boolean removeSellerById(Seller seller) {
		// TODO Auto-generated method stub
		
		//checking if seller exist or not
		Optional<Seller> checkSeller = sellerCrudRepo.findById(seller.getUserId());
		boolean flag = false;
		
		if (checkSeller.isPresent()) {
			sellerCrudRepo.delete(seller);
			flag = true;
		} else {
			throw new SellerNotFoundException("sellar not found");
		}

		return flag;
	}

	@Override
	public List<Seller> viewAllSeller() {
		// TODO Auto-generated method stub
		
		List<Seller> sellers = sellerCrudRepo.findAll();
		
		//if no seller found in database
		if (sellers.size() <= 0) {
			throw new SellerNotFoundException("sellar not found");
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
