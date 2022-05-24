package com.masai.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

	private List<Item> orderItems = new ArrayList<>();
	
	private double cost;
	
	private double gst;
	
	private int deliveryCost;
	
	private double totalCost;
	
	//private Map<Product, Integer> productDetails = new HashMap<>();
	
	private List<Product> prodcuts = new ArrayList<>();
	
}
