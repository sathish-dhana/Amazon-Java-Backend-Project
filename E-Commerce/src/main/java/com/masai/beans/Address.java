	package com.masai.beans;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Integer addressId;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	//@Pattern(regexp="^[1-9]{1}[0-9]{2}\\\\s{0,1}[0-9]{3}$", message = "Only Valid for 6 digit indian pincode")
	private Integer pincode;
		
	@ManyToOne(cascade=CascadeType.ALL) 
	@JsonIgnoreProperties("addresses")
	private User user;
	 
}


