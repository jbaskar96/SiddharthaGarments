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

import com.siddhartha.garments.entity.UserTypeMaster;
import com.siddhartha.garments.repository.UserTypeMasterRepository;
import com.siddhartha.garments.request.UserTypeRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService {
	
	@Autowired
	private UserTypeMasterRepository repository;
	
	@Autowired
	private SimpleDateFormat sdf;

	@Override
	public CommonResponse saveUserType(UserTypeRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			Long userTypeId =repository.count()+1;
			UserTypeMaster userTypeMaster =UserTypeMaster.builder()
					.userTypeId(StringUtils.isBlank(req.getUserTypeId())?userTypeId.intValue():Integer.valueOf(req.getUserTypeId()))
					.userType(req.getUserTypeName())
					.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
					.entryDate(new Date())
					.build();
			
			repository.save(userTypeMaster);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("Data saved Successfully");
		}catch (Exception e) {
			e.printStackTrace();
			response.setError(null);
			response.setMessage("Failed");
			response.setResponse("Data saved Failed");
		}
		return response;
	}

	@Override
	public CommonResponse getAllUserType() {
		CommonResponse response = new CommonResponse();
		try {
			List<UserTypeMaster> list =repository.findAll();
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("UserTypeId", p.getUserTypeId().toString());
					map.put("UserName",p.getUserType());
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
	public CommonResponse editUserType(Integer userTypeId) {
		CommonResponse response = new CommonResponse();
		try {
			UserTypeMaster p = repository.findById(userTypeId).orElse(null);
			if(p !=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("UserTypeId", p.getUserTypeId().toString());
				map.put("UserName",p.getUserType());
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
	public CommonResponse deleteUserType(Integer userTypeId) {
		CommonResponse response = new CommonResponse();
		try {
			repository.deleteById(userTypeId);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("Data deleted Successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
