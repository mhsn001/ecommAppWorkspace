package com.khan.mhsn.ecomm.orderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.khan.mhsn.ecomm.orderService.exception.CustomeException;
import com.khan.mhsn.ecomm.orderService.model.PaymentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@FeignClient(name ="PAYMENT-SERVICE/payment")
public interface PaymentService {

	
	// Circuit breaker annotation should be specific to each api because return type of fallback method should be same as api return type
	@CircuitBreaker(name = "order-service", fallbackMethod = "fallbackDoPayment")
	@PostMapping("/")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request);
	
	// return type should be same as daPayment(..) api
	default ResponseEntity<Long> fallbackDoPayment(Exception e) {
		throw new CustomeException("Payment service is down !", "UNAVAILABLE",500 );
	}
	
}
