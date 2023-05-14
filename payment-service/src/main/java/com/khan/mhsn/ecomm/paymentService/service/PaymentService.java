package com.khan.mhsn.ecomm.paymentService.service;

import com.khan.mhsn.ecomm.paymentService.model.PaymentRequest;

public interface PaymentService {

	long doPayment(PaymentRequest request);

}
