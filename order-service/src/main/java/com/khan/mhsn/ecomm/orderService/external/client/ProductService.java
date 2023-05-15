package com.khan.mhsn.ecomm.orderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khan.mhsn.ecomm.orderService.exception.CustomeException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name ="PRODUCT-SERVICE/products")
public interface ProductService {

	
	@PutMapping("/reduceQuantity/{id}")
	ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam("quantity") long quantity);
	
	
	
	default void fallback(Exception e) {
		throw new CustomeException("Product service is down !", "UNAVAILABLE",500 );
	}
}
