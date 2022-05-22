package com.masai.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	//this regular expression will accept master card & VISA 
	@Id
	@Pattern(regexp="^(?:5[1-5]|2(?!2([01]|20)|7(2[1-9]|3))[2-7])\\d{14}$")
	private Integer cardNumber;
	
	//this regular will accept MM/DD
	@Pattern(regexp="/^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$/")
	private String validDate;
	
}
