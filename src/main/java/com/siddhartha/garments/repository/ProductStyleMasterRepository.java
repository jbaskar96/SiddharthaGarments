package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductStyleMaster;
import com.siddhartha.garments.entity.ProductStyleMasterId;

@Repository
public interface ProductStyleMasterRepository extends JpaRepository<ProductStyleMaster, ProductStyleMasterId>{



	List<ProductStyleMaster> findByIdCompanyIdAndIdProductId(Integer companyId, Integer productId);

}
