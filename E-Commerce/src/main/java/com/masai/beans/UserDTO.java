package com.masai.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {
	
	@NotNull
	@Pattern(regexp="[a-z]{6}", message = "length must be 6 no caps and numbers allowed")
	private String userName;
	
	@NotNull
	@Pattern(regexp="[a-z0-9A-Z]{6}",message="length must be 6 no special characters")
	private String userPassword;
}
