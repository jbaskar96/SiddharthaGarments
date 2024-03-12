package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.OrderBillingDetails;

@Repository
public interface OrderBillingDetailRepository extends JpaRepository<OrderBillingDetails, String>{

}
