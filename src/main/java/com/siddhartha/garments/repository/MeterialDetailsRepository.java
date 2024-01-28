package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.Meterialdetails;
import com.siddhartha.garments.entity.MeterialdetailsId;

@Repository
public interface MeterialDetailsRepository extends JpaRepository<Meterialdetails, MeterialdetailsId> {

	List<Meterialdetails> findByMetIdOrderIdAndMetIdChallanId(String orderId, String challanNo);

}
