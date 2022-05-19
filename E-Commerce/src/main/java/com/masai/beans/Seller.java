package com.masai.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.tomcat.jni.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seller extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sellerId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> products;
	
}
