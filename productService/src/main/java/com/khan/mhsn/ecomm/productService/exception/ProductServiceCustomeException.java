package com.khan.mhsn.ecomm.productService.exception;

import lombok.Data;
// Custom runtime exception 
@Data
public class ProductServiceCustomeException extends RuntimeException{

	private String errorCode;
	
	public ProductServiceCustomeException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
