package com.siddhartha.garments.serviceImpl;

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

import com.siddhartha.garments.entity.WorkerEntryDetails;
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
	private SimpleDateFormat sdf;

	@Override
	public CommonResponse workerEntrySave(WorkerEntryDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> error =validation.validateWorkerEntry(req);
			
			if(error.isEmpty()) {
				WorkerEntryDetails workerEntryDetails =WorkerEntryDetails.builder()
						.serialNo(StringUtils.isBlank(req.getSerialNo())?workerEntryRepository.count()+1:Long.valueOf(req.getSerialNo()))
						.challanId(req.getChallanId())
						.colorId(req.getColorId())
						.damagedPieces(Integer.valueOf(req.getDamagedPieces()))
						.entryDate(new Date())
						.goodPieces(Integer.valueOf(req.getGoodPieces()))
						.operatorId(req.getOperatorId())
						.orderId(req.getOrderId())
						.sectionId(Integer.valueOf(req.getSectionId()))
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.updatedBy(req.getUpdatedBy())
						.goodPieces(Integer.valueOf(req.getGoodPieces()))
						.workedPieces(Integer.valueOf(req.getWorkedPieces()))
						.totalPieces(Integer.valueOf(req.getTotalPieces()))
						.build();
				workerEntryRepository.save(workerEntryDetails);
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
			}else {
				response.setError(null);
				response.setMessage("Error");
				response.setResponse(error);
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
					map.put("Wokedpieces", p.getWorkedPieces().toString());
					map.put("GoodPieces", p.getGoodPieces().toString());
					map.put("DamagePieces", p.getDamagedPieces().toString());
					map.put("UpdatedBy", p.getUpdatedBy());
					map.put("TotalPieces", p.getTotalPieces().toString());
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
					map.put("Wokedpieces", p.getWorkedPieces().toString());
					map.put("GoodPieces", p.getGoodPieces().toString());
					map.put("DamagePieces", p.getDamagedPieces().toString());
					map.put("UpdatedBy", p.getUpdatedBy());
					map.put("TotalPieces", p.getTotalPieces().toString());
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
				map.put("Wokedpieces", p.getWorkedPieces().toString());
				map.put("GoodPieces", p.getGoodPieces().toString());
				map.put("DamagePieces", p.getDamagedPieces().toString());
				map.put("UpdatedBy", p.getUpdatedBy());
				map.put("TotalPieces", p.getTotalPieces().toString());
					
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
