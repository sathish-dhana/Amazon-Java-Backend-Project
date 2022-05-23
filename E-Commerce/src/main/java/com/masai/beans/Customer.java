package com.masai.beans;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends User{
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name = "card_Number")
	private Card cardDetails;
	
	@JsonIgnore
	public Card getCardDetails() {
		return this.cardDetails;
	}
	
}

