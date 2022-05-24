package com.masai.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
	
	private DeliveryStatus deliveryStatus = DeliveryStatus.IN_TRANSIT;
		
}
