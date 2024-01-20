package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductSizeMaster;

@Repository
public interface ProductSizeMasterRepository extends JpaRepository<ProductSizeMaster, Integer>{

}
