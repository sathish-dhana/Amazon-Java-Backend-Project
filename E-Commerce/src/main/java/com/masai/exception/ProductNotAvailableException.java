package com.masai.exception;

public class ProductNotAvailableException extends RuntimeException{

	public ProductNotAvailableException() {
		
	}
	
	public ProductNotAvailableException(String message) {
		super(message);
	}
}
