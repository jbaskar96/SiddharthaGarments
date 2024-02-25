package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.OrderChallanDetails;
import com.siddhartha.garments.entity.OrderChallanDetailsId;

@Repository
public interface OrderChallanDetailsRepository extends JpaRepository<OrderChallanDetails, OrderChallanDetailsId> {

	List<OrderChallanDetails> findByIdOrderId(String orderId);

	Integer countByIdOrderId(String orderId);

}
