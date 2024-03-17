package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.entity.DistrictMaster;
import com.siddhartha.garments.entity.ExpensiveDetails;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.entity.OrderChallanDetails;
import com.siddhartha.garments.entity.OrderDetails;
import com.siddhartha.garments.entity.PurchaseMaster;
import com.siddhartha.garments.entity.UserDetailsMaster;
import com.siddhartha.garments.entity.WorkerEntryDetails;
import com.siddhartha.garments.request.PurchaseReportReq;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.ExpensiveReportRequest;
import com.siddhartha.garments.response.OrderReportReq;
import com.siddhartha.garments.response.WorkerReportReq;

@Component
@Transactional
public class CriteriaQueryServiceImpl {
	
	Logger log = LogManager.getLogger(CriteriaQueryServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	
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
	
	public List<WorkerEntryDetails> getWorkerReportDetails(WorkerReportReq req){
		try {
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<WorkerEntryDetails> query =cb.createQuery(WorkerEntryDetails.class);
			Root<WorkerEntryDetails> worker =query.from(WorkerEntryDetails.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if("OPT".equalsIgnoreCase(req.getReportType())) {
				predicates.add(cb.equal(worker.get("operatorId"), req.getReportValue()));
			}else if("O".equalsIgnoreCase(req.getReportType())) {
				predicates.add(cb.equal(worker.get("orderId"), req.getReportValue()));
			}else if("S".equalsIgnoreCase(req.getReportType())) {
				predicates.add(cb.equal(worker.get("sectionId"), req.getReportValue()));
			}else if("Date".equalsIgnoreCase(req.getReportType())) {
				Date start_date =sdf.parse(req.getStartDate());
				Date end_date =sdf.parse(req.getEndDate());
				predicates.add(cb.between(worker.get("entryDate"),start_date,end_date));
			}
				
			Predicate wh[] = new Predicate[predicates.size()];
			predicates.toArray(wh);
			
			query.select(worker).where(wh).orderBy(cb.desc(worker.get("entryDate")));
			
			TypedQuery<WorkerEntryDetails> typedQuery =em.createQuery(query);
			
			List<WorkerEntryDetails> entryDetails =typedQuery.getResultList();
			
			return entryDetails;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	public List<PurchaseMaster> getPurchaseReport(PurchaseReportReq req) {
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<PurchaseMaster> query =cb.createQuery(PurchaseMaster.class);
			Root<PurchaseMaster> root = query.from(PurchaseMaster.class);
			List<Predicate> predicates = new ArrayList<>();
			
			Date startDate =sdf.parse(req.getStartDate());
			Date endDate =sdf.parse(req.getEndDate());
			if("1".equals(req.getReportType())) {
				predicates.add(cb.between(root.get("billDate"),startDate,endDate));
				predicates.add(cb.equal(root.get("categoryId"), 1));
			}else if ("2".equals(req.getReportType())) {
				predicates.add(cb.between(root.get("billDate"),startDate,endDate));
				predicates.add(cb.equal(root.get("categoryId"), 2));
			}else if("ALL".equalsIgnoreCase(req.getReportType())) {
				predicates.add(cb.between(root.get("billDate"),startDate,endDate));
			}
			
			TypedQuery<PurchaseMaster> list =em.createQuery(query);
			
			return list.getResultList();
			

		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<Tuple> getOrderReport(OrderReportReq req) {
		List<Tuple> list = null;
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createTupleQuery();
			Root<OrderDetails> od =query.from(OrderDetails.class);
			
			List<Predicate> predicates = new ArrayList<>();
			
			//No of challans subquery
			Subquery<Long> totalChallan =query.subquery(Long.class);
			Root<OrderChallanDetails> ocd =totalChallan.from(OrderChallanDetails.class);
			totalChallan.select(cb.count(ocd)).where(cb.equal(od.get("orderId"), ocd.get("id").get("orderId")));
			
			//No of pieces subquery
			Subquery<Long> totalPieces =query.subquery(Long.class);
			Root<OrderChallanDetails> ocd1 =totalPieces.from(OrderChallanDetails.class);
			totalPieces.select(cb.sum(ocd1.get("totalPieces"))).where(cb.equal(od.get("orderId"), ocd1.get("id").get("orderId")));
			
			Expression<String> statusDesc =cb.selectCase()
					.when(cb.equal(od.get("status"), "Y"), "INWARD")
					.when(cb.equal(od.get("status"), "P"), "PRODUCTION")
					.when(cb.equal(od.get("status"), "D"), "DELIVERED")
					.otherwise("BILLING").as(String.class);
			
			query.multiselect(od.get("orderId").alias("orderId"),totalChallan.alias("totalChallan"),
					totalPieces.alias("totalPieces"),statusDesc.alias("statusDesc"),
					od.get("companyId").alias("companyId"),od.get("productId").alias("productId"),
					od.get("lotNumber").alias("lotNumber"),od.get("entryDate").alias("entryDate")
					);
			
			
			if(!"ALL".equalsIgnoreCase(req.getReportType())) {
				predicates.add(cb.equal(od.get("status"), req.getReportType()));
			}
			
			
			Date start_date =sdf.parse(req.getStartDate());
			Date end_date =sdf.parse(req.getEndDate());
			predicates.add(cb.between(od.get("entryDate"), start_date, end_date));
			
			Predicate [] predicatesArray =new Predicate[predicates.size()];
			predicates.toArray(predicatesArray);
			
			query.where(predicatesArray).orderBy(cb.desc(od.get("entryDate")));
			
			TypedQuery<Tuple> typedQuery =em.createQuery(query);
			 
			list =typedQuery.getResultList();
			
			return list ;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<ExpensiveDetails> getExpensiveReport(ExpensiveReportRequest req) {
		List<ExpensiveDetails> list = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ExpensiveDetails> query =cb.createQuery(ExpensiveDetails.class);
			Root<ExpensiveDetails> ed =query.from(ExpensiveDetails.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			Date start_date =sdf.parse(req.getStartDate());
			Date end_date =sdf.parse(req.getEndDate());
			if("ALL".equalsIgnoreCase(req.getCategory()) && "ALL".equalsIgnoreCase(req.getAccountType())) {
				predicates.add(cb.between(ed.get("entryDate"), start_date, end_date));
			}else if(!"ALL".equalsIgnoreCase(req.getCategory()) && "ALL".equalsIgnoreCase(req.getAccountType())) {
				predicates.add(cb.equal(ed.get("categoryType"), req.getCategory()));
				predicates.add(cb.between(ed.get("entryDate"), start_date, end_date));
			}else if("ALL".equalsIgnoreCase(req.getCategory()) && !"ALL".equalsIgnoreCase(req.getAccountType())) {
				predicates.add(cb.equal(ed.get("accountType"), req.getAccountType()));
				predicates.add(cb.between(ed.get("entryDate"), start_date, end_date));
			}else {
				predicates.add(cb.equal(ed.get("categoryType"), req.getCategory()));
				predicates.add(cb.equal(ed.get("accountType"), req.getAccountType()));
				predicates.add(cb.between(ed.get("entryDate"), start_date, end_date));
			}
			
			Predicate [] predicates_array = new Predicate[predicates.size()];
			predicates.toArray(predicates_array);
			
			
			query.select(ed).where(predicates_array).orderBy(cb.desc(ed.get("entryDate")));
			
			TypedQuery<ExpensiveDetails> typedQuery = em.createQuery(query);
			
			return list=typedQuery.getResultList();
			 
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
