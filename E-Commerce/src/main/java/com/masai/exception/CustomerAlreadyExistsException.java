package com.masai.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
	
	public CustomerAlreadyExistsException() {
	}
	
	public CustomerAlreadyExistsException(String message) {
		super(message);
	}
}
