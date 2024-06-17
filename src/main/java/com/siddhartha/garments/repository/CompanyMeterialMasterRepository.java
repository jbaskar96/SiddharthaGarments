package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.CompanyMeterialMaster;
import com.siddhartha.garments.entity.CompanyMeterialMasterID;

@Repository
public interface CompanyMeterialMasterRepository extends JpaRepository<CompanyMeterialMaster, CompanyMeterialMasterID>{

	List<CompanyMeterialMaster> findByCompanyId(Integer companyId);

	List<CompanyMeterialMaster> findByCompanyIdAndProductIdAndSizeIdAndColorId(Integer companyId, Integer productId, Integer sizeId,
			Integer colorId);

	List<CompanyMeterialMaster> findByCompanyIdAndProductIdAndSizeIdAndColorIdAndStatusIgnoreCaseOrderByMeasurementDisplayOrder(Integer companyId, Integer productId, Integer sizeId,
			Integer colorId, String status);

	

}
