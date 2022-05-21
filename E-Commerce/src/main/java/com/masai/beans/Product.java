package com.masai.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotNull(message = "Product name cannot be empty")
	private String productName;
	
	
	private String description;
	
	@Min(value = 1, message = "Product price should not be 0")
	private int price;
	
	private Double rating;
	
	@Min(value = 1, message = "Minimum quantity should be 1")
	private int quantity;
	
	private ProductCategory category;
	
	
	 @ManyToOne(cascade = CascadeType.ALL) 
	 private User seller;
	 
	
}
