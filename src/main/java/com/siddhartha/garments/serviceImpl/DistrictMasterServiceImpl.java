package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.DistrictMaster;
import com.siddhartha.garments.entity.DistrictMasterID;
import com.siddhartha.garments.repository.DistrictMasterRepository;
import com.siddhartha.garments.request.DisrictRequest;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.DistrictMasterService;

@Service
public class DistrictMasterServiceImpl implements DistrictMasterService {
	
	Logger log = LogManager.getLogger(DistrictMasterServiceImpl.class);
	
	@Autowired
	private DistrictMasterRepository repository;
	
	@Autowired
	private CriteriaQueryServiceImpl criteriaQuery;
	
	public static SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private InputValidationServiceImpl validation;

	@Override
	public CommonResponse saveDistrict(DisrictRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> error =validation.validateMasterInfo(req, "DISTRICT");
			
			if(error.isEmpty()) {
			
				Integer districtCode =StringUtils.isBlank(req.getDistrictCode())?criteriaQuery.getMaxOfCodeByStateCode(req.getStateCode())
						:Integer.valueOf(req.getDistrictCode());
				
				DistrictMasterID districtMasterID =DistrictMasterID.builder()
						.stateCode(Integer.valueOf(req.getStateCode()))
						.districtCode(districtCode)
						.build();
				
				DistrictMaster districtMaster =DistrictMaster.builder()
						.distId(districtMasterID)
						.districtName(req.getDistrictName())
						.entryDate(new Date())
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.build();
				repository.save(districtMaster);
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("data saved successfully");
			}else {
				response.setError(error);
				response.setMessage("Error");
				response.setResponse(null);
			}
		}catch (Exception e) {
			log.error(e);
		}
		return response;
	}

	@Override
	public CommonResponse getAllDistrict(Integer statecode) {
		CommonResponse response = new CommonResponse();
		try {
			List<DistrictMaster> list =repository.findByDistIdStateCode(statecode);
			if(!list.isEmpty()) {
				List<HashMap<String,String>> listRes =new ArrayList<>();
				list.forEach(p ->{
					HashMap<String,String> map = new HashMap<>();
					map.put("StateCode", p.getDistId().getStateCode().toString());
					map.put("DistrictCode",  p.getDistId().getDistrictCode().toString());
					map.put("DistrictName", p.getDistrictName());
					map.put("Status", p.getStatus());
					map.put("CreatedDate",sdf.format(p.getEntryDate()));
					listRes.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(listRes);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			log.error(e);
		}
		return response;
	}

	@Override
	public CommonResponse getAllDistrict(Integer stateCode, Integer districtCode) {
		CommonResponse response = new CommonResponse();
		try {
			DistrictMasterID districtMasterID =DistrictMasterID.builder()
					.stateCode(stateCode)
					.districtCode(districtCode)
					.build();
			
			DistrictMaster p =repository.findById(districtMasterID).orElse(null);
			if(p!=null) {
				HashMap<String,String> map = new HashMap<>();
				map.put("StateCode",  p.getDistId().getStateCode().toString());
				map.put("DistrictCode",  p.getDistId().getDistrictCode().toString());
				map.put("DistrictName", p.getDistrictName());
				map.put("Status", p.getStatus());
				map.put("CreatedDate",sdf.format(p.getEntryDate()));
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(map);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			log.error(e);
		}
		return response;
	}


	@Override
	public CommonResponse deleteDistrict(Integer stateCode, Integer districtCode) {
		CommonResponse response = new CommonResponse();
		try {
			DistrictMasterID district = new DistrictMasterID();
			district.setDistrictCode(districtCode);
			district.setStateCode(stateCode);
			
			repository.deleteById(district);
			
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("deleted successfully");
		}catch (Exception e) {
			log.error(e);
		}
		return response;
	}

}
