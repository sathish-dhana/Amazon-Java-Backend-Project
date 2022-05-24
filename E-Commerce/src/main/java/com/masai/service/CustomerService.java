package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Address;
import com.masai.beans.Card;
import com.masai.beans.Cart;
import com.masai.beans.Customer;
import com.masai.beans.Login;
import com.masai.beans.Seller;
import com.masai.beans.UserDTO;
import com.masai.exception.CustomerAlreadyExistsException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.SellerNotFoundException;
import com.masai.repository.CustomerCrudRepo;

@Service
public class CustomerService implements CustomerServiceInterface {
	
	@Autowired
	private CustomerCrudRepo customerCrudRepo;
	
	@Autowired
	private CardServiceInteface cardService;
	
	@Autowired
	private AddressServiceInterface addressService;
	
	@Override
	public Customer addCustomer(Customer customer) {
		Optional<Customer> opt = customerCrudRepo.findByUserName(customer.getUserName());
		
		if(opt.isEmpty()) {
			
			customer.setCart(new Cart());
			Customer savedCustomer = customerCrudRepo.save(customer);
			return savedCustomer;
		} else {
			throw new CustomerAlreadyExistsException("Customer with the given username already exists.");
		}
	}

	@Override
	public String removeCustomer(UserDTO userInfo) {
		
		Optional<Customer> customer = customerCrudRepo.findByUserName(userInfo.getUserName());
		
		if(customer.isPresent() && customer.get().getUserPassword().equals(userInfo.getUserPassword())) {
			
			customerCrudRepo.delete(customer.get());
			
		} else {
			
			throw new CustomerNotFoundException("username/password is wrong. Please provide the correct details to perform this operation");
			
		}
		
		return "Successfully deleted " + userInfo.getUserName() + "'s Account from the database";
		
	}

	@Override
	public List<Customer> viewAllCustomers() {
		List<Customer> customers = customerCrudRepo.findAll();
		if(customers.size() == 0) {
			throw new CustomerNotFoundException("No Customers registered on the portal!");
		}
		
		return customers;
	}

	@Override
	public Customer updateCustomer(UserDTO customerInfo, Integer id) {
		
		Optional<Customer> opt = customerCrudRepo.findById(id);
		
		if(opt.isPresent()) {
			Customer customer = opt.get();
			
			//Updating the email
			if(customerInfo.getEmail() != null) {
				customer.setEmail(customerInfo.getEmail());
			}
			
			//Updating the First Name
			if(customerInfo.getFirstName() != null) {
				customer.setFirstName(customerInfo.getFirstName());
			}
			
			//Updating the Last Name
			if(customerInfo.getLastName() != null) {
				customer.setLastName(customerInfo.getLastName());
			}
			
			//Updating the Mobile Number
			if(customerInfo.getMobileNumber() != null) {
				customer.setMobileNumber(customerInfo.getMobileNumber());
			}
			
			//Updating the User Name
			if(customerInfo.getUserName() != null) {
				customer.setUserName(customerInfo.getUserName());
			}
			
			//Updating the User Password
			if(customerInfo.getUserPassword() != null) {
				customer.setUserPassword(customerInfo.getUserPassword());
			}
			
			customerCrudRepo.save(customer);
			return customer;
		} else {
			throw new CustomerNotFoundException("No customer exists with the given id!");
		}

	}

	@Override
	public Customer findByUsernameAndPassword(String username, String password) {
		Optional<Customer> customer = customerCrudRepo.findByUserNameAndUserPassword(username, password);
		if(customer.isPresent()) {
			return customer.get();
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
	}
	
	@Override
	public Customer getCustomerById(Integer customerId) {
		
		Optional<Customer> opt = customerCrudRepo.findById(customerId);
		
		if(!opt.isEmpty()) {
			return opt.get();
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
	}

	@Override
	public Customer addCustomerCard(Integer customerId, Card card) {
		// TODO Auto-generated method stub
		Optional<Customer> getCustomer = customerCrudRepo.findById(customerId);
		
		if (getCustomer.isPresent()) {
			Card addCard = cardService.addCard(card);
			getCustomer.get().setCardDetails(card);
			Customer savedCustomer = customerCrudRepo.save(getCustomer.get());
			return savedCustomer;
		} else {
			throw new CustomerAlreadyExistsException("Customer with the given username already exists.");
		}
		
	}
	
	@Override
	public Customer addCustomerAddress(Integer customerId, Address address) {
		// TODO Auto-generated method stub
		Optional<Customer> getCustomer = customerCrudRepo.findById(customerId);
		
		if (getCustomer.isPresent()) {
			address.setUser(getCustomer.get());
			
			Address savedAddress = addressService.addAddress(address);
			
			getCustomer.get().getAddresses().add(address);
			
			return customerCrudRepo.save(getCustomer.get());
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
	}

	@Override
	public Customer persistCustomer(Integer customerID, Login login) {
		// TODO Auto-generated method stub
		Optional<Customer> temp = customerCrudRepo.findById(customerID);
		
		if (temp.isPresent()) {
			Customer customer = temp.get();
			customer.setLogin(login);
			customerCrudRepo.save(customer);
			return customer;
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
		
	}
	
	@Override
	public Customer removeCustomerAddress(Integer customerId, Integer addressId) {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerCrudRepo.findById(customerId);
		
		boolean flag = false;
		
		if (customer.isPresent()) {
			
			for (int i = 0; i < customer.get().getAddresses().size(); i++) {
				if (customer.get().getAddresses().get(i).getAddressId() == addressId)
					customer.get().getAddresses().remove(i);
			}
			
			Customer savedcustomer = customerCrudRepo.save(customer.get());
			
			addressService.deleteAddress(addressId);
			
			return savedcustomer;
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
	}

}
