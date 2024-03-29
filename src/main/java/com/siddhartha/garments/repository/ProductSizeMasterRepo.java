package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductMaster;
import com.siddhartha.garments.entity.ProductSizeMaster;
import com.siddhartha.garments.entity.ProductSizeMasterId;

@Repository
public interface ProductSizeMasterRepo extends JpaRepository<ProductSizeMaster, ProductSizeMasterId> {

	List<ProductSizeMaster> findByIdCompanyIdAndIdProductId(Integer companyId, Integer productId);

	List<ProductSizeMaster> findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(Integer companyId, Integer productId,
			String string);

}
