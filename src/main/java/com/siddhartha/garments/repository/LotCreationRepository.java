package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.LotDeatils;

@Repository
public interface LotCreationRepository  extends JpaRepository<LotDeatils, String>{

	List<LotDeatils> findByStatusIgnoreCase(String status);

}
