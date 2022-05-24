package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Card;
import com.masai.beans.Customer;
import com.masai.beans.Item;
import com.masai.beans.Order;
import com.masai.beans.OrderDTO;
import com.masai.beans.Product;
import com.masai.beans.ProductCategory;
import com.masai.exception.NoProductFoundInCart;

@Service
public class OrderService implements OrderServiceInterface{

	@Autowired
	private CustomerService customerServ;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public OrderDTO getOrderStatus(Integer customerId) {
		

		//getting customer using customerID
		Customer customer = customerServ.getCustomerById(customerId);
		
		OrderDTO order = new OrderDTO();
		
		//getting the cart list of that customer
		List<Item> cartList = customer.getCart().getItems();
		
		if(cartList.isEmpty())
			throw new NoProductFoundInCart("No Products are found in the cart to order");
		
		order.setOrderItems(cartList);         
	
		for(Item element : cartList) {
			
			ProductCategory category = element.getProduct().getCategory();
			
			Product product = element.getProduct();
				
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
	
	//Create endpoint for this and it will call the empty cart method
	@Override
	public Order createOrder(int customerId, String lastFourDigitsOfCardUsed) {
		
		//Calling the order status method to get the details of the order cost
		OrderDTO orderDetails = this.getOrderStatus(customerId);
		
		//Emptying the cart and getting the list that is to be added to the order
		List<Item> itemsOrdered = cartService.sendToOrder(customerId);
		
		//Creating the order using the cart of the user and the orderDetails
		Order order = new Order();
		
		//Creation of order
		order.setCardUsedForPayment("XXXXXXXX".concat(lastFourDigitsOfCardUsed));
		order.setDeliveryCharge(orderDetails.getDeliveryCost());
		order.setGst(orderDetails.getGst());
		order.setItemsCost(orderDetails.getCost());
//		order.setOrderDate(LocalDate.now());
//		order.setOrderedItems(itemsOrdered);
		order.setTotalAmount(orderDetails.getTotalCost());
		
		
		//Send the order to the customer to be added to the order list
		
		return null;
	}
	
	

}
