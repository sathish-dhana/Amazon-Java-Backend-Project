package com.masai.exception;

public class LoginFailedException extends RuntimeException {
	public LoginFailedException (String message) {
		super(message);
	}
}
