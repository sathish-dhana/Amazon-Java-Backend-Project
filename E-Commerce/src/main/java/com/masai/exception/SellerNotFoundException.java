package com.masai.exception;

public class SellerNotFoundException extends RuntimeException{
	
	public SellerNotFoundException() {
	}
	
	public SellerNotFoundException(String message) {
		super(message);
	}
}
