package com.masai.beans;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
	
	private String shippedFrom;
	
	private String shippedTo;
	
	private LocalDate expectedDate;
		
}
