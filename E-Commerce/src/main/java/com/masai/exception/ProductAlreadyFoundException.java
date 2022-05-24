package com.masai.exception;

public class ProductAlreadyFoundException extends RuntimeException{

	public ProductAlreadyFoundException() {
		
	}
	
	public ProductAlreadyFoundException(String message) {
		super(message);
	}
}
