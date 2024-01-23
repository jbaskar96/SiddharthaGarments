package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.repository.ProductColorMasterRepository;
import com.siddhartha.garments.request.ColorSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ProductColorService;

@Service
public class ProductColorServiceImpl implements ProductColorService {
	
	
	@Autowired
	private ProductColorMasterRepository repository;
	
	

	@Override
	public CommonResponse saveColor(ColorSaveRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			Long colorId =repository.count()+1;
			ProductColorMaster colorMaster =ProductColorMaster.builder()
					.colorId(StringUtils.isBlank(req.getColorId())?colorId.intValue():Integer.valueOf(req.getColorId()))
					.colorName(req.getColorName())
					.entryDate(new Date())
					.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
					.build();
			
			repository.save(colorMaster);
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
	public CommonResponse getAllColor() {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductColorMaster> list =repository.findAll();
			if(list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("ColorId", p.getColorId().toString());
					map.put("ColorName",p.getColorName());
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


	@Override
	public CommonResponse editColor(Integer colorId) {
		CommonResponse response = new CommonResponse();
		try {
			ProductColorMaster p =repository.findById(colorId).orElse(null);
			if(p !=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("ColorId", p.getColorId().toString());
				map.put("ColorName",p.getColorName());
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
	public CommonResponse deleteColor(Integer colorId) {
		CommonResponse response = new CommonResponse();
		try {
			repository.deleteById(colorId);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("Data seleted Successfully");
		}catch (Exception e) {
			e.printStackTrace();
			response.setError(null);
			response.setMessage("Failed");
			response.setResponse("Data deleted Failed");
		}
		return response;
	}


}
