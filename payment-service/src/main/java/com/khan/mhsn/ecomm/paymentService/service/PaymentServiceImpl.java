package com.khan.mhsn.ecomm.paymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khan.mhsn.ecomm.paymentService.entity.TransactionDetails;
import com.khan.mhsn.ecomm.paymentService.model.PaymentRequest;
import com.khan.mhsn.ecomm.paymentService.repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired 
	TransactionDetailsRepository transactionDetailsRepository;

	@Override
	public long doPayment(PaymentRequest request) {

		log.info("Initiating Payment for order : {}", request.getOrderId());
		
		TransactionDetails td = new TransactionDetails().builder()
								.paymentDate(Instant.now())
								.paymentMode(request.getPaymentMode().name())
								.paymentStatus("SUCCESS")
								.orderId(request.getOrderId())
								.referenceNum(request.getReferenceNum())
								.amount(request.getAmount())
								.build();
		td = transactionDetailsRepository.save(td);
		
		log.info("Payment successefuly completed with transaction : {}", td.getId());
		
		
		return td.getId();
	}

}
