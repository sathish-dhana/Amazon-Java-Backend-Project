package com.masai.exceptionHandler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import com.masai.exception.SellerNotFoundException;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(SellerNotFoundException.class)
	public void sellerHandler(HttpServletResponse response, SellerNotFoundException error) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), error.getMessage());
	}
}
