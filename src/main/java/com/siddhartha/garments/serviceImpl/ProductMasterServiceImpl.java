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

import com.siddhartha.garments.entity.ProductMaster;
import com.siddhartha.garments.repository.ProductMasterRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.ProductSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ProductMasterService;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {
	
	
	@Autowired
	private ProductMasterRepository repository;
	
	
	@Autowired
	private SimpleDateFormat sdf;
	

	@Autowired
	private InputValidationServiceImpl validation;

	@Override
	public CommonResponse saveProduct(ProductSaveRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> error =validation.validateMasterInfo(req, "PRODUCT");
			
			if(error.isEmpty()) {
				Long productId =repository.count()+1;
				ProductMaster productMaster =ProductMaster.builder()
						.productId(StringUtils.isBlank(req.getProductId())?productId.intValue():Integer.valueOf(req.getProductId()))
						.productName(req.getProductName())
						.status(req.getStatus())
						.entryDate(new Date())
						.build();
				
				repository.save(productMaster);
				
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
	public CommonResponse getAllProduct() {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductMaster> list=repository.findAll();
			if(list.isEmpty()) {
				List<Map<String, String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("ProductId", p.getProductId().toString());
					map.put("ProductName", p.getProductName());
					map.put("Status", p.getStatus());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
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
	public CommonResponse editProduct(Integer productId) {
		CommonResponse response = new CommonResponse();
		try {
			ProductMaster p =repository.findById(productId).orElse(null);
			if(p!=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("ProductId", p.getProductId().toString());
				map.put("ProductName", p.getProductName());
				map.put("Status", p.getStatus());
				map.put("CreatedDate", sdf.format(p.getEntryDate()));
			
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
	public CommonResponse deleteProduct(Integer productId) {
		CommonResponse response = new CommonResponse();
		try {
			repository.deleteById(productId);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("data deleted successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
