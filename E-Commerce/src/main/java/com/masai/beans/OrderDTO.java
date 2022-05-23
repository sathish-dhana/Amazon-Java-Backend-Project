package com.masai.beans;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

	List<Item> orderItems = new ArrayList<>();
	
	int cost;
	
	int gst;
	
	int deliveryCost;
	
	int totalCost;
	
}
