package com.masai.service;

import com.masai.beans.Order;
import com.masai.beans.OrderDTO;

public interface OrderServiceInterface {

	public OrderDTO getOrderStatus(Integer customerId);
	public Order createOrder(int customerId, String lastFourDigitsOfCardUsed);
}
