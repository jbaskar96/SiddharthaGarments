package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductSectionMaster;

@Repository
public interface ProductSectionMasterRepository extends JpaRepository<ProductSectionMaster, Integer>{

	List<ProductSectionMaster> findByStatusIgnoreCase(String status);

	Long countByIdCompanyIdAndIdProductId(Integer valueOf, Integer valueOf2);

	List<ProductSectionMaster> findByIdCompanyIdAndIdProductId(Integer companyId, Integer productId);

}
