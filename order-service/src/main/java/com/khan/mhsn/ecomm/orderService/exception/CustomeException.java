package com.khan.mhsn.ecomm.orderService.exception;

import lombok.Data;

@Data
public class CustomeException extends RuntimeException{

	private String errorCode;
	private int status; 
	
	public CustomeException(String message, String errorCode, int status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}
	
}


