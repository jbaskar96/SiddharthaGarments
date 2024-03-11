package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
import com.siddhartha.garments.entity.OrderChallanDetails;
import com.siddhartha.garments.entity.OrderChallanDetailsId;
import com.siddhartha.garments.entity.OrderDetails;
import com.siddhartha.garments.entity.OrderSizeColorDetails;
import com.siddhartha.garments.entity.OrderSizeColorDetailsId;
import com.siddhartha.garments.repository.OrderChallanDetailsRepository;
import com.siddhartha.garments.repository.OrderDetailsRepository;
import com.siddhartha.garments.repository.OrderSizeColorDetailsRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.response.CommonResponse;
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
	private OrderSizeColorDetailsRepository orderSizeColorDetailsRepo;
	
	@Autowired
	private InputValidationServiceImpl validation;
	
	@Override
	public CommonResponse saveOrder(OrderDetailsRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.validateOrder(req);
			if(error.isEmpty()) {
				
				OrderDetails orderDetails = OrderDetails.builder()
						.orderId(StringUtils.isBlank(req.getOrderId())?generateNumber("O",""):req.getOrderId())
						.companyId(Integer.valueOf(req.getCompanyId()))
						.createdBy(req.getCreatedBy())
						.entryDate(new Date())
						.inwardDate(sdf.parse(req.getInwardDate()))
						.lotNumber(req.getLotNumber())
						.productId(Integer.valueOf(req.getProductId()))
						.remarks(StringUtils.isBlank(req.getRemarks())?"":req.getRemarks())
						.styleId(Integer.valueOf(req.getStyleId()))
						.status(req.getStatus())
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
		
		if("O".equals(type)) {
			Date date = new Date();
			String strDate =sdf.format(date);
			Integer no =(int)orderDetailsRepos.count()+1;
			number="ORDER/"+strDate+"/1000"+no+"";
		}else if("C".equals(type)) {
			Integer no =orderChallanDetailsRepos.countByIdOrderId(orderId)+1;
			number="CHA/1000"+no+"";
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
				orderRes.put("StyleId", o.getStyleId().toString());
				orderRes.put("LotNumber", o.getLotNumber());
				orderRes.put("InwardDate", sdf.format(o.getInwardDate()));
				orderRes.put("Status", o.getStatus());
				orderRes.put("CreatedBy", o.getCreatedBy());
				orderRes.put("Remarks", StringUtils.isBlank(o.getRemarks())?"":o.getRemarks());
				
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
					
					OrderSizeColorDetailsId colorDetailsId = OrderSizeColorDetailsId.builder()
							.orderId(r.getOrderId())
							.challanId(r.getChallanId())
							.colorId(StringUtils.isBlank(r.getColorId())?getColorId(r.getOrderId(),r.getChallanId()):r.getColorId())
							.build();

					OrderSizeColorDetails colorDetails = OrderSizeColorDetails.builder()
							.colorCode(Integer.valueOf(r.getColorCode()))
							.colorName(r.getColorName())
							.entryDate(new Date())
							.id(colorDetailsId)
							.totalPieces(Integer.valueOf(r.getTotalPieces()))
							.build();
					
					orderSizeColorDetailsRepo.save(colorDetails);
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

	private String getColorId(String orderId, String challanId) {
		Integer no =orderSizeColorDetailsRepo.findByIdOrderIdAndIdChallanId(orderId,challanId).size()+1;
		return "COL/1000"+no+"";
	}

	@Override
	public CommonResponse getOrderColorDetails(GetOrderSizeColorReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<OrderSizeColorDetails> list =orderSizeColorDetailsRepo.findByIdOrderIdAndIdChallanId(req.getOrderId(), req.getChallanId());
			if(!list.isEmpty()) {
				
				List<Map<String,String>> colorList = new ArrayList<>();
				list.forEach(p ->{
					Map<String,String> colorRes =new HashMap<String,String>();
					colorRes.put("OrderId", p.getId().getOrderId());
					colorRes.put("ChallanId", p.getId().getChallanId());
					colorRes.put("ColorId", p.getId().getColorId());
					colorRes.put("ColorCode", p.getColorCode().toString());
					colorRes.put("ColorName", p.getColorName());
					colorRes.put("TotalPieces", p.getTotalPieces().toString());
					
					colorList.add(colorRes);
					
				});
				
				response.setMessage("Success");
				response.setResponse(colorList);
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
	public CommonResponse editOrderColorDetails(GetOrderSizeColorReq req) {
		CommonResponse response = new CommonResponse();
		try {
			OrderSizeColorDetailsId colorDetailsId =OrderSizeColorDetailsId.builder()
					.orderId(req.getOrderId())
					.challanId(req.getChallanId())
					.colorId(req.getColorId())
					.build();
			
			Optional<OrderSizeColorDetails> data =orderSizeColorDetailsRepo.findById(colorDetailsId);
			
			if(data.isPresent()) {
				
				OrderSizeColorDetails p =data.get();
				Map<String,String> colorRes =new HashMap<String,String>();
				colorRes.put("OrderId", p.getId().getOrderId());
				colorRes.put("ChallanId", p.getId().getChallanId());
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

	

}
