package com.masai.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

	private List<Item> orderItems = new ArrayList<>();
	
	private double cost;
	
	private double gst;
	
	private double deliveryCost;
	
	private double totalCost;
	
}
