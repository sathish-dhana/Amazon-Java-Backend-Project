package com.masai.beans;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	private Integer cardNumber;
	
	private LocalDate validDate;
	
	
}
