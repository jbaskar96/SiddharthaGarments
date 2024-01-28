package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Tuple;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.ChallanDetails;
import com.siddhartha.garments.entity.ChallanDetailsId;
import com.siddhartha.garments.entity.ColorDeatils;
import com.siddhartha.garments.entity.ColorDeatilsId;
import com.siddhartha.garments.entity.LotDeatils;
import com.siddhartha.garments.entity.Meterialdetails;
import com.siddhartha.garments.entity.MeterialdetailsId;
import com.siddhartha.garments.repository.ChallanRepository;
import com.siddhartha.garments.repository.ColorDetailsRepository;
import com.siddhartha.garments.repository.LotCreationRepository;
import com.siddhartha.garments.repository.MeterialDetailsRepository;
import com.siddhartha.garments.request.ChallanInfoRequest;
import com.siddhartha.garments.request.ColorDetailsRequest;
import com.siddhartha.garments.request.ColorInfoReq;
import com.siddhartha.garments.request.DeliveryMoveRequest;
import com.siddhartha.garments.request.EditColorDetailReq;
import com.siddhartha.garments.request.EditMeterialDetailReq;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.GetColorDetailsReq;
import com.siddhartha.garments.request.LotDetailsRequest;
import com.siddhartha.garments.request.MeterialDetailsReq;
import com.siddhartha.garments.request.MeterialDetailsRequest;
import com.siddhartha.garments.request.MeterialInfoReq;
import com.siddhartha.garments.request.ProductionMoveReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.LotCreationService;

@Service
public class LotCreationServiceImpl implements LotCreationService{
	
	@Autowired
	private LotCreationRepository repository;
	
	@Autowired
	private ChallanRepository challanRepository;
	
	@Autowired
	private ColorDetailsRepository colorDetailsRepo;
	
	@Autowired
	private MeterialDetailsRepository meterialDetailsRepo;
	
	@Autowired
	private CriteriaQueryServiceImpl criteriaQuery;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Autowired
	private InputValidationServiceImpl validation;


	@Override
	public CommonResponse lotCreation(LotDetailsRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> error =validation.validateLotInfo(req);
			
			if(error.isEmpty()) {
				LotDeatils lot =repository.findById(req.getOrderId()).orElse(null);
				LotDeatils  lotDeatils =LotDeatils.builder()
						.address(req.getAddress())
						.city(req.getCity())
						.stateCode(Integer.valueOf(req.getStateCode()))
						.districtCode(Integer.valueOf(req.getDistrictCode()))
						.entryDate(lot==null?new Date():lot.getEntryDate())
						.deliveryDate(lot==null?null:lot.getDeliveryDate())
						.productionDate(lot==null?null:lot.getProductionDate())
						.productionRemarks(lot==null?"":lot.getProductionRemarks())
						.deliveryRemarks(lot==null?"":lot.getDeliveryRemarks())
						.updatedDate(new Date())
						.updatedBy(req.getUpdatedBy())
						.gstNo(req.getGstNo())
						.inwardDate(sdf.parse(req.getInwardDate()))
						.lotNo(req.getLotNo())
						.phoneNo(req.getPhoneNo())
						.productCode(Integer.valueOf(req.getProductCode()))
						.orderId(StringUtils.isBlank(req.getOrderId())?"ORDER_0"+getOrderId():req.getOrderId())
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.build();
				
				LotDeatils savedData =repository.save(lotDeatils);
				
				for(ChallanInfoRequest cha : req.getChallanInfo()) {
					
					ChallanDetailsId challanDetailsId = ChallanDetailsId.builder()
							.challanId(StringUtils.isBlank(cha.getChallanId())?"CHALLAN_0"+getChallanId(savedData.getOrderId())
							:cha.getChallanId())
							.orderId(savedData.getOrderId())
							.build();
					
					ChallanDetails challanDetails = ChallanDetails.builder()
							.chaId(challanDetailsId)
							.challanDate(sdf.parse(cha.getChallanDate()))
							.challanNo(cha.getChallanNo())
							.pieceyn(cha.getPieceYn())
							.entryDate(savedData.getEntryDate())
							.quality(cha.getQuality())
							.size(Integer.valueOf(cha.getSize()))
							.status(StringUtils.isBlank(cha.getStatus())?"Y":cha.getStatus())
							.totalPiece(Integer.valueOf(cha.getTotalPieces()))
							.build();
					
					challanRepository.save(challanDetails);
				}
				
				HashMap<String,String> map = new HashMap<>();
				map.put("OrderId", savedData.getOrderId());
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(map);
			}else {
				response.setError(error);
				response.setMessage("Error");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setError(e.getMessage());
			response.setMessage("Failed");
			return response;
		}
		return response;
	}

	@Override
	public CommonResponse getLotDetails(String orderId) {
		CommonResponse response = new CommonResponse();
		try {
			Optional<LotDeatils> deatils =repository.findById(orderId);
			if(deatils.isPresent()) {
				LotDeatils lo =deatils.get();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("OrderId", lo.getOrderId());
				map.put("LotNo", lo.getLotNo());
				map.put("CompanyName", lo.getCompanyName());
				map.put("StateCode", lo.getStateCode().toString());
				map.put("DistrictCode", lo.getDistrictCode().toString());
				map.put("City", lo.getCity());
				map.put("Address", lo.getAddress());
				map.put("GstNo", lo.getGstNo());
				map.put("PhoneNo", lo.getPhoneNo());
				map.put("InwardDate", sdf.format(lo.getInwardDate()));
				map.put("ProductionDate", lo.getProductionDate()==null?"":sdf.format(lo.getProductionDate()));
				map.put("DeliveryDate", lo.getDeliveryDate()==null?"":sdf.format(lo.getDeliveryDate()));
				map.put("ProductionComments", StringUtils.isBlank(lo.getProductionRemarks())?"":lo.getProductionRemarks());
				map.put("DeliveryComments",StringUtils.isBlank(lo.getDeliveryRemarks())?"":lo.getDeliveryRemarks());
				map.put("Status", lo.getStatus());
				map.put("OrderCreationDate",sdf.format(lo.getEntryDate()));
				map.put("UpdatedBy", lo.getUpdatedBy());
				map.put("UpdatedDate",sdf.format(lo.getUpdatedDate()));
				map.put("ProductCode", lo.getProductCode().toString());
				
				List<ChallanDetails> challanDetails =challanRepository.findByChaIdOrderId(orderId);
				List<Map<String,String>> list = new ArrayList<>();
				for(ChallanDetails cha :challanDetails) {
					HashMap<String,String> chaObj = new HashMap<String, String>();
					chaObj.put("OrderId", cha.getChaId().getOrderId());
					chaObj.put("ChallanId", cha.getChaId().getChallanId());
					chaObj.put("ChallanNo", cha.getChallanNo());
					chaObj.put("ChallanDate", sdf.format(cha.getChallanDate()));
					chaObj.put("PieceYn", cha.getPieceyn());
					chaObj.put("TotalPieces", cha.getTotalPiece().toString());
					chaObj.put("Quantity", cha.getQuality());
					chaObj.put("Status", cha.getStatus());
					chaObj.put("Size", cha.getSize().toString());
					
					list.add(chaObj);
				}
				map.put("ChallanInfo", list);
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(map);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse challanColorCreation(ColorDetailsRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> error =validation.validateColorInfo(req);

			if(error.isEmpty()) {
				for(ColorInfoReq col : req.getColorInfo()) {
					ColorDeatilsId colorDeatilsId = ColorDeatilsId.builder()
							.orderId(req.getOrderId())
							.challanId(req.getChallanId())
							.colorId(StringUtils.isBlank(col.getColorId())?"COLOR_0"+
									getColorId(req.getOrderId(), req.getChallanId())
									:col.getColorId())
							.build();
					
					ColorDeatils colorDeatils = ColorDeatils.builder()
							.colorCode(Integer.valueOf(col.getColorCode()))
							.entryDate(new Date())
							.colId(colorDeatilsId)
							.noOfBundle(Integer.valueOf(col.getNoOfBundles()))
							.piecesCount(Integer.valueOf(col.getPiecesCount()))
							.receivedElastic(Integer.valueOf(col.getReceivedElastic()))
							.requiredElastic(Integer.valueOf(col.getRequiredElastic()))
							.receivedFoldingWeight(Double.valueOf(col.getReceivedFoldingWeigth()))
							.requiredFoldingWeight(Double.valueOf(col.getRequiredFoldingWeight()))
							.status(StringUtils.isBlank(col.getStatus())?"Y":col.getStatus())
							.remarks(StringUtils.isBlank(col.getRemarks())?"":col.getRemarks())
							.totalDozen(Integer.valueOf(col.getTotalDozen()))
							.build();
					
					colorDetailsRepo.save(colorDeatils);
				}
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("Data saved Successfully");
			}else {
				response.setError(error);
				response.setMessage("Error");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setError(null);
			response.setMessage("Failed");
			response.setResponse("Data saved Failed");
			return response;
		}
		return response;
	}

	@Override
	public CommonResponse challanMeterialSave(MeterialDetailsRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> error =validation.validateMeterialInfo(req);

			if(error.isEmpty()) {
				
				for(MeterialInfoReq met :req.getMeterialInfo()) {
					
					MeterialdetailsId meterialdetailsId =MeterialdetailsId.builder()
							.orderId(req.getOrderId())
							.challanId(req.getChallanId())
							.meterialId(StringUtils.isBlank(met.getMeterialId())?"METAL_0"+
									getMeterialId(req.getOrderId(), req.getChallanId()):met.getMeterialId())
							.build();
					
					Meterialdetails meterialdetails =Meterialdetails.builder()
							.bodySticker(Integer.valueOf(met.getBodySticker()))
							.entryDate(new Date())
							.hologramSticker(Integer.valueOf(met.getHoloGramSticker()))
							.metId(meterialdetailsId)
							.innerCard(Integer.valueOf(met.getInnercard()))
							.lable(Integer.valueOf(met.getLable()))
							.masterBox(Integer.valueOf(met.getMasterBox()))
							.metalType(met.getMetalType())
							.mrpSticker(Integer.valueOf(met.getMrpSticker()))
							.policyBag(Integer.valueOf(met.getPolyBag()))
							.remarks(StringUtils.isBlank(met.getRemarks())?"":met.getRemarks())
							.singleBox(Integer.valueOf(met.getSingleBox()))
							.sizeSticker(Integer.valueOf(met.getSizeSticker()))
							.status(StringUtils.isBlank(met.getStatus())?"Y":met.getStatus())
							
							.build();
					
					meterialDetailsRepo.save(meterialdetails);
					
				}
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("Data saved Successfully");
			}else {
				response.setError(error);
				response.setMessage("Error");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setError(null);
			response.setMessage("Failed");
			response.setResponse("Data saved Failed");
			return response;
		}
		return response;
	}

	@Override
	public CommonResponse getColorDetails(GetColorDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ColorDeatils> list =colorDetailsRepo.findByColIdOrderIdAndColIdChallanId(req.getOrderId(),req.getChallanNo());
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList =new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("OrderId", p.getColId().getOrderId());
					map.put("ChallanId", p.getColId().getChallanId());
					map.put("ColorId", p.getColId().getColorId());
					map.put("ColorCode", p.getColorCode().toString());
					map.put("PieceCount", p.getPiecesCount().toString());
					map.put("NoOfBundle", p.getNoOfBundle().toString());
					map.put("TotalDozen", p.getTotalDozen().toString());
					map.put("RequiredFoldingWeight", p.getRequiredFoldingWeight().toString());
					map.put("ReceviedFoldingWeight", p.getReceivedFoldingWeight().toString());
					map.put("RequiredElastic", p.getRequiredElastic().toString());
					map.put("ReceivedElastic", p.getReceivedElastic().toString());
					map.put("Status", p.getStatus());
					map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
					mapList.add(map);
					
				});
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(mapList);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No Record found");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getMeterialDetails(MeterialDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<Meterialdetails> list =meterialDetailsRepo.findByMetIdOrderIdAndMetIdChallanId(req.getOrderId(),req.getChallanNo());
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList =new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("OrderId", p.getMetId().getOrderId());
					map.put("ChallanId", p.getMetId().getChallanId());
					map.put("MeterialId", p.getMetId().getMeterialId());
					map.put("MetalId", p.getMetalType().toString());
					map.put("Label", p.getLable().toString());
					map.put("SizeSticker", p.getSizeSticker().toString());
					map.put("InnerCard", p.getInnerCard().toString());
					map.put("PolicyBag", p.getPolicyBag().toString());
					map.put("SingleBox", p.getSingleBox().toString());
					map.put("MasterBox", p.getMasterBox().toString());
					map.put("MrpSticker", p.getMrpSticker().toString());
					map.put("BodySticker", p.getBodySticker().toString());
					map.put("HoloGramSticker", p.getHologramSticker().toString());
					map.put("Status", p.getStatus());
					map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
					mapList.add(map);
					
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(mapList);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No Record found");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse editColorDetail(EditColorDetailReq req) {
		CommonResponse response = new CommonResponse();
		try {
			ColorDeatilsId colorDeatilsId =ColorDeatilsId.builder()
					.orderId(req.getOrderId())
					.challanId(req.getChallanNo())
					.colorId(req.getColorId())
					.build();
			
			Optional<ColorDeatils> colorDeatils =colorDetailsRepo.findById(colorDeatilsId);
			
			if(colorDeatils.isPresent()) {
				ColorDeatils p =colorDeatils.get();
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("OrderId", p.getColId().getOrderId());
				map.put("ChallanId", p.getColId().getChallanId());
				map.put("ColorId", p.getColId().getColorId());
				map.put("ColorCode", p.getColorCode().toString());
				map.put("PieceCount", p.getPiecesCount().toString());
				map.put("NoOfBundle", p.getNoOfBundle().toString());
				map.put("TotalDozen", p.getTotalDozen().toString());
				map.put("RequiredFoldingWeight", p.getRequiredFoldingWeight().toString());
				map.put("ReceviedFoldingWeight", p.getReceivedFoldingWeight().toString());
				map.put("RequiredElastic", p.getRequiredElastic().toString());
				map.put("ReceivedElastic", p.getReceivedElastic().toString());
				map.put("Status", p.getStatus());
				map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(map);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse(null);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse editMeterialDetail(EditMeterialDetailReq req) {
		CommonResponse response = new CommonResponse();
		try {
			MeterialdetailsId meterialdetailsId =MeterialdetailsId.builder()
					.orderId(req.getOrderId())
					.challanId(req.getChallanNo())
					.meterialId(req.getMeterialId())
					.build();
			
			Optional<Meterialdetails> meterialdetails =meterialDetailsRepo.findById(meterialdetailsId);
			
			if(meterialdetails.isPresent()) {
				Meterialdetails p =meterialdetails.get();
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("OrderId", p.getMetId().getOrderId());
				map.put("ChallanId", p.getMetId().getChallanId());
				map.put("MeterialId", p.getMetId().getMeterialId());
				map.put("MetalId", p.getMetalType().toString());
				map.put("Label", p.getLable().toString());
				map.put("SizeSticker", p.getSizeSticker().toString());
				map.put("InnerCard", p.getInnerCard().toString());
				map.put("PolicyBag", p.getPolicyBag().toString());
				map.put("SingleBox", p.getSingleBox().toString());
				map.put("MasterBox", p.getMasterBox().toString());
				map.put("MrpSticker", p.getMrpSticker().toString());
				map.put("BodySticker", p.getBodySticker().toString());
				map.put("HoloGramSticker", p.getHologramSticker().toString());
				map.put("Status", p.getStatus());
				map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(map);
			}else {
				
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getOrderDeatils(String status) {
		CommonResponse response = new CommonResponse();
		try {
			List<Tuple> list =criteriaQuery.getAllOrderDetails(status);
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("OrderId", p.get("orderId")==null?"":p.get("orderId").toString());
					map.put("LotNo", p.get("lotNo")==null?"":p.get("lotNo").toString());
					map.put("Status", p.get("status")==null?"":p.get("status").toString());
					map.put("TotalSizes", p.get("totalSize")==null?"":p.get("totalSize").toString());
					map.put("Inwarddate", p.get("inwardDate")==null?"":sdf.format(p.get("inwardDate")));
					map.put("ProductionDate", p.get("productionDate")==null?"":sdf.format(p.get("productionDate")));
					map.put("DeliveryDate", p.get("deliveryDate")==null?"":sdf.format(p.get("deliveryDate")));
					map.put("CompanyName", p.get("companyName")==null?"":p.get("companyName").toString());
					map.put("PhoneNo", p.get("phoneNo")==null?"":p.get("phoneNo").toString());
					map.put("GstNo",p.get("gstNo")==null?"":p.get("gstNo").toString());
					mapList.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(mapList);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data Found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse moveProduction(ProductionMoveReq req) {
		CommonResponse response = new CommonResponse();
		try {
			Optional<LotDeatils> lot =repository.findById(req.getOrderId());
			if(lot.isPresent()) {
				LotDeatils l =lot.get();
				l.setStatus("P");
				l.setProductionDate(new Date());
				l.setProductionRemarks(StringUtils.isBlank(req.getRemarks())?"":req.getRemarks());
				repository.save(l);
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("Data moved successfully to Production");
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("Data moved failed to Production");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse moveDelivery(DeliveryMoveRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			Optional<LotDeatils> lot =repository.findById(req.getOrderId());
			if(lot.isPresent()) {
				LotDeatils l =lot.get();
				l.setStatus("D");
				l.setDeliveryDate(new Date());
				l.setDeliveryRemarks(StringUtils.isBlank(req.getRemarks())?"":req.getRemarks());
				repository.save(l);
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("Data moved successfully to Delivery");
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("Data moved failed to Delivery");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private Long getOrderId() {
		return repository.count()+1;
	}
	
	private Long getChallanId(String orderId) {
		return (long)challanRepository.findByChaIdOrderId(orderId).size()+1;
	}

	private Long getColorId(String orderId,String challanId) {
		return (long)colorDetailsRepo.findByColIdOrderIdAndColIdChallanId(orderId, challanId).size()+1;
	}
	
	private Long getMeterialId(String orderId,String challanId) {
		return (long)meterialDetailsRepo.findByMetIdOrderIdAndMetIdChallanId(orderId, challanId).size()+1;
	}
}
