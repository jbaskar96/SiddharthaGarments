package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.StateMaster;

@Repository
public interface StateMasterRepository extends JpaRepository<StateMaster, Integer>{

}
