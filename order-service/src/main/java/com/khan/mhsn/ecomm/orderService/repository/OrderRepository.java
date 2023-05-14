package com.khan.mhsn.ecomm.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khan.mhsn.ecomm.orderService.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
