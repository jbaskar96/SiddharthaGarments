package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.LoginMaster;

@Repository
public interface LoginMasterRepository extends JpaRepository<LoginMaster, String>{

	LoginMaster findByLoginIdAndPassword(String userName, String epass);

	LoginMaster findByLoginId(String username);

	LoginMaster findByLoginIdIgnoreCaseAndPassword(String userName, String paswd);

	LoginMaster findByLoginIdIgnoreCase(String username);

}
