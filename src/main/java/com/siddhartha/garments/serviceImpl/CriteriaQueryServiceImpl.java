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

import com.siddhartha.garments.entity.ChallanDetails;
import com.siddhartha.garments.entity.ColorDeatils;
import com.siddhartha.garments.entity.DistrictMaster;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.entity.LotDeatils;
import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.entity.PurchaseMaster;
import com.siddhartha.garments.entity.UserDetailsMaster;
import com.siddhartha.garments.entity.WorkerEntryDetails;
import com.siddhartha.garments.request.PurchaseReportReq;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;

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
	
	public List<Tuple> getAllOrderDetails(String status){
		List<Tuple> list = null;
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<LotDeatils> lotRoot =query.from(LotDeatils.class);
			
			Subquery<Integer> subQuery=query.subquery(Integer.class);
			Root<ChallanDetails> root =subQuery.from(ChallanDetails.class);
			subQuery.select(cb.count(root).as(Integer.class)).where(cb.equal(lotRoot.get("orderId"), lotRoot.get("orderId"))
					,cb.equal(cb.upper(root.get("status")), "Y"));

			Expression<String> statusDesc =cb.selectCase()
					.when(cb.equal(lotRoot.get("status"), "Y"), "INWARD")
					.when(cb.equal(lotRoot.get("status"), "P"), "PRODUCTION")
					.otherwise("DELIVERED")
					.as(String.class);
			
			query.multiselect(lotRoot.get("orderId").alias("orderId"),lotRoot.get("lotNo").alias("lotNo"),lotRoot.get("companyName").alias("companyName"),
					lotRoot.get("inwardDate").alias("inwardDate"),lotRoot.get("gstNo").alias("gstNo"),lotRoot.get("phoneNo").alias("phoneNo"),
					subQuery.alias("totalSize"),statusDesc.alias("status"),lotRoot.get("deliveryDate").alias("deliveryDate"),lotRoot.get("productionDate").alias("productionDate"))
			.where(cb.equal(cb.upper(lotRoot.get("status")), status))
			.orderBy(cb.desc(lotRoot.get("entryDate")));
			
			TypedQuery<Tuple> typedQuery =em.createQuery(query);
			list=typedQuery.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public List<Tuple> getColorDeatilsByChallanId(String orderId,String challanId){
		List<Tuple> list = null;
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<ColorDeatils> color =query.from(ColorDeatils.class);
			Root<ProductColorMaster> polCol =query.from(ProductColorMaster.class);
			query.multiselect(color.get("colId").get("colorId").alias("colorId"),polCol.get("colorName").alias("colorName"),
					color.get("piecesCount").alias("piecesCount"))
			.where(cb.equal(color.get("colId").get("orderId"), orderId),
					cb.equal(color.get("colId").get("challanId"), challanId),
					cb.equal(color.get("colorCode"), polCol.get("colorId")),cb.equal(polCol.get("status"),"Y"))
			.orderBy(cb.asc(polCol.get("colorName")));
			
			TypedQuery<Tuple> typedQuery =em.createQuery(query);
			list =typedQuery.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<WorkerEntryDetails> getWorkerReportDetails(WorkerEntryDetailsReq req){
		try {
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<WorkerEntryDetails> query =cb.createQuery(WorkerEntryDetails.class);
			Root<WorkerEntryDetails> worker =query.from(WorkerEntryDetails.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			if(StringUtils.isBlank(req.getOperatorId()) && StringUtils.isBlank(req.getSectionId()) && StringUtils.isBlank(req.getOrderId())
					&& StringUtils.isBlank(req.getChallanId()) && StringUtils.isBlank(req.getColorId())) {
				
				predicates.add(cb.equal(worker.get("operatorId"), req.getOperatorId()));
				predicates.add(cb.equal(worker.get("sectionId"), req.getSectionId()));
				predicates.add(cb.equal(worker.get("orderId"), req.getOperatorId()));
				predicates.add(cb.equal(worker.get("challanId"), req.getChallanId()));
				predicates.add(cb.equal(worker.get("colorId"), req.getColorId()));
				
			}else if(StringUtils.isBlank(req.getOperatorId()) && StringUtils.isBlank(req.getSectionId()) && StringUtils.isBlank(req.getOrderId())
					&& StringUtils.isBlank(req.getChallanId())) {
				
				predicates.add(cb.equal(worker.get("operatorId"), req.getOperatorId()));
				predicates.add(cb.equal(worker.get("sectionId"), req.getSectionId()));
				predicates.add(cb.equal(worker.get("orderId"), req.getOperatorId()));
				predicates.add(cb.equal(worker.get("challanId"), req.getChallanId()));
				
			}else if(StringUtils.isBlank(req.getOperatorId()) && StringUtils.isBlank(req.getSectionId()) && StringUtils.isBlank(req.getOrderId())) {
				
				predicates.add(cb.equal(worker.get("operatorId"), req.getOperatorId()));
				predicates.add(cb.equal(worker.get("sectionId"), req.getSectionId()));
				predicates.add(cb.equal(worker.get("orderId"), req.getOperatorId()));
				
			}else if(StringUtils.isBlank(req.getOperatorId()) && StringUtils.isBlank(req.getSectionId())) {
				
				predicates.add(cb.equal(worker.get("operatorId"), req.getOperatorId()));
				predicates.add(cb.equal(worker.get("sectionId"), req.getSectionId()));
				
			}else if(StringUtils.isBlank(req.getOperatorId())) {
				
				predicates.add(cb.equal(worker.get("operatorId"), req.getOperatorId()));

			}else {
				
				predicates.add(cb.equal(worker.get("status"), "Y"));
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
			}else {
				predicates.add(cb.between(root.get("billDate"),startDate,endDate));
			}
			
			TypedQuery<PurchaseMaster> list =em.createQuery(query);
			
			return list.getResultList();
			

		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
