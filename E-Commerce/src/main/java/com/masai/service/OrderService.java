package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Customer;
import com.masai.beans.OrderDTO;

@Service
public class OrderService implements OrderServiceInterface{

	@Autowired
	private CustomerService customerServ;
	
	@Override
	public OrderDTO getAllOrders(Integer customerId) {
		
		Customer customer = customerServ.getCustomerById(customerId);
		
		OrderDTO order = new OrderDTO();
		
		//order.setOrderItems(customer.getCart()            //set up the list here
		
		
		
	}

}
