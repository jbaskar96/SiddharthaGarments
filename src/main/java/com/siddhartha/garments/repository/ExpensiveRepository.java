package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ExpensiveDetails;

@Repository
public interface ExpensiveRepository  extends JpaRepository<ExpensiveDetails, Long>{

}
