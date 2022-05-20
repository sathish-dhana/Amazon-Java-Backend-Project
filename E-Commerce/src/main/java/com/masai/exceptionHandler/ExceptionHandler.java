package com.masai.exceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import com.masai.exception.ErrorDetails;
import com.masai.exception.SellerAlreadyExistException;
import com.masai.exception.SellerNotFoundException;

import net.bytebuddy.asm.Advice.Return;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(SellerNotFoundException.class)
	public ResponseEntity<ErrorDetails> sellerHandler(SellerNotFoundException error, WebRequest webRequest) {
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(SellerAlreadyExistException.class)
	public ResponseEntity<ErrorDetails> sellerHandler(SellerAlreadyExistException error, WebRequest webRequest) {
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
}
