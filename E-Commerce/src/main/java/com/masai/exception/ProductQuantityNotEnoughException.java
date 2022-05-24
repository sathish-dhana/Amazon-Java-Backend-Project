package com.masai.exception;

public class ProductQuantityNotEnoughException extends RuntimeException{

	public ProductQuantityNotEnoughException() {
		
	}
	
	public ProductQuantityNotEnoughException(String message) {
		super(message);
	}
}
