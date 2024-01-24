package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.entity.DistrictMaster;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.entity.UserDetailsMaster;
import com.siddhartha.garments.entity.UserTypeMaster;

@Component
@Transactional
public class CriteriaQueryServiceImpl {
	
	Logger log = LogManager.getLogger(CriteriaQueryServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	public Integer getMaxOfCodeByStateCode(String stateCode) {
		Integer count =0;
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery <Integer>  query =cb.createQuery(Integer.class);
			Root<DistrictMaster> root = query.from(DistrictMaster.class);
			query.select(cb.count(root).as(Integer.class))
				.where(cb.equal(root.get("distId").get("stateCode"), stateCode)).distinct(true);
			TypedQuery<Integer> queryrst =em.createQuery(query);
			count=queryrst.getSingleResult()+1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}


	public List<Tuple> getUserList(String userId) {
		List<Tuple> list = new ArrayList<>();
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<UserDetailsMaster> user = query.from(UserDetailsMaster.class);
			Root<LoginMaster> login =query.from(LoginMaster.class);
			
			query.multiselect(user.get("userId").alias("userId"),user.get("firstName").alias("firstName"),user.get("lastName").alias("lastName"),
					user.get("mobileNo").alias("mobileNo"),user.get("aadharNo").alias("aadharNo"),user.get("email").alias("email"),user.get("dateOfBirth").alias("dateOfBirth"),
					user.get("createdBy").alias("createdBy"),user.get("status").alias("status"),user.get("userType").alias("userType"),user.get("stateCode").alias("stateCode"),
					user.get("districtCode").alias("districtCode"),user.get("city").alias("city"),user.get("address").alias("address"),login.get("loginId").alias("loginId"),
					login.get("password").alias("password")
					).where(cb.equal(user.get("userId"), userId),cb.equal(user.get("loginId"), login.get("loginId")));
	
			TypedQuery<Tuple> typedQuery = em.createQuery(query);
			list=typedQuery.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
