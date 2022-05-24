package com.masai.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

	@NotNull(message = "Product Id must Not be Null")
	Integer productId;
	
	
	@Min(0)
	@NotNull(message = "Product Id must not be Null")
	Integer requiredQuantity;
}
