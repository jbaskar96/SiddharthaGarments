package com.siddhartha.garments.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.entity.DistrictMaster;

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
				.where(cb.equal(root.get("stateCode"), stateCode)).distinct(true);
			TypedQuery<Integer> queryrst =em.createQuery(query);
			count=queryrst.getSingleResult()+1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
