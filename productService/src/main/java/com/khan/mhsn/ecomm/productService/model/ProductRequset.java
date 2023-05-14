package com.khan.mhsn.ecomm.productService.model;

import lombok.Data;

// A request coming from a client.
@Data
public class ProductRequset {
	
	private String name;
	private long price;
	private int quantity; 

}
