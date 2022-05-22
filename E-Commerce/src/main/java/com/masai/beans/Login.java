package com.masai.beans;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Login {
	
	String apiKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
	
	LocalDateTime keyExpiryDate = LocalDateTime.now().plusHours(4);
	
	LoginStatus status = LoginStatus.LOGGED_IN;
	
}
