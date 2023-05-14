package com.khan.mhsn.ecomm.orderService.service;

import com.khan.mhsn.ecomm.orderService.entity.Order;
import com.khan.mhsn.ecomm.orderService.model.GetOrderResponse;
import com.khan.mhsn.ecomm.orderService.model.OrderRequest;

public interface OrderService {

	public long createOrder(OrderRequest orderRequest);

	public GetOrderResponse getOrderById(long orderId);
}
