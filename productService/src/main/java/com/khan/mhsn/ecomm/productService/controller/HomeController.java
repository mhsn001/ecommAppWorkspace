package com.khan.mhsn.ecomm.productService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khan.mhsn.ecomm.productService.model.ProductRequset;
import com.khan.mhsn.ecomm.productService.model.ProductResponse;
import com.khan.mhsn.ecomm.productService.service.ProductService;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/products")
@Log4j2
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/hello/{name}")
	public String sayHello(@PathVariable("name") String name) {
		return "Hello "+ name +" :)";
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/add")
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequset request){
		long pId = productService.addProduct(request);
		return new ResponseEntity<>(pId, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
	@GetMapping("{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long id){
		
		ProductResponse res = productService.getProductById(id);
		return new ResponseEntity<ProductResponse>(res,HttpStatus.OK);
	}
	
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam("quantity") long quantity){
		
		log.info("API Controller : Reduce API" );
		productService.reduceQuantity(productId, quantity);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
