package com.masai.service;

import com.masai.beans.Ordered;
import com.masai.beans.OrderDTO;

public interface OrderServiceInterface {

	public OrderDTO getOrderStatus(Integer customerId);
	public Ordered createOrder(int customerId, String lastFourDigitsOfCardUsed);
	public Ordered addOrder(Ordered order);
}
