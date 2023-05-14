package com.khan.mhsn.ecomm.orderService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.khan.mhsn.ecomm.orderService.model.ErrorResponse;



@ControllerAdvice // we use this annotation for exception handling 
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	// this annotation is used whenever given exception (ProductServiceCustomeException) is thrown
	@ExceptionHandler(CustomeException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomeException exception){
		//Preparing error response
		ErrorResponse errResponse = new ErrorResponse()
				.builder()
				.errorMessage(exception.getMessage())
				.errorCode(exception.getErrorCode())
				.build();
		// once exception is caught here, we will return the response from here to the client. 
		return new ResponseEntity<>(errResponse, HttpStatus.valueOf(exception.getStatus()));
	}
}
