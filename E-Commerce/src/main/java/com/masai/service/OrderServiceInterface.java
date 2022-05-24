package com.masai.service;

import com.masai.beans.OrderDTO;

public interface OrderServiceInterface {

	OrderDTO getOrderStatus(Integer customerId);

}
