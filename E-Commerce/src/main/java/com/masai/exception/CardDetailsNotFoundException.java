package com.masai.exception;

public class CardDetailsNotFoundException extends RuntimeException {
	
	public CardDetailsNotFoundException() {
	}
	
	public CardDetailsNotFoundException(String message) {
		super(message);
	}
}
