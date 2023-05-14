package com.khan.mhsn.ecomm.orderService.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
	
	private long productId;
		
	private long quantity;

	private String orderStatus;
	
	private PaymentMode paymentMode;
	
	private long amount;

}
