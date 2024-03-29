package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductColorMetalMaster;
import com.siddhartha.garments.entity.ProductColorMetalMasterId;

@Repository
public interface ProductColorMetalMasterRepository extends JpaRepository<ProductColorMetalMaster, ProductColorMetalMasterId> {




	List<ProductColorMetalMaster> findByIdCompanyIdAndIdProductIdAndIdColourCode(Integer valueOf, Integer valueOf2,
			Integer valueOf3);

	List<ProductColorMetalMaster> findByIdCompanyIdAndIdProductIdAndIdColourCodeOrderByDisplayOrder(
			Integer companyId, Integer productId,Integer colorCode);

	List<ProductColorMetalMaster> findByIdCompanyIdAndIdProductIdAndIdColourCodeAndStatusOrderByDisplayOrder(
			Integer companyId, Integer productId, Integer colorCode, String string);

}
