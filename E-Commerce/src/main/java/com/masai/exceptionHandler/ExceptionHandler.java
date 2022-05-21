package com.masai.exceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.masai.exception.CustomerAlreadyExistsException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.ErrorDetails;
import com.masai.exception.ProductNotFoundException;
import com.masai.exception.SellerAlreadyExistException;
import com.masai.exception.SellerNotFoundException;

import net.bytebuddy.asm.Advice.Return;

@ControllerAdvice
public class ExceptionHandler {
	
	//-------------------------------------------------------------------------//
	//									SELLER EXCEPTIONS
	//-------------------------------------------------------------------------//
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
	
	
	//-------------------------------------------------------------------------//
	//									CUSTOMER EXCEPTIONS
	//-------------------------------------------------------------------------//
	
	@org.springframework.web.bind.annotation.ExceptionHandler(CustomerAlreadyExistsException.class) 
	public ResponseEntity<ErrorDetails> customerExists(CustomerAlreadyExistsException error, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorDetails> sellerHandler(CustomerNotFoundException error, WebRequest webRequest) {
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	
	//-------------------------------------------------------------------------//
	//									VALIDATION EXCEPTIONS
	//-------------------------------------------------------------------------//
	
	//This exception takes care of the invalid inputs
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		
		ErrorDetails err = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation Error", me.getBindingResult().getFieldError().getDefaultMessage());
	
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	
	//This exception takes care of the invalid URIs
	@org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandler(NoHandlerFoundException noHandler) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "NOT FOUND", "Not a Valid URL");
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	//Product Exceptions
	@org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDetails> productHandler(ProductNotFoundException error, WebRequest webRequest){
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
}
