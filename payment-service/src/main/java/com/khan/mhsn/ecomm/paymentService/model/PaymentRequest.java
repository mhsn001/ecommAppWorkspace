package com.khan.mhsn.ecomm.paymentService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

	private long orderId;
	private long amount; 
	private String referenceNum;
	private PaymentMode paymentMode;
	
	
	
	
}
