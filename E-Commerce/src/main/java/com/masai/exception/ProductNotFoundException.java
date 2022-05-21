package com.masai.exception;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException() {
		
	}
	
	public ProductNotFoundException(String message) {
		super(message);
	}
}
