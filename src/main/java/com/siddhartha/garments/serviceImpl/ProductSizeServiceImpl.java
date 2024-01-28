package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.ProductSizeMaster;
import com.siddhartha.garments.repository.ProductSizeMasterRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.SizeMasterRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ProductSizeService;

@Service
public class ProductSizeServiceImpl implements ProductSizeService{
	
	@Autowired
	private ProductSizeMasterRepository repository; 
	
	@Autowired
	private InputValidationServiceImpl validation;

	@Override
	public CommonResponse saveSize(SizeMasterRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> error =validation.validateMasterInfo(req, "SIZE");
			
			if(error.isEmpty()) {
				Long sizeId =repository.count()+1;
				ProductSizeMaster productSizeMaster =ProductSizeMaster.builder()
						.productSize(Integer.valueOf(req.getSize()))
						.productSizeId(StringUtils.isBlank(req.getSizeId())?sizeId.intValue():Integer.valueOf(req.getSizeId()))
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.entryDate(new Date())
						.build();
				repository.save(productSizeMaster);
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
	public CommonResponse editSize(Integer sizeId) {
		CommonResponse response = new CommonResponse();
		try {
			ProductSizeMaster p = repository.findById(sizeId).orElse(null);
			if(p !=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("SizeId", p.getProductSizeId().toString());
				map.put("Size",p.getProductSize().toString());
				map.put("Status", p.getStatus());
				map.put("CreatedDate",OperatorMasterServiceImpl.sdf.format(p.getEntryDate()));
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
	public CommonResponse deleteSize(Integer sizeId) {
		CommonResponse response = new CommonResponse();
		try {
			repository.deleteById(sizeId);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("Data seleted Successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public CommonResponse getAllSize() {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductSizeMaster> list =repository.findAll();
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("SizeId", p.getProductSizeId().toString());
					map.put("Size",p.getProductSize().toString());
					map.put("Status", p.getStatus());
					map.put("CreatedDate",OperatorMasterServiceImpl.sdf.format(p.getEntryDate()));
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

}
