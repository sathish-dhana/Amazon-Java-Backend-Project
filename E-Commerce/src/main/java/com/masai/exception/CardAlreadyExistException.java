package com.masai.exception;

public class CardAlreadyExistException extends RuntimeException{
	
	public CardAlreadyExistException() {
	}
	
	public CardAlreadyExistException(String message) {
		super(message);
	}
}
