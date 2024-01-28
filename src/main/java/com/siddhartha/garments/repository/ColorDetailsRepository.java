package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ColorDeatils;
import com.siddhartha.garments.entity.ColorDeatilsId;

@Repository
public interface ColorDetailsRepository extends JpaRepository<ColorDeatils, ColorDeatilsId> {

	List<ColorDeatils> findByColIdOrderIdAndColIdChallanId(String orderId, String challanNo);

}
