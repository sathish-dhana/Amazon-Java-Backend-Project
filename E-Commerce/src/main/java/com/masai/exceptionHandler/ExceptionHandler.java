package com.masai.exceptionHandler;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.masai.exception.AddressAlreadyExistException;
import com.masai.exception.AddressNotFoundException;
import com.masai.exception.CardAlreadyExistException;
import com.masai.exception.CardDetailsNotFoundException;
import com.masai.exception.CustomerAlreadyExistsException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.ErrorDetails;
import com.masai.exception.InvalidLoginKeyException;
import com.masai.exception.LoginFailedException;
import com.masai.exception.NoProductFoundInCart;
import com.masai.exception.ProductAlreadyFoundException;
import com.masai.exception.ProductNotFoundException;
import com.masai.exception.ProductQuantityNotEnoughException;
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
	//									CARD EXCEPTIONS
	//-------------------------------------------------------------------------//
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(CardAlreadyExistException.class)
	public ResponseEntity<ErrorDetails> cardAlreadyExists(CardAlreadyExistException error, WebRequest webRequest) {
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request as card already exists with the customer", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(CardDetailsNotFoundException.class)
	public ResponseEntity<ErrorDetails> cardDetailsNotFound(CardDetailsNotFoundException error, WebRequest webRequest) {
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request as card details not found with the customer", error.getMessage());
		
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
	
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorDetails> httpRequestMethodException(HttpRequestMethodNotSupportedException exception){
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED.value(), "Check the http method", exception.getMessage());
		
		return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorDetails> missingServletRequestParameterException(MissingServletRequestParameterException exception){
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED.value(), "comes from MissingServletRequestParameterException", exception.getMessage());
		
		return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
	}

	//-------------------------------------------------------------------------//
	//									PRODUCT EXCEPTIONS
	//-------------------------------------------------------------------------//
	@org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDetails> productHandler(ProductNotFoundException error, WebRequest webRequest){
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	//-------------------------------------------------------------------------//
	//									ADDRESS EXCEPTIONS
	//-------------------------------------------------------------------------//
	
	@org.springframework.web.bind.annotation.ExceptionHandler(AddressAlreadyExistException.class)
	public ResponseEntity<ErrorDetails> sellerHandler(AddressAlreadyExistException error, WebRequest webRequest) {
			
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
			
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
		
	@org.springframework.web.bind.annotation.ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ErrorDetails> sellerHandler(AddressNotFoundException error, WebRequest webRequest) {
			
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
			
    //-------------------------------------------------------------------------//
	//									LOGIN EXCEPTIONS
	//-------------------------------------------------------------------------//
	
	@org.springframework.web.bind.annotation.ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<ErrorDetails> sellerHandler(LoginFailedException error, WebRequest webRequest) {
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidLoginKeyException.class)
	public ResponseEntity<ErrorDetails> sellerHandler(InvalidLoginKeyException error, WebRequest webRequest) {
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	
	//-------------------------------------------------------------------------//
    //									ORDER EXCEPTIONS
	//-------------------------------------------------------------------------//
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NoProductFoundInCart.class)
	public ResponseEntity<ErrorDetails> orderHandler(NoProductFoundInCart error, WebRequest webRequest){
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.BAD_REQUEST );
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ProductQuantityNotEnoughException.class)
	public ResponseEntity<ErrorDetails> quantityNotEnough(ProductQuantityNotEnoughException error, WebRequest webRequest){
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.BAD_REQUEST );
	}
	
	//-------------------------------------------------------------------------//
    //									CART EXCEPTIONS
	//-------------------------------------------------------------------------//
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ProductAlreadyFoundException.class)
	public ResponseEntity<ErrorDetails> orderHandler(ProductAlreadyFoundException error, WebRequest webRequest){
		
		ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", error.getMessage());
		
		return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.BAD_REQUEST);
	}
}
