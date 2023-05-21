package com.khan.mhsn.ecomm.orderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khan.mhsn.ecomm.orderService.exception.CustomeException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@FeignClient(name ="PRODUCT-SERVICE/products")
public interface ProductService {

	
	
	// Circuit breaker annotation should be specific to each api because return type of fallback method should be same as api return type
	@PutMapping("/reduceQuantity/{id}")
	@CircuitBreaker(name = "order-service", fallbackMethod = "fallbackReduceQuantity")
	ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam("quantity") long quantity);
	
	
	// return type should be same as daPayment(..) api
	default ResponseEntity<Void> fallbackReduceQuantity(Exception e) {
		throw new CustomeException("Product service is down !", "UNAVAILABLE",500 );
	}
}
