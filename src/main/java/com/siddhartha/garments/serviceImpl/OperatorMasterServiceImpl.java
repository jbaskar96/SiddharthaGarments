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

import com.siddhartha.garments.entity.OperatorMaster;
import com.siddhartha.garments.repository.OperatorMasterRepository;
import com.siddhartha.garments.request.OperatorSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.OperatorMasterService;

@Service
public class OperatorMasterServiceImpl implements OperatorMasterService {
	
	@Autowired
	private OperatorMasterRepository repository;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public CommonResponse saveOperator(OperatorSaveRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			Long optId =repository.count()+1;
			OperatorMaster operatorMaster =OperatorMaster.builder()
					.aadharNo(req.getAadharNo())
					.createdBy(req.getCreatedBy())
					.dateOfBirth(sdf.parse(req.getDateOfBirth()))
					.email(req.getEmail())
					.entryDate(new Date())
					.mobileNo(req.getMobileNo())
					.firstName(req.getFirstname())
					.lastName(req.getLastName())
					.stateCode(Integer.valueOf(req.getStateCode()))
					.districtCode(Integer.valueOf(req.getDistrictCode()))
					.city(req.getCity())
					.address(req.getAdress())
					.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
					.operatorId(StringUtils.isBlank(req.getOperatorId())?"OPT_"+optId:req.getOperatorId())
					.build();
			
			repository.save(operatorMaster);
			
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("Data saved Successfully");
		}catch (Exception e) {
			e.printStackTrace();
			response.setError(e.getMessage());
			response.setMessage("Failed");
			response.setResponse("Data saved Failed");
		}
		return response;
	}

	@Override
	public CommonResponse getAllOperator() {
		CommonResponse response = new CommonResponse();
		try {
			List<OperatorMaster> list =repository.findByStatusIgnoreCase("Y");
			if(list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("AadharNo", p.getAadharNo());
					map.put("MobileNo",p.getMobileNo());
					map.put("OperatorId", p.getOperatorId());
					map.put("FirstName",p.getFirstName());
					map.put("LastName", p.getLastName());
					map.put("Email", StringUtils.isBlank(p.getEmail())?"":p.getEmail());
					map.put("DateOfBirth", sdf.format(p.getDateOfBirth()));
					map.put("CreatedBy", p.getCreatedBy());
					map.put("Status",p.getStatus());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					map.put("StateCode", p.getStateCode().toString());
					map.put("DistrictCode", p.getDistrictCode().toString());
					map.put("City", p.getCity());
					map.put("Address", p.getAddress());
				
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
	public CommonResponse editOperator(String operatorId) {
		CommonResponse response = new CommonResponse();
		try {
			OperatorMaster p =repository.findById(operatorId).orElse(null);
			if(p!=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("AadharNo", p.getAadharNo());
				map.put("MobileNo",p.getMobileNo());
				map.put("OperatorId", p.getOperatorId());
				map.put("FirstName",p.getFirstName());
				map.put("LastName", p.getLastName());
				map.put("Email", StringUtils.isBlank(p.getEmail())?"":p.getEmail());
				map.put("DateOfBirth", sdf.format(p.getDateOfBirth()));
				map.put("CreatedBy", p.getCreatedBy());
				map.put("Status",p.getStatus());
				map.put("CreatedDate", sdf.format(p.getEntryDate()));
				map.put("StateCode", p.getStateCode().toString());
				map.put("DistrictCode", p.getDistrictCode().toString());
				map.put("City", p.getCity());
				map.put("Address", p.getAddress());
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(p);
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
	public CommonResponse deleteOperator(String operatorId) {
		CommonResponse response = new CommonResponse();
		try {
			repository.deleteById(operatorId);

			response.setError(null);
			response.setMessage("Success");
			response.setResponse("Record Deleted Sucesfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
