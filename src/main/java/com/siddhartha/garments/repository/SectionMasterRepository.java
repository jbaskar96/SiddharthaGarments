package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.SectionMaster;
import com.siddhartha.garments.entity.SectionMasterId;

@Repository
public interface SectionMasterRepository extends JpaRepository<SectionMaster, SectionMasterId>{

	List<SectionMaster> findByStatusIgnoreCase(String status);

	Long countByIdCompanyId(Integer companyId);


	List<SectionMaster> findByIdCompanyIdAndStatusIgnoreCase(Integer companyId, String string);

}
