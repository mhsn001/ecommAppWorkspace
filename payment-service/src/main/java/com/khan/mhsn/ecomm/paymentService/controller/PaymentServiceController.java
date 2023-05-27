package com.khan.mhsn.ecomm.paymentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khan.mhsn.ecomm.paymentService.model.PaymentRequest;
import com.khan.mhsn.ecomm.paymentService.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentServiceController {

	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request){
		
		Long paymentId = paymentService.doPayment(request);
				
		return new ResponseEntity<>(paymentId,HttpStatus.OK);
	}
}
