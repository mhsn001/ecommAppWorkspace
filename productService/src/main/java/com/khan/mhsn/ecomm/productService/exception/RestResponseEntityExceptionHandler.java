package com.khan.mhsn.ecomm.productService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.khan.mhsn.ecomm.productService.model.ErrorResponse;


@ControllerAdvice // we use this annotation for exception handling 
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	// this annotation is used whenever given exception (ProductServiceCustomeException) is thrown
	@ExceptionHandler(ProductServiceCustomeException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomeException exception){
		//Preparing error response
		ErrorResponse errResponse = new ErrorResponse()
				.builder()
				.errorMessage(exception.getMessage())
				.errorCode(exception.getErrorCode())
				.build();
		// once exception is caught here, we will return the response from here to the client. 
		return new ResponseEntity<>(errResponse, HttpStatus.NOT_FOUND);
	}
}
