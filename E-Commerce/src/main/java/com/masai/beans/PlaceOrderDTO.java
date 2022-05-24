package com.masai.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDTO {
	
	@Pattern(regexp="[0-9]{3}", message = "CVV must be 3 digits")
	@NotNull
	private String cvv;
	
	@NotNull
	private int addressId;
	
}
