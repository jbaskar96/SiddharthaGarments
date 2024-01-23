package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductColorMaster;

@Repository
public interface ProductColorMasterRepository extends JpaRepository<ProductColorMaster, Integer>{

}