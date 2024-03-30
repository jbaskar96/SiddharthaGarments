package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhartha.garments.entity.SequenceGenerator;

public interface SequenceGeneratorRepository extends JpaRepository<SequenceGenerator, Long> {

}
