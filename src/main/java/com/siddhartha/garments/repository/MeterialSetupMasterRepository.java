package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.Meterialdetails;
import com.siddhartha.garments.entity.MeterialdetailsId;

@Repository
public interface MeterialSetupMasterRepository extends JpaRepository<Meterialdetails, MeterialdetailsId>{

}
