package com.masai.beans;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Customer_Details")
public class Customer {
	
	
//	private Cart cart;
	
	@Embedded
	private Card cardDetails;
	
	
}

