package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductSizeMaster;

@Repository
public interface ProductSizeMasterRepository extends JpaRepository<ProductSizeMaster, Integer>{

	List<ProductSizeMaster> findByStatusIgnorecase(String status);

}
