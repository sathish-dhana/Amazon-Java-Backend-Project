package com.masai.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "seller_id")
public class Seller extends User {
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("seller")
	private List<Product> products = new ArrayList<>();
	
}
