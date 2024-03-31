package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.dto.GetOrderSizeColorReq;
import com.siddhartha.garments.dto.OrderChallanColorReq;
import com.siddhartha.garments.dto.OrderChallanInfoReq;
import com.siddhartha.garments.dto.OrderDetailsRequest;
import com.siddhartha.garments.entity.OrderBillingDetails;
import com.siddhartha.garments.entity.OrderChallanDetails;
import com.siddhartha.garments.entity.OrderChallanDetailsId;
import com.siddhartha.garments.entity.OrderColorDetails;
import com.siddhartha.garments.entity.OrderColorDetailsId;
import com.siddhartha.garments.entity.OrderDetails;
import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.repository.OrderBillingDetailRepository;
import com.siddhartha.garments.repository.OrderChallanDetailsRepository;
import com.siddhartha.garments.repository.OrderColorDetailsRepository;
import com.siddhartha.garments.repository.OrderDetailsRepository;
import com.siddhartha.garments.repository.ProductColorMasterRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.OrderBillingRequest;
import com.siddhartha.garments.response.UpdateOrderStatusReq;
import com.siddhartha.garments.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepos;
	
	@Autowired
	private OrderChallanDetailsRepository orderChallanDetailsRepos;
	
	@Autowired
	private OrderColorDetailsRepository orderColorDetailsRepo;
	
	@Autowired
	private InputValidationServiceImpl validation;
	
	@Autowired
	private OrderBillingDetailRepository orderBillingRepo;
	
	@Autowired
	private SequenceGeneratorServiceImpl seq;
	
	@Autowired
	private ProductColorMasterRepository productColorRepo;
	
	@Override
	public CommonResponse saveOrder(OrderDetailsRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.validateOrder(req);
			if(error.isEmpty()) {
				
				OrderDetails ord =new OrderDetails();
				if(StringUtils.isNotBlank(req.getOrderId())) {
					 ord = orderDetailsRepos.findById(req.getOrderId()).get();
				}
				
				OrderDetails orderDetails = OrderDetails.builder()
						.orderId(StringUtils.isBlank(req.getOrderId())?generateNumber("O",""):req.getOrderId())
						.companyId(Integer.valueOf(req.getCompanyId()))
						.createdBy(req.getCreatedBy())
						.entryDate(new Date())
						.inwardDate(sdf.parse(req.getInwardDate()))
						.lotNumber(req.getLotNumber())
						.productId(Integer.valueOf(req.getProductId()))
						.remarks(StringUtils.isBlank(req.getRemarks())?"":req.getRemarks())
						//.styleId(Integer.valueOf(req.getStyleId()))
						.status(req.getStatus())
						.colorFoldingYn(StringUtils.isBlank(req.getOrderId())?"N":StringUtils.isBlank(ord.getColorFoldingYn())?"N":ord.getColorFoldingYn())
						.sizefoldingYn(StringUtils.isBlank(req.getOrderId())?"N":StringUtils.isBlank(ord.getSizefoldingYn())?"N":ord.getSizefoldingYn())
						.build();
				
				OrderDetails details =orderDetailsRepos.save(orderDetails);
				
				if(details!=null) {
					
					for(OrderChallanInfoReq c :req.getOrderChallanInfo()) {
						
						OrderChallanDetailsId challanDetailsId =OrderChallanDetailsId.builder()
								.orderId(details.getOrderId())
								.challanId(StringUtils.isBlank(c.getChallanId())?generateNumber("C",details.getOrderId()):c.getChallanId())
								.build();
						
						OrderChallanDetails challanDetails =OrderChallanDetails.builder()
								.challanDate(sdf.parse(c.getChallanDate()))
								.challanNumber(c.getChallanNumber())
								.entryDate(new Date())
								.id(challanDetailsId)
								.size(Integer.valueOf(c.getSize()))
								.sizeId(Integer.valueOf(c.getSizeId()))
								.totalPieces(Integer.valueOf(c.getTotalPieces()))
								.build();
						
						orderChallanDetailsRepos.save(challanDetails);
					}
					
					

				}
				
				Map<String,String> respo = new HashMap<String, String>();
				respo.put("OrderId", details.getOrderId());
				
				response.setMessage("Success");
				response.setResponse(respo);
				response.setError(null);
			}else {
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private synchronized String generateNumber(String type,String orderId) {
		String number ="";
		
		String no =seq.getSequenceNo();
		
		if("O".equals(type)) {
			Date date = new Date();
			String strDate =sdf.format(date);
			number="ORDER/"+strDate+"/1"+no+"";
		}else if("C".equals(type)) {
			number="CHA/1"+no+"";
		}else if("COL".equals(type)) {
			number="COL/1"+no+"";
		}
		return number;
	}

	@Override
	public CommonResponse editOrder(EditOrderDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			Optional<OrderDetails> order =orderDetailsRepos.findById(req.getOrderId());
			
			if(order.isPresent()) {
				OrderDetails o =order.get();
				Map<String,Object> orderRes =new HashMap<String,Object>();
				orderRes.put("OrderId", o.getOrderId());
				orderRes.put("CompanyId", o.getCompanyId().toString());
				orderRes.put("ProductId", o.getProductId().toString());
				orderRes.put("LotNumber", o.getLotNumber());
				orderRes.put("InwardDate", sdf.format(o.getInwardDate()));
				orderRes.put("Status", o.getStatus());
				orderRes.put("CreatedBy", o.getCreatedBy());
				orderRes.put("Remarks", StringUtils.isBlank(o.getRemarks())?"":o.getRemarks());
				orderRes.put("SizeFoldingYn", StringUtils.isBlank(o.getSizefoldingYn())?"N":o.getSizefoldingYn());
				orderRes.put("ColorFoldingYn", StringUtils.isBlank(o.getColorFoldingYn())?"N":o.getColorFoldingYn());
				orderRes.put("ProductFoldingYn", StringUtils.isBlank(o.getProductFoldingYn())?"N":o.getProductFoldingYn());

				List<OrderChallanDetails> challan =orderChallanDetailsRepos.findByIdOrderId(o.getOrderId());
				List<Map<String,String>> challanList = new ArrayList<>();
				challan.forEach(p ->{
					Map<String,String> challRes =new HashMap<String,String>();
					challRes.put("ChallanId", p.getId().getChallanId().toString());
					challRes.put("ChallanNumber", p.getChallanNumber());
					challRes.put("ChallanDate", sdf.format(p.getChallanDate()));
					challRes.put("SizeId", p.getSizeId().toString());
					challRes.put("Size", p.getSize().toString());
					challRes.put("TotalPieces", p.getTotalPieces().toString());
					challanList.add(challRes);
				});
				orderRes.put("OrderChallanInfo", challanList);
				
				response.setMessage("Success");
				response.setResponse(orderRes);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Record Found");
				response.setError(null);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse saveColor(List<OrderChallanColorReq> req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.orderColor(req);
			if(error.isEmpty()) {
				
				for(OrderChallanColorReq r :req) {
					
					OrderColorDetailsId colorDetailsId = OrderColorDetailsId.builder()
							.orderId(r.getOrderId())
							.challanId(r.getChallanId())							
							.colorId(StringUtils.isBlank(r.getColorId())?generateNumber("COL", null)
									:r.getColorId())
							.build();

					OrderColorDetails colorDetails = OrderColorDetails.builder()
							.colorCode(Integer.valueOf(r.getColorCode()))
							.colorName(r.getColorName())
							.entryDate(new Date())
							.id(colorDetailsId)
							.totalPieces(Integer.valueOf(r.getTotalPieces()))
							.build();
					
					orderColorDetailsRepo.save(colorDetails);
				}
				
				
				
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	
	@Override
	public CommonResponse getOrderColorDetails(GetOrderSizeColorReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<OrderColorDetails> colorDet =orderColorDetailsRepo.findByIdOrderIdAndIdChallanId(req.getOrderId(), req.getChallanId());
			List<Map<String,String>> mapList = new ArrayList<>();
			if(!colorDet.isEmpty()) {
				OrderDetails order =orderDetailsRepos.findById(req.getOrderId()).get();
				Integer companyId =order.getCompanyId();
				Integer productId =order.getProductId();
				List<ProductColorMaster>  metal_master =productColorRepo.findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(companyId, productId,"Y");
				
				List<String> filterCol = new ArrayList<>();
				
				String isUpdateColor =StringUtils.isBlank(req.getIsUpdateColor())?"N":req.getIsUpdateColor();
				
				if("Y".equals(isUpdateColor)) {
					
					List<String> colDet =colorDet.stream().map(p ->p.getColorCode().toString()).collect(Collectors.toList());
					
					filterCol =metal_master.stream()
							.map(p ->p.getId().getColourCode().toString())
							.filter(p -> checkContains(p,colDet))
							.collect(Collectors.toList());				
					
					List<Map<String,String>> filColList = new ArrayList<>();
					if(!filterCol.isEmpty()) {
						 filColList =filterCol.stream()
								.map(p ->{
									Map<String,String> map = new HashMap<String, String>();
									map.put("OrderId", colorDet.get(0).getId().getOrderId());
									map.put("ChallanId", colorDet.get(0).getId().getChallanId());
									map.put("ColorId", "");
									map.put("ColorCode", p);
									map.put("TotalPieces", "0");
									return map;
								}).collect(Collectors.toList());
						 
						 mapList.addAll(filColList);
					}
				}
				
				for (OrderColorDetails ocd :colorDet) {
					Map<String,String> map = new HashMap<String, String>();
					map.put("OrderId", ocd.getId().getOrderId());
					map.put("ChallanId", ocd.getId().getChallanId());
					map.put("ColorId", ocd.getId().getColorId());
					map.put("ColorCode", ocd.getColorCode().toString());
					map.put("TotalPieces", ocd.getTotalPieces().toString());
					mapList.add(map);
				}
				
				
				
			}else {
				OrderDetails order =orderDetailsRepos.findById(req.getOrderId()).get();
				Integer companyId =order.getCompanyId();
				Integer productId =order.getProductId();
				List<ProductColorMaster>  metal_master =productColorRepo.findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(companyId, productId,"Y");
				 mapList =metal_master.stream()
							.map(p ->{
								Map<String,String> map = new HashMap<String, String>();
								map.put("OrderId", "");
								map.put("ChallanId",  "");
								map.put("ColorId",  "");
								map.put("ColorCode", p.getId().getColourCode().toString());
								map.put("TotalPieces", "0");
								return map;
							}).collect(Collectors.toList());
					
			}
			
			if(!mapList.isEmpty()) {
				response.setMessage("Success");
				response.setResponse(mapList);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Record Found");
				response.setError(null);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private boolean checkContains(String p, List<String> colDet) {
		return !colDet.contains(p);
		
	}

	@Override
	public CommonResponse editOrderColorDetails(GetOrderSizeColorReq req) {
		CommonResponse response = new CommonResponse();
		try {
			OrderColorDetailsId colorDetailsId =OrderColorDetailsId.builder()
					.orderId(req.getOrderId())
					.colorId(req.getColorId())
					.build();
			
			Optional<OrderColorDetails> data =orderColorDetailsRepo.findById(colorDetailsId);
			
			if(data.isPresent()) {
				
				OrderColorDetails p =data.get();
				Map<String,String> colorRes =new HashMap<String,String>();
				colorRes.put("OrderId", p.getId().getOrderId());
				colorRes.put("ColorId", p.getId().getColorId());
				colorRes.put("ColorCode", p.getColorCode().toString());
				colorRes.put("ColorName", p.getColorName());
				colorRes.put("TotalPieces", p.getTotalPieces().toString());
				
				response.setMessage("Success");
				response.setResponse(colorRes);
				response.setError(null);
			}else{
				response.setMessage("Failed");
				response.setResponse("No Record Found");
				response.setError(null);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getAllOrderDeatils(String status) {
		CommonResponse response = new CommonResponse();
		try {
			List<OrderDetails> list =orderDetailsRepos.findByStatusIgnoreCase(status,Sort.by("entryDate").descending());
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					List<OrderChallanDetails> challan =orderChallanDetailsRepos.findByIdOrderId(p.getOrderId());
					Map<String,String> map =new HashMap<String,String>();
					map.put("OrderId", p.getOrderId());
					map.put("InwardDate", sdf.format(p.getInwardDate()));
					map.put("LotNumber", p.getLotNumber());
					map.put("Status", status);
					map.put("NoOfChallans", String.valueOf(challan.size()));
					map.put("TotalPieces", challan.stream().map(k ->k.getTotalPieces()).collect(Collectors.summingInt(c-> c)).toString());
					map.put("OrderCreatedDate", sdf.format(p.getEntryDate()));
					
					mapList.add(map);
					
				});
				
				response.setMessage("Success");
				response.setResponse(mapList);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Record Found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse updateOrderStatus(UpdateOrderStatusReq reqList) {
		CommonResponse response = new CommonResponse();
		try {
			
			for(String req : reqList.getOrderId()) {
				Optional<OrderDetails> data =orderDetailsRepos.findById(req);
				if(data.isPresent()) {
					OrderDetails details =data.get();
					details.setStatus(reqList.getOrderStatus());
					if("P".equalsIgnoreCase(reqList.getOrderStatus())) {
						details.setProductionDate(new Date());	
					}else if("D".equalsIgnoreCase(reqList.getOrderStatus())) {
						details.setDeliveryDate(new Date());
					}else if("B".equalsIgnoreCase(reqList.getOrderStatus())) {
						details.setBillingDate(new Date());
					}
					
					orderDetailsRepos.save(details);
			
				}
			}
				
		  response.setMessage("Success");
		  response.setResponse("Order updated successfully");
		  response.setError(null);
			
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Failed");
			response.setResponse(e.getMessage());
			response.setError(null);
			return response;
		}
		return response;
	}

	@Override
	public CommonResponse saveOrderBilling(OrderBillingRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.orderBilling(req);
			if(error.isEmpty()) {
				OrderBillingDetails billingDetails =OrderBillingDetails.builder()
						.deliveryType(req.getDeliveryType())
						.entryDate(new Date())
						.noOfboxDozens(Integer.valueOf(req.getNoOfBoxesPerPieces()))
						.noOfPieces(Integer.valueOf(req.getNoOfPieces()))
						.orderId(req.getOrderId())
						.remarks(StringUtils.isBlank(req.getRemarks())?null:req.getRemarks())
						.status("Y")
						.totalBox(Integer.valueOf(req.getTotalBox()))
						.totalPieces(Integer.valueOf(req.getTotalPieces()))
						.updatedBy(req.getUpdatedBy())
						.build();
				
				orderBillingRepo.save(billingDetails);
				
				response.setMessage("Success");
				response.setResponse("Data saved Successfully");
				response.setError(null);
			}else {
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse viewOrderBilling(EditOrderDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			Optional<OrderBillingDetails> data =orderBillingRepo.findById(req.getOrderId());
			if(data.isPresent()) {
				OrderBillingDetails obd =data.get();
				HashMap<String, String> res = new HashMap<String, String>();
				res.put("OrderId", obd.getOrderId());
				res.put("TotalPieces", obd.getTotalPieces().toString());
				res.put("DeliveryType", obd.getDeliveryType());
				res.put("NoOfBoxOrDozens", obd.getNoOfboxDozens().toString());
				res.put("NoOfPieces", obd.getNoOfPieces().toString());
				res.put("TotalBox", obd.getTotalBox().toString());
				res.put("Remarks", obd.getRemarks());
				res.put("UpdatedBy", obd.getUpdatedBy());
				res.put("EntryDate", sdf.format(obd.getEntryDate()));
				response.setMessage("Success");
				response.setResponse(res);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Data Found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getAllOrderBilling() {
		CommonResponse response = new CommonResponse();
		try {
			List<OrderBillingDetails> data =orderBillingRepo.findAll();
			if(data.size()>0) {
				List<Map<String,String>> resList = new ArrayList<Map<String,String>>();
				for(OrderBillingDetails obd : data) {
					HashMap<String, String> res = new HashMap<String, String>();
					res.put("OrderId", obd.getOrderId());
					res.put("TotalPieces", obd.getTotalPieces().toString());
					res.put("DeliveryType", obd.getDeliveryType());
					res.put("NoOfBoxOrDozens", obd.getNoOfboxDozens().toString());
					res.put("NoOfPieces", obd.getNoOfPieces().toString());
					res.put("TotalBox", obd.getTotalBox().toString());
					res.put("Remarks", obd.getRemarks());
					res.put("UpdatedBy", obd.getUpdatedBy());
					res.put("EntryDate", sdf.format(obd.getEntryDate()));
					resList.add(res);
				}
					response.setMessage("Success");
					response.setResponse(resList);
					response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Data Found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	

}
