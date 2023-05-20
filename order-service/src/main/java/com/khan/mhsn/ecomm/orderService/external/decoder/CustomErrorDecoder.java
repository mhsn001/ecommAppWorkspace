package com.khan.mhsn.ecomm.orderService.external.decoder;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khan.mhsn.ecomm.orderService.exception.CustomeException;
import com.khan.mhsn.ecomm.orderService.external.response.ErrorResponse;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder{
	
	@Override
	public Exception decode(String methodKey, Response response) {
		
		ObjectMapper mapper = new ObjectMapper();
		log.error("Facing issus while calling following API : {}", response.request().url());
		log.info("Decoding the error");
			
	
		try {
			ErrorResponse responseError = mapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			log.info("Decoded the error");
			return new CustomeException(responseError.getErrorMessage(), responseError.getErrorCode(), response.status());
		} catch (IOException e) {
			log.warn("Error decoding failed, there is some issue");
			throw new CustomeException("Internal Server Error : "+e.getMessage(), "INTERNAL_SERVER_ERROR", 500);
		}
		
		
	}

	
	
}
