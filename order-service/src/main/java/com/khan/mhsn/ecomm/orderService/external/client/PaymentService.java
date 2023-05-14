package com.khan.mhsn.ecomm.orderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.khan.mhsn.ecomm.orderService.model.PaymentRequest;


@FeignClient(name ="PAYMENT-SERVICE/payment")
public interface PaymentService {

	
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request);
	
}
