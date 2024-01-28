package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ChallanDetails;
import com.siddhartha.garments.entity.ChallanDetailsId;

@Repository
public interface ChallanRepository extends JpaRepository<ChallanDetails, ChallanDetailsId>{

	List<ChallanDetails> findByChaIdOrderId(String orderId);

}
