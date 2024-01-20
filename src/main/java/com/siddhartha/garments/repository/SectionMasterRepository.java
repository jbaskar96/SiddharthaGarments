package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.SectionMaster;

@Repository
public interface SectionMasterRepository extends JpaRepository<SectionMaster, Integer>{

}
