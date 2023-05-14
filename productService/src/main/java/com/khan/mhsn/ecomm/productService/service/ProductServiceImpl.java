package com.khan.mhsn.ecomm.productService.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khan.mhsn.ecomm.productService.entity.Product;
import com.khan.mhsn.ecomm.productService.exception.ProductServiceCustomeException;
import com.khan.mhsn.ecomm.productService.model.ProductRequset;
import com.khan.mhsn.ecomm.productService.model.ProductResponse;
import com.khan.mhsn.ecomm.productService.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
	
	@Autowired 
	ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequset req) {
		
		log.info("Adding product");
		Product product = Product.builder()
				.productName(req.getName())
				.quantity(req.getQuantity())
				.price(req.getPrice())
				.quantity(req.getQuantity())
				.build();
		
		productRepository.save(product);
		log.info("Added product");
		
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long id) {
		
		Product product = productRepository.findById(id)
						.orElseThrow(() -> new ProductServiceCustomeException("Product with given id is not found.", "PRODUCT_NOT_FOUND"));
		
		ProductResponse productResponse = new ProductResponse();

		// Using BeanUtils for mapping objects.
		BeanUtils.copyProperties(product, productResponse);
		
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		
		log.info("Reducing the quantity");
		
		Product product = productRepository.findById(productId)
		.orElseThrow(() -> new ProductServiceCustomeException("Product not found ", "PRODUCT_NOT_FOUND"));
		 
		if (product.getQuantity() < quantity)
		{
			throw new ProductServiceCustomeException("Product does not have sufficient quantity", "INSUFFICIENT_QUANTITY");
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		
		productRepository.save(product);
		
		log.info("Product quantity is updated successfully !");
		
		// TODO Auto-generated method stub
		
	}
	
	

}
