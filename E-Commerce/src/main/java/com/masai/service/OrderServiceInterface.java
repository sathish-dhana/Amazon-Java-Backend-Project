package com.masai.service;

import com.masai.beans.OrderDTO;
import com.masai.beans.Ordered;
import com.masai.beans.PlaceOrderDTO;

public interface OrderServiceInterface {

	public OrderDTO getOrderStatus(Integer customerId);
	
	public Ordered createOrder(int customerId, String lastFourDigitsOfCardUsed, int addressId);
	
	public Ordered addOrder(Ordered order);
	
	public Ordered placeOrder(int customerId, PlaceOrderDTO loginInfo);
	
}
