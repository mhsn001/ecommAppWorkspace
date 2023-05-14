package com.khan.mhsn.ecomm.productService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Response sending to the client.
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	
	private String productName;
	private long productId;
	private long price;
	private long quantity; 

}
