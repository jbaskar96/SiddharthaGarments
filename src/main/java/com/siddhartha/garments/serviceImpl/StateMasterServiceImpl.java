package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.StateMaster;
import com.siddhartha.garments.repository.DistrictMasterRepository;
import com.siddhartha.garments.repository.StateMasterRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.StateSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.StateMasterService;

@Service
public class StateMasterServiceImpl implements StateMasterService{
	
	@Autowired
	private StateMasterRepository repository;
	
	@Autowired
	private DistrictMasterRepository districtRepo;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Autowired
	private InputValidationServiceImpl validation;

	@Override
	public CommonResponse saveState(StateSaveRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error =validation.validateMasterInfo(req, "STATE");
			
			if(error.isEmpty()) {
				Long stateCode =repository.count()+1;
				StateMaster stateMaster =StateMaster.builder()
						.stateCode(StringUtils.isBlank(req.getStateCode()) ?stateCode.toString():req.getStateCode())
						.stateName(req.getStateName())
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.entryDate(new Date())
						.build();
				repository.save(stateMaster);
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
		}
		return response;
	}

	@Override
	public CommonResponse editState(Integer stateCode) {
		CommonResponse response = new CommonResponse();
		try {
			StateMaster p = repository.findById(stateCode.toString()).orElse(null);
			if(p !=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("StateCode", p.getStateCode());
				map.put("StateName",p.getStateName());
				map.put("Status", p.getStatus());
				map.put("CreatedDate",sdf.format(p.getEntryDate()));
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

	@Override
	public CommonResponse getAllState() {
		CommonResponse response = new CommonResponse();
		try {
			List<StateMaster> list =repository.findAll();
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("StateCode", p.getStateCode());
					map.put("StateName",p.getStateName());
					map.put("Status", p.getStatus());
					map.put("CreatedDate",sdf.format(p.getEntryDate()));
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
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
	public CommonResponse deleteState(Integer stateCode) {
		CommonResponse response = new CommonResponse();
		try {
			repository.deleteById(stateCode.toString());
			districtRepo.deleteByDistIdStateCode(stateCode);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("Data deleted Successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
