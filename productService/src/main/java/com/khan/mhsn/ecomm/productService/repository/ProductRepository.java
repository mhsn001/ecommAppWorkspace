package com.khan.mhsn.ecomm.productService.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khan.mhsn.ecomm.productService.entity.Product;

// Defining a repository for product. It will be an interface. 
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
