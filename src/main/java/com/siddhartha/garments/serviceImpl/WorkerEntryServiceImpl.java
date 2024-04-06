package com.siddhartha.garments.serviceImpl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.OrderDetails;
import com.siddhartha.garments.entity.ProductSectionMaster;
import com.siddhartha.garments.entity.ProductSectionMasterId;
import com.siddhartha.garments.entity.WorkerEntryDetails;
import com.siddhartha.garments.repository.OrderDetailsRepository;
import com.siddhartha.garments.repository.ProductSectionMasterRepository;
import com.siddhartha.garments.repository.WorkerEntryRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.WorkerEntryService;

@Service
public class WorkerEntryServiceImpl implements WorkerEntryService {
	
	
	@Autowired
	private WorkerEntryRepository workerEntryRepository;
	
	@Autowired
	private InputValidationServiceImpl validation;
	
	@Autowired
	private ProductSectionMasterRepository psmRepo;
	
	@Autowired
	private OrderDetailsRepository odRepo;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	public static final NumberFormat formatter = new DecimalFormat("#0.00"); 

	@Override
	public CommonResponse workerEntrySave(WorkerEntryDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> error =validation.validateWorkerEntry(req);
			
			if(error.isEmpty()) {
				
				OrderDetails od = odRepo.findById(req.getOrderId()).get();
				Integer companyId =od.getCompanyId();
				Integer productId =od.getProductId();
				Integer sectionId =Integer.valueOf(req.getSectionId());
				
				ProductSectionMasterId id = ProductSectionMasterId.builder()
						.companyId(companyId)
						.productId(productId)
						.sectionId(sectionId)
						.build();
				
				ProductSectionMaster psm =psmRepo.findById(id).get();
				Double perPiecesAmt =psm.getPiecesAmount();
				Integer noOfPieces =psm.getNoOfPieces();
				Double totalAmount =0D;
				if(StringUtils.isNotBlank(req.getSerialNo())) {
					WorkerEntryDetails wed =workerEntryRepository.findById(Long.valueOf(req.getSerialNo())).get();
					totalAmount = wed.getEmployeeWorkedPieces() / noOfPieces * perPiecesAmt;
				}else {
					totalAmount =Integer.valueOf(req.getEmployeeWorkedPieces()) / noOfPieces * perPiecesAmt;
				}
				
				String formtAmt =formatter.format(totalAmount);
				
				WorkerEntryDetails workerEntryDetails =WorkerEntryDetails.builder()
						.serialNo(StringUtils.isBlank(req.getSerialNo())?workerEntryRepository.count()+1:Long.valueOf(req.getSerialNo()))
						.challanId(req.getChallanId())
						.colorId(req.getColorId())
						.entryDate(new Date())
						.operatorId(req.getOperatorId())
						.orderId(req.getOrderId())
						.sectionId(Integer.valueOf(req.getSectionId()))
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.updatedBy(req.getUpdatedBy())
						.employeeWorkedPieces(Integer.valueOf(req.getEmployeeWorkedPieces()))
						.totalPieces(Integer.valueOf(req.getTotalPieces()))
						.size(Integer.valueOf(req.getSize()))
						.operatorName(req.getOperatorName())
						.sectionName(req.getSectionName())
						.colorName(req.getColorName())
						.lotNumber(req.getLotNumber())
						.perPiecesAmount(perPiecesAmt)
						.totalAmount(Double.valueOf(formtAmt))
						.numberOfPieces(noOfPieces)
						.companyId(companyId)
						.productId(productId)
						.build();
				workerEntryRepository.save(workerEntryDetails);
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
			}else {
				response.setError(error);
				response.setMessage("Error");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getWorkerEntryByOrderId(String orderId) {
		CommonResponse response = new CommonResponse();
		try {
			List<WorkerEntryDetails> workerDetails =workerEntryRepository.findByOrderId(orderId);
			if(!workerDetails.isEmpty()) {
				List<Map<String,String>> mapList =new ArrayList<>();
				workerDetails.forEach(p ->{
					HashMap<String, String> map =new HashMap<String, String>();
					map.put("SerialNo", p.getSerialNo().toString());
					map.put("OperatorId", p.getOperatorId());
					map.put("SectionId", p.getSectionId().toString());
					map.put("OrderId", p.getOrderId());
					map.put("ChallanId", p.getChallanId());
					map.put("ColorId", p.getColorId().toString());
					map.put("WokedDate", sdf.format(p.getEntryDate()));
					map.put("EmployeeWorkedPieces", p.getEmployeeWorkedPieces().toString());
					map.put("TotalAmount", p.getTotalAmount().toString());
					map.put("UpdatedBy", p.getUpdatedBy());
					map.put("TotalPieces", p.getTotalPieces().toString());
					map.put("OperatorName", p.getOperatorName());
					map.put("SectionName", p.getSectionName());
					map.put("LotNumber", p.getLotNumber());
					map.put("Size", p.getSize().toString());
					map.put("ColorName", p.getColorName());
					map.put("NoOfPieces", p.getNumberOfPieces().toString());
					map.put("PerPiecesAmt", p.getPerPiecesAmount().toString());
					
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
	public CommonResponse getAllWorkerEntryDetail() {
		CommonResponse response = new CommonResponse();
		try {
			List<WorkerEntryDetails> workerDetails =workerEntryRepository.findAll();
			if(!workerDetails.isEmpty()) {
				List<Map<String,String>> mapList =new ArrayList<>();
				workerDetails.forEach(p ->{
					HashMap<String, String> map =new HashMap<String, String>();
					map.put("SerialNo", p.getSerialNo().toString());
					map.put("OperatorId", p.getOperatorId());
					map.put("SectionId", p.getSectionId().toString());
					map.put("OrderId", p.getOrderId());
					map.put("ChallanId", p.getChallanId());
					map.put("ColorId", p.getColorId().toString());
					map.put("WokedDate", sdf.format(p.getEntryDate()));
					map.put("EmployeeWorkedPieces", p.getEmployeeWorkedPieces().toString());
					map.put("TotalAmount", p.getTotalAmount().toString());
					map.put("UpdatedBy", p.getUpdatedBy());
					map.put("TotalPieces", p.getTotalPieces().toString());
					map.put("OperatorName", p.getOperatorName());
					map.put("SectionName", p.getSectionName());
					map.put("LotNumber", p.getLotNumber());
					map.put("Size", p.getSize().toString());
					map.put("ColorName", p.getColorName());
					map.put("NoOfPieces", p.getNumberOfPieces().toString());
					map.put("PerPiecesAmt", p.getPerPiecesAmount().toString());
					
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
	public CommonResponse editWorkerEntryDetail(String serialNo) {
		CommonResponse response = new CommonResponse();
		try {
			Optional<WorkerEntryDetails> workerDetails =workerEntryRepository.findById(Long.valueOf(serialNo));
			if(workerDetails.isPresent()) {
				WorkerEntryDetails p =workerDetails.get();
				HashMap<String, String> map =new HashMap<String, String>();
				map.put("SerialNo", p.getSerialNo().toString());
				map.put("OperatorId", p.getOperatorId());
				map.put("SectionId", p.getSectionId().toString());
				map.put("OrderId", p.getOrderId());
				map.put("ChallanId", p.getChallanId());
				map.put("ColorId", p.getColorId().toString());
				map.put("WokedDate", sdf.format(p.getEntryDate()));
				map.put("EmployeeWorkedPieces", p.getEmployeeWorkedPieces().toString());
				map.put("TotalAmount", p.getTotalAmount().toString());
				map.put("UpdatedBy", p.getUpdatedBy());
				map.put("TotalPieces", p.getTotalPieces().toString());
				map.put("OperatorName", p.getOperatorName());
				map.put("SectionName", p.getSectionName());
				map.put("LotNumber", p.getLotNumber());
				map.put("Size", p.getSize().toString());
				map.put("ColorName", p.getColorName());
				map.put("NoOfPieces", p.getNumberOfPieces().toString());
				map.put("PerPiecesAmt", p.getPerPiecesAmount().toString());
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(map);
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


}
