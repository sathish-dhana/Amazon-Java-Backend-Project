package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Address;
import com.masai.beans.Customer;
import com.masai.beans.Login;
import com.masai.beans.Product;
import com.masai.beans.ProductDTO;
import com.masai.beans.Seller;
import com.masai.beans.UserDTO;
import com.masai.exception.CustomerAlreadyExistsException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.ProductNotFoundException;
import com.masai.exception.SellerAlreadyExistException;
import com.masai.exception.SellerNotFoundException;
import com.masai.repository.SellerCrudRepo;

import lombok.Data;

@Service
@Data
public class SellerService implements SellerServiceInterface {
	
	@Autowired
	private SellerCrudRepo sellerCrudRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AddressServiceInterface addressService;
	
	@Override
	public Seller addSeller(Seller seller) {
		// TODO Auto-generated method stub
		
		Optional<Seller> checkSeller = sellerCrudRepo.findByUserName(seller.getUserName());
		Seller savedSeller = null;
		
		if (!checkSeller.isPresent()) {
			savedSeller = sellerCrudRepo.save(seller);
		} else {
			throw new SellerAlreadyExistException("Seller Already Exist, try different username & password");
		}
		
		return savedSeller;
	}
	
	@Override
	public String removeSeller(UserDTO userInfo) {
		
		Optional<Seller> seller = sellerCrudRepo.findByUserName(userInfo.getUserName());
		
		if(seller.isPresent() && seller.get().getUserPassword().equals(userInfo.getUserPassword())) {
			
			sellerCrudRepo.delete(seller.get());
			
		} else {
			
			throw new SellerNotFoundException("username/password is wrong. Please provide the correct details to perform this operation");
			
		}
		
		return "Successfully deleted " + userInfo.getUserName() + "'s Account from the database";
		
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

//	@Override
//	public String removeSellerByName(String sellerName) {
//		// TODO Auto-generated method stub
//		
//		//checking if seller exist or not
//		Optional<Seller> checkSeller = sellerCrudRepo.findByUserName(sellerName);
//		String message = "Not deleted";
//		
//		if (checkSeller.isPresent()) {
//			sellerCrudRepo.deleteById(checkSeller.get().getUserId());
//			message = "Deleted seller \nseller name : " + checkSeller.get().getUserName() + "\nId : " + checkSeller.get().getUserId();
//		} else {
//			throw new SellerNotFoundException("seller not found");
//		}
//		
//		return message;
//	}
	
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
	public Seller updateSeller(UserDTO sellerInfo, Integer id) {
		
		Optional<Seller> opt = sellerCrudRepo.findById(id);
		
		if(opt.isPresent()) {
			Seller seller = opt.get();
			
			//Updating the email
			if(sellerInfo.getEmail() != null) {
				seller.setEmail(sellerInfo.getEmail());
			}
			
			//Updating the First Name
			if(sellerInfo.getFirstName() != null) {
				seller.setFirstName(sellerInfo.getFirstName());
			}
			
			//Updating the Last Name
			if(sellerInfo.getLastName() != null) {
				seller.setLastName(sellerInfo.getLastName());
			}
			
			//Updating the Mobile Number
			if(sellerInfo.getMobileNumber() != null) {
				seller.setMobileNumber(sellerInfo.getMobileNumber());
			}
			
			//Updating the User Name
			if(sellerInfo.getUserName() != null) {
				seller.setUserName(sellerInfo.getUserName());
			}
			
			//Updating the User Password
			if(sellerInfo.getUserPassword() != null) {
				seller.setUserPassword(sellerInfo.getUserPassword());
			}
			
			sellerCrudRepo.save(seller);
			return seller;
		} else {
			throw new SellerNotFoundException("No seller exists with the given id!");
		}
	}

	@Override
	public Seller addProducts(Integer sellerId, Product product) {
		// TODO Auto-generated method stub
		Optional<Seller> checkSeller = sellerCrudRepo.findById(sellerId);
		Seller updatedSeller = checkSeller.get();
		updatedSeller.getProducts().add(product);
		
		productService.addProduct(updatedSeller, product);
		
		if (checkSeller.isPresent()) {
			sellerCrudRepo.save(updatedSeller);
		} else {
			throw new SellerNotFoundException("seller not found");
		}
		return updatedSeller;
	}
			
	@Override
	public Seller findByUsernameAndPassword(String username, String password) {
		Optional<Seller> seller = sellerCrudRepo.findByUserNameAndUserPassword(username, password);
		if(seller.isPresent()) {
			return seller.get();
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}
	}
	
	@Override
	public Seller persistCustomer(Integer customerID, Login login) {
		// TODO Auto-generated method stub
		Optional<Seller> temp = sellerCrudRepo.findById(customerID);
		Seller seller = temp.get();
		seller.setLogin(login);
		sellerCrudRepo.save(seller);
		return seller;
	}
	
	@Override
	public Seller addSellerAddress(Integer sellerId, Address address) {
		// TODO Auto-generated method stub
		Optional<Seller> getSeller = sellerCrudRepo.findById(sellerId);
		
		if (getSeller.isPresent()) {
			address.setUser(getSeller.get());
			
			Address savedAddress = addressService.addAddress(address);
			
			getSeller.get().getAddresses().add(address);
			
			return sellerCrudRepo.save(getSeller.get());
		} else {
			throw new SellerAlreadyExistException("Seller with the given username already exists.");
		}
	}

	@Override
	public Seller removeProduct(Integer sellerId, Integer productId) {
		// TODO Auto-generated method stub
		Optional<Seller> seller = sellerCrudRepo.findById(sellerId);
		
		boolean flag = false;
		
		if (seller.isPresent()) {
			
			for (int i = 0; i < seller.get().getProducts().size(); i++) {
				if (seller.get().getProducts().get(i).getProductId() == productId)
					seller.get().getProducts().remove(i);
			}
			
			Seller sel = sellerCrudRepo.save(seller.get());
			
			productService.deleteProduct(productId);
			
			return sel;
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}
	}

	@Override
	public Seller updateProducts(Integer sellerId, Integer productId, ProductDTO product){
		// TODO Auto-generated method stub
		Optional<Seller> seller = sellerCrudRepo.findById(sellerId);
		
		boolean flag = false;
		
		if (seller.isPresent()) {
			
			for (int i = 0; i < seller.get().getProducts().size(); i++) {
				if (seller.get().getProducts().get(i).getProductId() == productId) {
					
					//Updating the email
					if(product.getProductName() != null) {
						seller.get().getProducts().get(i).setProductName(product.getProductName());
					}
					
					//Updating the First Name
					if(product.getDescription() != null) {
						seller.get().getProducts().get(i).setDescription(product.getDescription());
					}
					
					//Updating the Last Name
					if(product.getPrice() != null) {
						seller.get().getProducts().get(i).setPrice(product.getPrice());
					}
					
					//Updating the Mobile Number
					if(product.getQuantity() != null) {
						seller.get().getProducts().get(i).setQuantity(product.getQuantity());
					}
					
					//Updating the Mobile Number
					if(product.getCategory() != null) {
						seller.get().getProducts().get(i).setCategory(product.getCategory());
					}
				}
			}
			
			Seller sel = sellerCrudRepo.save(seller.get());
			
			productService.updateProduct(productId, product);
			
			return sel;
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}

	}
	
	@Override
	public Seller removeSellerAddress(Integer sellerId, Integer addressId) {
		// TODO Auto-generated method stub
		Optional<Seller> seller = sellerCrudRepo.findById(sellerId);
		
		boolean flag = false;
		
		if (seller.isPresent()) {
			
			for (int i = 0; i < seller.get().getAddresses().size(); i++) {
				if (seller.get().getAddresses().get(i).getAddressId() == addressId)
					seller.get().getAddresses().remove(i);
			}
			
			Seller sel = sellerCrudRepo.save(seller.get());
			
			addressService.deleteAddress(addressId);
			
			return sel;
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}
	}

	@Override
	public List<Product> viewAllProductsBySeller(Integer sellerId) {
		
		Optional<Seller> seller = sellerCrudRepo.findById(sellerId);
		
		if (seller.isPresent()) {
			
			return seller.get().getProducts();
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}
	}
	
	
}
