package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductMaster;

@Repository
public interface ProductMasterRepository  extends JpaRepository<ProductMaster, Integer>{

	List<ProductMaster> findByStatusIgnoreCase(String status);

}
