package com.khan.mhsn.ecomm.orderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.khan.mhsn.ecomm.orderService.entity.Order;
import com.khan.mhsn.ecomm.orderService.exception.CustomeException;
import com.khan.mhsn.ecomm.orderService.external.client.PaymentService;
import com.khan.mhsn.ecomm.orderService.external.client.ProductService;
import com.khan.mhsn.ecomm.orderService.model.GetOrderResponse;
import com.khan.mhsn.ecomm.orderService.model.OrderRequest;
import com.khan.mhsn.ecomm.orderService.model.PaymentMode;
import com.khan.mhsn.ecomm.orderService.model.PaymentRequest;
import com.khan.mhsn.ecomm.orderService.model.ProductResponse;
import com.khan.mhsn.ecomm.orderService.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductService productService; 
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public long createOrder(OrderRequest orderRequest) {
		
		//1.Order Service : Save the order data with created order
		//2.Product service : Block the product (Reduce the quantity)
		//3.Payment Service : Make the payment -> If payment is Success Then Mark Order staus as COMPLETED ELSE mark it CANCELLED
		
		//check and reducing the product quantity
		
		log.info("1. Calling Reducing the quantity.  ");
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("1.1 Completed Reducing the quantity.  ");
		// Saving the order 
		log.info("2. Saving the order");
		
		Order order = new Order().builder()
				.productId(orderRequest.getProductId())
				.quantity(orderRequest.getQuantity())
				.orderDate(Instant.now())
				.orderStatus("P")
				.amount(orderRequest.getAmount())
				.build();

		order = orderRepository.save(order);
		
		log.info("3. Calling payment service for order : {}", order.getOrderId());
				
		//Making the payment 
		PaymentRequest paymentrequest = new PaymentRequest().builder()
				.orderId(order.getOrderId())
				.amount(order.getAmount())
				.referenceNum(order.getOrderDate().toString())
				.paymentMode(PaymentMode.CASH)
				.build();
		
		String orderStatus = null;
		try {
			paymentService.doPayment(paymentrequest);
			log.info("4. Payment placed successfuly!");
			orderStatus = "COMPLETED";
						
		}catch(Exception e) {
			log.info("Payment failed");
			orderStatus = "FAILED";
		
		}
		
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		
		return order.getOrderId();
	}

	// Get order details with product details 
	@Override
	public GetOrderResponse getOrderById(long orderId) {

		//Using Feign client
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new CustomeException("Order not found", "ORDER_NOT_FOUND", 404));
		
		//Using RestTemplate
		ProductResponse pRes =  
				restTemplate.getForObject("http://PRODUCT-SERVICE/products/"+order.getProductId(), ProductResponse.class);
		
		GetOrderResponse.ProductDetails product = 
				new GetOrderResponse.ProductDetails().builder()
				.productName(pRes.getProductName())
				.productId(pRes.getProductId())
				.price(pRes.getPrice())
				.quantity(pRes.getQuantity())
				.build();
		
		/* TODO */
		// Fetch the order details as well and add it in response. 
		// check point 63 
		
		GetOrderResponse res = new GetOrderResponse().builder()
								.orderId(orderId)
								.orderDate(order.getOrderDate())
								.orderStatus(order.getOrderStatus())
								.amount(order.getAmount())
								.productDetails(product)
								.build();
		
		return res;
	}

}
