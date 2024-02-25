package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.OrderSizeColorDetails;
import com.siddhartha.garments.entity.OrderSizeColorDetailsId;

@Repository
public interface OrderSizeColorDetailsRepository  extends JpaRepository<OrderSizeColorDetails, OrderSizeColorDetailsId>{

	List<OrderSizeColorDetails> findByIdOrderIdAndIdChallanId(String orderId, String challanId);

}
