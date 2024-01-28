package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.DistrictMaster;
import com.siddhartha.garments.entity.OperatorMaster;
import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.entity.ProductMaster;
import com.siddhartha.garments.entity.ProductSizeMaster;
import com.siddhartha.garments.entity.SectionMaster;
import com.siddhartha.garments.entity.StateMaster;
import com.siddhartha.garments.entity.UserTypeMaster;
import com.siddhartha.garments.repository.DistrictMasterRepository;
import com.siddhartha.garments.repository.OperatorMasterRepository;
import com.siddhartha.garments.repository.ProductColorMasterRepository;
import com.siddhartha.garments.repository.ProductMasterRepository;
import com.siddhartha.garments.repository.ProductSizeMasterRepository;
import com.siddhartha.garments.repository.SectionMasterRepository;
import com.siddhartha.garments.repository.StateMasterRepository;
import com.siddhartha.garments.repository.UserTypeMasterRepository;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.DropDownService;

@Service
public class DropDownServiceImpl implements DropDownService{
	
	@Autowired
	private SectionMasterRepository sectionRepo;

	@Autowired
	private ProductMasterRepository productRepo;

	@Autowired
	private UserTypeMasterRepository userTypeRepo;

	@Autowired
	private ProductColorMasterRepository colorRepo;;

	@Autowired
	private StateMasterRepository stateRepo;

	@Autowired
	private OperatorMasterRepository operatorRepo;

	@Autowired
	private ProductSizeMasterRepository sizeRepo;
	
	@Autowired
	private DistrictMasterRepository districtRepo;


	@Override
	public CommonResponse section() {
		CommonResponse response = new CommonResponse();
		try {
			List<SectionMaster> list =sectionRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getSectionId().toString());
					map.put("CodeDesc", p.getSectionName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse product() {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductMaster> list =productRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getProductId().toString());
					map.put("CodeDesc", p.getProductName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse usertype() {
		CommonResponse response = new CommonResponse();
		try {
			List<UserTypeMaster> list =userTypeRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getUserTypeId().toString());
					map.put("CodeDesc", p.getUserType());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse color() {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductColorMaster> list =colorRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getColorId().toString());
					map.put("CodeDesc", p.getColorName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse state() {
		CommonResponse response = new CommonResponse();
		try {
			List<StateMaster> list =stateRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getStateCode());
					map.put("CodeDesc", p.getStateName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse district(String stateCode) {
		CommonResponse response = new CommonResponse();
		try {
			List<DistrictMaster> list =districtRepo.findByDistIdStateCodeAndStatusIgnoreCase(Integer.valueOf(1),"Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getDistId().getDistrictCode().toString());
					map.put("CodeDesc", p.getDistrictName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public CommonResponse operator() {
		CommonResponse response = new CommonResponse();
		try {
			List<OperatorMaster> list =operatorRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					String desc =p.getFirstName().concat(StringUtils.isBlank(p.getLastName())?"":p.getLastName());
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getOperatorId());
					map.put("CodeDesc",desc);
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse size() {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductSizeMaster> list =sizeRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getProductSizeId().toString());
					map.put("CodeDesc", p.getProductSize().toString());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
