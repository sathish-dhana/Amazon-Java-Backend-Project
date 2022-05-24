package com.masai.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shipmentTrackId;
	
	private Address shippedFrom;
	
	private Address shippedTo;
	
	private LocalDate expectedDate;
		
}
