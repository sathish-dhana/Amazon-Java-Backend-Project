package com.masai.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.beans.OrderDTO;
import com.masai.beans.Product;
import com.masai.beans.ProductCategory;

@Service
public class OrderService implements OrderServiceInterface{

	@Autowired
	private CustomerService customerServ;
	
	@Override
	public OrderDTO getAllOrders(Integer customerId) {
		

		//Customer customer = customerServ.getCustomerById(customerId);
		
		//OrderDTO order = new OrderDTO();
		
		//order.setOrderItems(customer.getCart()            //set up the list here
//		return null;

		//getting customer using customerID
		Customer customer = customerServ.getCustomerById(customerId);
		
		OrderDTO order = new OrderDTO();
		
		//getting the cart list of that customer
		List<Item> cartList = customer.getCart().getItems();
		
		order.setOrderItems(cartList);         
	
		for(Item element : cartList) {
			
			ProductCategory category = element.getProduct().getCategory();
			
			Product product = element.getProduct();
			
			//adding all the product details in list to show it in api
			List<Product> list = order.getProdcuts();
			list.add(product);
			order.setProdcuts(list);
			
			//calculating gst for each categories
			if(category == ProductCategory.ELECTRONICS) {
				
				Double itemGST = element.getProduct().getPrice() * 0.18;
	
				order.setGst(order.getGst() + itemGST);
			}else if(category == ProductCategory.CLOTHES) {
				
				Double itemGST = element.getProduct().getPrice() * 0.12;
				
				order.setGst(order.getGst() + itemGST);
				
			}else if(category == ProductCategory.BOOKS) {
				
				Double itemGST = element.getProduct().getPrice() * 0.05;
				
				order.setGst(order.getGst() + itemGST);
			}
			
			
		}
		
		
		//cost of all items excluding gst, delivery
		double total = customer.getCart().getCartTotal();
		order.setCost(total);
		
		//setting delivery cost
		order.setDeliveryCost(40);
		
		
		//final cost paid by customer
		order.setTotalCost(total + order.getGst() + order.getDeliveryCost());

		
		return order;
		
	}

}
