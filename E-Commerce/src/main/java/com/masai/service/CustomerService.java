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
import com.masai.beans.Ordered;
import com.masai.beans.Seller;
import com.masai.beans.UserDTO;
import com.masai.exception.AddressNotFoundException;
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
	
	@Autowired
	private OrderServiceInterface orderService;
	
	
	
	//-------------------------------------------------------------------------//
	//	1. TO add customer details
	// 	2. Get the seller Id & address Id that should be deleted
	//-------------------------------------------------------------------------//
	@Override
	public Customer addCustomer(Customer customer) {
		
		Optional<Customer> opt = customerCrudRepo.findByUserName(customer.getUserName());

		if(opt.isEmpty()) {
			
			//setting the cart
			customer.setCart(new Cart());
			
			Customer savedCustomer = customerCrudRepo.save(customer);
			
			return savedCustomer;
		} else {
			throw new CustomerAlreadyExistsException("Customer with the given username already exists.");
		}
	}

	
	//-------------------------------------------------------------------------//
	//	1. TO remove the Customer
	//-------------------------------------------------------------------------//
	@Override
	public String removeCustomer(UserDTO userInfo) {
		
		Optional<Customer> customer = customerCrudRepo.findByUserName(userInfo.getUserName());
		
		//if the customer is present & login detials match we delete the customer
		if(customer.isPresent() && customer.get().getUserPassword().equals(userInfo.getUserPassword())) {
			customerCrudRepo.delete(customer.get());
		} else {
			throw new CustomerNotFoundException("username/password is wrong. Please provide the correct details to perform this operation");
		}
		
		return "Successfully deleted " + userInfo.getUserName() + "'s Account from the database";
		
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. TO View all the customers in the table
	//-------------------------------------------------------------------------//
	@Override
	public List<Customer> viewAllCustomers() {
		
		List<Customer> customers = customerCrudRepo.findAll();
		
		//If no customer found throw the exception
		if(customers.size() == 0) {
			throw new CustomerNotFoundException("No Customers registered on the portal!");
		}
		
		return customers;
	}

	
	//-------------------------------------------------------------------------//
	//	1. TO update the customers in the table
	//	2. Get the DTO of customer details & customer Id
	//	3. check the fields and update the customer if the fields are not null
	//-------------------------------------------------------------------------//
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
	
	
	
	//-------------------------------------------------------------------------//
	//	1. To find the customer
	//	2. Get the username & password to check
	//-------------------------------------------------------------------------//
	@Override
	public Customer findByUsernameAndPassword(String username, String password) {
		
		Optional<Customer> customer = customerCrudRepo.findByUserNameAndUserPassword(username, password);
		
		//check if present return
		if(customer.isPresent()) {
			return customer.get();
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
		
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. To find the customer by the Id
	//-------------------------------------------------------------------------//
	@Override
	public Customer getCustomerById(Integer customerId) {
		
		Optional<Customer> opt = customerCrudRepo.findById(customerId);
		
		if(!opt.isEmpty()) {
			return opt.get();
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
	}

	
	//-------------------------------------------------------------------------//
	//	1. To add customer card details
	//	2. Get the customer Id & Card details
	//	3. provide Uni-directional mapping
	//-------------------------------------------------------------------------//
	@Override
	public Customer addCustomerCard(Integer customerId, Card card) {
		// TODO Auto-generated method stub
		Optional<Customer> getCustomer = customerCrudRepo.findById(customerId);
		
		if (getCustomer.isPresent()) {
			
			//save the card
			Card addCard = cardService.addCard(card);
			
			//set this card with the customer Id provided
			getCustomer.get().setCardDetails(card);
			
			//save the customer
			Customer savedCustomer = customerCrudRepo.save(getCustomer.get());
			
			return savedCustomer;
		} else {
			throw new CustomerAlreadyExistsException("Customer with the given username already exists.");
		}
		
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. To add customer address details
	//	2. Get the customer Id & address details
	//	3. provide Bi-directional mapping
	//-------------------------------------------------------------------------//
	@Override
	public Customer addCustomerAddress(Integer customerId, Address address) {
		// TODO Auto-generated method stub
		Optional<Customer> getCustomer = customerCrudRepo.findById(customerId);
		
		if (getCustomer.isPresent()) {
			
			//get the customer
			address.setUser(getCustomer.get());
			
			//save the address to database
			Address savedAddress = addressService.addAddress(address);
			
			//Add the address to the customers list of address
			getCustomer.get().getAddresses().add(address);
			
			return customerCrudRepo.save(getCustomer.get());
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. To add Login the customer 
	//	2. Get the customer Id & Login details
	//-------------------------------------------------------------------------//
	@Override
	public Customer persistCustomer(Integer customerID, Login login) {
		// TODO Auto-generated method stub
		Optional<Customer> temp = customerCrudRepo.findById(customerID);
		
		if (temp.isPresent()) {
			
			Customer customer = temp.get();
			
			//set customer with the login details provided
			customer.setLogin(login);
			
			//save the customer
			customerCrudRepo.save(customer);
			
			return customer;
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
		
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. To remove customer address details
	//	2. Get the customer Id & address Id
	//	3. remove Bi-directional mapping
	//-------------------------------------------------------------------------//
	@Override
	public Customer removeCustomerAddress(Integer customerId, Integer addressId) {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerCrudRepo.findById(customerId);
		
		boolean flag = false;
		
		if (customer.isPresent()) {
			
			//Iterate into the list of address and remove it
			for (int i = 0; i < customer.get().getAddresses().size(); i++) {
				if (customer.get().getAddresses().get(i).getAddressId() == addressId) {
					customer.get().getAddresses().remove(i);
					flag = true;
				}
			}
			
			//If the address id not found throw the exception
			if (!flag) {
				throw new AddressNotFoundException("Address Not Found");
			}
			
			//save the customer & address to get bi directional 
			Customer savedcustomer = customerCrudRepo.save(customer.get());
			
			addressService.deleteAddress(addressId);
			
			return savedcustomer;
		} else {
			throw new CustomerNotFoundException("No such customer. Please check the provided details.");
		}
	}
	
	
	//-------------------------------------------------------------------------//
	//	1. To add customer orders
	//	2. Get the customer Id & order
	//  3. Provide Bi-directional mapping
	//-------------------------------------------------------------------------//
	@Override
	public Customer addCustomerOrder(Integer customerId, Ordered order) {

		Optional<Customer> getCustomer = customerCrudRepo.findById(customerId);
		
		if (getCustomer.isPresent()) {
			
			//Persist the order
			Ordered addOrder = orderService.addOrder(order);
			
			//Add the orders to the customer Id provided
			getCustomer.get().getOrders().add(addOrder);
			
			//save the customer to table
			Customer savedCustomer = customerCrudRepo.save(getCustomer.get());
			
			return savedCustomer;
		} else {
			throw new CustomerAlreadyExistsException("Wrong customer ID, check your login key");
		}
		
	}

}
