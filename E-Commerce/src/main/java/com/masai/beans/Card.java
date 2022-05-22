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
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	//this regular expression will accept master card & VISA  example : 4155279860457
	@Id
	@Pattern(regexp="^4[0-9]{12}(?:[0-9]{3})?$")
	private String cardNumber;
	
	//this regular will accept MM/DD
	@Pattern(regexp= "^(0[1-9]|1[0-2])\\/?([0-9]{2})$" )
	private String validDate;
	
}
