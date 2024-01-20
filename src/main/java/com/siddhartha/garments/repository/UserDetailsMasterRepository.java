package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.UserDetailsMaster;

@Repository
public interface UserDetailsMasterRepository extends JpaRepository<UserDetailsMaster, String> {

}
