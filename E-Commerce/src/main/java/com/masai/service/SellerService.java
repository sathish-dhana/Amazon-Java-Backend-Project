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
import com.masai.beans.ProductStatus;
import com.masai.beans.Seller;
import com.masai.beans.UserDTO;
import com.masai.exception.AddressNotFoundException;
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
	
	
	
	//-------------------------------------------------------------------------//
	//	Adding seller into the database
	//-------------------------------------------------------------------------//
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
	
	
	//-------------------------------------------------------------------------//
	//	1. Passing user details as a DTO
	//	2. Validating the user DTO fields
	//	3. remove the seller if validation is successfull (or) throw SellerNotFoundException
	//-------------------------------------------------------------------------//
	@Override
	public String removeSeller(UserDTO userInfo) {
		
		Optional<Seller> seller = sellerCrudRepo.findByUserName(userInfo.getUserName());
		
		//checing the username & password
		if(seller.isPresent() && seller.get().getUserPassword().equals(userInfo.getUserPassword())) {
			sellerCrudRepo.delete(seller.get());
		} else {
			throw new SellerNotFoundException("username/password is wrong. Please provide the correct details to perform this operation");
		}
		
		return "Successfully deleted " + userInfo.getUserName() + "'s Account from the database";
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. View All seller in the system
	//	2. if not seller is added before, throw SellerNotFoundException
	//-------------------------------------------------------------------------//
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
	
	
	//-------------------------------------------------------------------------//
	//	1. TO update the seller detials
	// 	2. Get the seller info as a DTO with sellerId
	//	3. checking & updating the sellerInfo only if the DTO fields are not null
	//-------------------------------------------------------------------------//
	@Override
	public Seller updateSeller(UserDTO sellerInfo, Integer sellerId) {
		
		Optional<Seller> opt = sellerCrudRepo.findById(sellerId);
		
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

	
	
	//-------------------------------------------------------------------------//
	//	1. Add product method with sellerId & 
	// 	2. Get the seller info as a DTO with sellerId
	//	3. checking if the updating the sellerInfo only if the DTO fields are not null
	//-------------------------------------------------------------------------//
	@Override
	public Seller addProducts(Integer sellerId, Product product) {
		// TODO Auto-generated method stub
		
		//We only accept product if it has quantity > 1, so changing the status to AVAILLABLE
		product.setProductStatus(ProductStatus.AVAILABLE);
		
		Optional<Seller> checkSeller = sellerCrudRepo.findById(sellerId);
		Seller updatedSeller = checkSeller.get();
		
		//we will need to let customer to add address only if he provides address
		if(updatedSeller.getAddresses().isEmpty())
			throw new AddressNotFoundException("Add the address first to add the products");
		
		//adds product in seller list
		updatedSeller.getProducts().add(product);
		
		//provides bi-directional relationship
		productService.addProduct(updatedSeller, product);
		
		if (checkSeller.isPresent()) {
			sellerCrudRepo.save(updatedSeller);
		} else {
			throw new SellerNotFoundException("seller not found");
		}
		
		return updatedSeller;
	}
			
	
	
	//-------------------------------------------------------------------------//
	//	1. To find the seller by username & password
	//-------------------------------------------------------------------------//
	@Override
	public Seller findByUsernameAndPassword(String username, String password) {
		
		Optional<Seller> seller = sellerCrudRepo.findByUserNameAndUserPassword(username, password);
		
		if(seller.isPresent()) {
			return seller.get();
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}
	}
	
	
	
	//-------------------------------------------------------------------------//
	//	1. Used in login module to persist seller
	//-------------------------------------------------------------------------//
	@Override
	public Seller persistSeller(Integer sellerId, Login login) {
		// TODO Auto-generated method stub
		
		Optional<Seller> temp = sellerCrudRepo.findById(sellerId);
		Seller seller = temp.get();
		seller.setLogin(login);
		sellerCrudRepo.save(seller);
		return seller;
	}
	
	
	
	//-------------------------------------------------------------------------//
	//	1. Add address to the given sellerId
	// 	2. Adding address bi-directionally.
	//-------------------------------------------------------------------------//
	@Override
	public Seller addSellerAddress(Integer sellerId, Address address) {
		// TODO Auto-generated method stub
		Optional<Seller> getSeller = sellerCrudRepo.findById(sellerId);
		
		if (getSeller.isPresent()) {
			//setting the referece of seller in his address
			address.setUser(getSeller.get());
			
			//saving the address with seller reference
			Address savedAddress = addressService.addAddress(address);
			
			//adding the address in seller address to get bi-directional relationship
			getSeller.get().getAddresses().add(address);
			
			return sellerCrudRepo.save(getSeller.get());
		} else {
			throw new SellerAlreadyExistException("Seller with the given username already exists.");
		}
	}
	
	
	
	//-------------------------------------------------------------------------//
	//	1. Updating the product status.
	//-------------------------------------------------------------------------//
	@Override
	public Seller updateProductStatus(Integer sellerId, Integer productId) {
		// TODO Auto-generated method stub
		Optional<Seller> seller = sellerCrudRepo.findById(sellerId);
		
		boolean flag = false;
		
		if (seller.isPresent()) {
			
			//if we find the product with given Id we are making the product status UNAVAILLABLE
			for (int i = 0; i < seller.get().getProducts().size(); i++) {
				if (seller.get().getProducts().get(i).getProductId() == productId)
					seller.get().getProducts().get(i).setProductStatus(ProductStatus.UNAVAILABLE);
			}
			
			//To persist the seller in database
			Seller saveSeller = sellerCrudRepo.save(seller.get());
			
			productService.updateProductStatus(productId);
			
			return saveSeller;
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}
	}

	
	
	//-------------------------------------------------------------------------//
	//	1. TO update the seller product details
	// 	2. Get the product info as a DTO with sellerId & productId
	//	3. Updating the seller product Info only if the DTO fields are not null
	//-------------------------------------------------------------------------//
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
			
			Seller saveSeller = sellerCrudRepo.save(seller.get());
			
			productService.updateProduct(productId, product);
			
			return saveSeller;
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}

	}
	
	
	
	//-------------------------------------------------------------------------//
	//	1. TO remove the seller address
	// 	2. Get the seller Id & address Id that should be deleted
	//-------------------------------------------------------------------------//
	@Override
	public Seller removeSellerAddress(Integer sellerId, Integer addressId) {
		// TODO Auto-generated method stub
		Optional<Seller> seller = sellerCrudRepo.findById(sellerId);
		
		boolean flag = false;
		
		if (seller.isPresent()) {
			
			//check if the seller address is same as provided address Id
			for (int i = 0; i < seller.get().getAddresses().size(); i++) {
				if (seller.get().getAddresses().get(i).getAddressId() == addressId) {
					seller.get().getAddresses().remove(i);
					flag = true;
				}
			}
			
			//if the address Id is not present throw exception
			if (!flag) {
				throw new AddressNotFoundException("Address Not Found");
			}
			
			Seller saveSeller = sellerCrudRepo.save(seller.get());
			
			addressService.deleteAddress(addressId);
			
			return saveSeller;
		} else {
			throw new SellerNotFoundException("No such seller. Please check the provided details.");
		}
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. TO view all the products in posted by the seller
	//-------------------------------------------------------------------------//
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
