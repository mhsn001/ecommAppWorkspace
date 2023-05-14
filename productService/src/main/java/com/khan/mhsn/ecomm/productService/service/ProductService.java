package com.khan.mhsn.ecomm.productService.service;

import com.khan.mhsn.ecomm.productService.model.ProductRequset;
import com.khan.mhsn.ecomm.productService.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequset req);

	ProductResponse getProductById(long id);

	void reduceQuantity(long productId, long quantity);

}
