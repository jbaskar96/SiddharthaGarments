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
import com.siddhartha.garments.entity.SectionMaster;
import com.siddhartha.garments.repository.SectionMasterRepository;
import com.siddhartha.garments.request.SectionSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.SectionMasterService;

@Service
public class SectionMasterServiceImpl implements SectionMasterService {
	
	@Autowired
	private SectionMasterRepository repository;
	
	@Autowired
	private SimpleDateFormat sdf;

	@Override
	public CommonResponse saveSection(SectionSaveRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			Long sectionId =repository.count()+1;
			SectionMaster sectionMaster =SectionMaster.builder()
					.sectionId(StringUtils.isBlank(req.getSectionId())?sectionId.intValue():Integer.valueOf(req.getSectionId()))
					.sectionName(req.getSectionName())
					.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
					.entryDate(new Date())
					.build();
			repository.save(sectionMaster);
			
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
	public CommonResponse getAllSection() {
		CommonResponse response = new CommonResponse();
		try {
			List<SectionMaster> list =repository.findAll();
			if(list.isEmpty()) {
				List<Map<String, String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("SectionId", p.getSectionId().toString());
					map.put("SectionName", p.getSectionName());
					map.put("Status", p.getStatus());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
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
	public CommonResponse editSection(Integer sectionId) {
		CommonResponse response = new CommonResponse();
		try {
			SectionMaster p =repository.findById(sectionId).orElse(null);
			if(p!=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("SectionId", p.getSectionId().toString());
				map.put("SectionName", p.getSectionName());
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
	public CommonResponse deleteSection(Integer sectionId) {
		CommonResponse response = new CommonResponse();
		try {
			repository.deleteById(sectionId);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("data deleted successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
