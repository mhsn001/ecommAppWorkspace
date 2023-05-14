package com.khan.mhsn.ecomm.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khan.mhsn.ecomm.orderService.entity.Order;
import com.khan.mhsn.ecomm.orderService.model.GetOrderResponse;
import com.khan.mhsn.ecomm.orderService.model.OrderRequest;
import com.khan.mhsn.ecomm.orderService.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderServiceController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "Hey " + name + " ! Greetings From Order service :)";
	}
	
	@PostMapping("/create")
	public ResponseEntity<Long> createOrder(@RequestBody OrderRequest orderRequest) {
		long orderId = orderService.createOrder(orderRequest);
		return new ResponseEntity<>(orderId,HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<GetOrderResponse> getOrderById(@PathVariable long orderId) {
		
	GetOrderResponse res =  orderService.getOrderById(orderId);
	
	return new ResponseEntity<>(res, HttpStatus.OK);
	
	}
	
	
	
}
