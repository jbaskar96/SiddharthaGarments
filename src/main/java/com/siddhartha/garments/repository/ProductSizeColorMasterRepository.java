package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductSizeColorMaster;
import com.siddhartha.garments.entity.ProductSizeColorMasterId;

@Repository
public interface ProductSizeColorMasterRepository extends JpaRepository<ProductSizeColorMaster, ProductSizeColorMasterId>{

	List<ProductSizeColorMaster> findByIdCompanyIdAndIdProductIdAndIdSizeId(Integer valueOf, Integer valueOf2,
			Integer valueOf3);

}
