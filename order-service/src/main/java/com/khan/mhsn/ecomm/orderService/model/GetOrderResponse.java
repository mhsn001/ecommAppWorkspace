package com.khan.mhsn.ecomm.orderService.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOrderResponse {

	private long orderId;
	private Instant orderDate;
	private String orderStatus;
	private long amount;
	private ProductDetails productDetails; 
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ProductDetails {
		
		private String productName;
		private long productId;
		private long price;
		private long quantity; 

	}
}
