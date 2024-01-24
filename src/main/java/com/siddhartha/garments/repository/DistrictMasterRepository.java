package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.DistrictMaster;
import com.siddhartha.garments.entity.DistrictMasterID;

@Repository
public interface DistrictMasterRepository extends JpaRepository<DistrictMaster, DistrictMasterID> {


	List<DistrictMaster> findByDistIdStateCode(Integer statecode);

	void deleteByStateCode(Integer stateCode);

	List<DistrictMaster> findByStateCodeStatusIgnorecase(String status);

}
