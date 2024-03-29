package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.PurchaseMaster;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseMaster, Long> {

}
