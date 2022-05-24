package com.masai.beans;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Login {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int loginId;
	
	private String apiKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
	
	private LocalDateTime keyExpiryDate = LocalDateTime.now().plusHours(4);
	
	private LoginStatus status = LoginStatus.LOGGED_IN;
	
	@JsonIgnoreProperties("login")
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	public void newLogin() {
		this.apiKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
		this.keyExpiryDate = LocalDateTime.now().plusHours(4);
		this.status = LoginStatus.LOGGED_IN;
	}
	
	public void revokeLogin() {
		this.apiKey = null;
		this.keyExpiryDate = null;
		this.status = LoginStatus.LOGGED_OUT;
	}
	
}
