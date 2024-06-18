package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.CompanyMeterialMaster;
import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.entity.ProductMaster;
import com.siddhartha.garments.entity.ProductSizeMaster;
import com.siddhartha.garments.repository.CompanyMeterialMasterRepository;
import com.siddhartha.garments.repository.ProductColorMasterRepository;
import com.siddhartha.garments.repository.ProductRepository;
import com.siddhartha.garments.repository.ProductSizeMasterRepo;
import com.siddhartha.garments.request.CompanyMeterialDropdownReq;
import com.siddhartha.garments.request.CompanyMeterialMasterReq;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.CompanyMeterialMasterService;

@Service
public class CompanyMeterialMasterServiceImpl implements CompanyMeterialMasterService {
	
	Logger log = LogManager.getLogger(CompanyMeterialMasterServiceImpl.class);
	
	private static SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private CompanyMeterialMasterRepository repository;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductSizeMasterRepo productSizeMasterRepo;
	
	@Autowired
	private ProductColorMasterRepository productSizeColorMasterRepo;
	
	@Autowired
	private InputValidationServiceImpl validation;

	@Override
	public CommonResponse saveMeterial(List<CompanyMeterialMasterReq> request) {
		CommonResponse response = new CommonResponse();
		log.info("saveMeterial request size:: "+request.size());
		try {
			List<ErrorList> error = validation.validateCompanyMeterial(request);
			if(error.isEmpty()) {
				List<CompanyMeterialMaster> cmm_list = new ArrayList<>();
				int index =1;
				for(CompanyMeterialMasterReq req :request){					
					CompanyMeterialMaster cmm = CompanyMeterialMaster.builder()
							.companyId(Integer.valueOf(req.getCompanyId()))
							.productId(Integer.valueOf(req.getProductId()))
							.sizeId(Integer.valueOf(req.getSizeId()))
							.colorId(Integer.valueOf(req.getColorId()))
							.meterialId(StringUtils.isBlank(req.getMeterialId())?null:Integer.valueOf(req.getMeterialId()))
							.measurementDisplayName(req.getMeasurementDispalyName())
							.measurementDisplayOrder(Integer.valueOf(req.getMeasurementDisplayOrder()))
							.measurementName(req.getMeasurementName())
							.measurementPieces(Integer.valueOf(req.getMeasurementPieces()))
							.measurementType(req.getMeasurementType())
							.measurementValue(Double.valueOf(req.getMeasurementValue()))
							.status(req.getStatus())
							.entryDate(new Date())
							.dbColumnName(StringUtils.isBlank(req.getDbColumnName())?"PARAM_"+index:req.getDbColumnName())
							.build();
					
					cmm_list.add(cmm);
					
					index++;
				}
				repository.saveAll(cmm_list);
				
				response.setMessage("Success");
				response.setResponse("data saved successfully..!");
				response.setError(null);
			}else {
				
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getAllMeterialByCompanyId(Integer companyId, Integer productId) {
		CommonResponse response = new CommonResponse();
		try {
			List<CompanyMeterialMaster> list = repository.findByCompanyIdAndProductId(companyId,productId);
			if(list.size()>0) {
				List<Map<String,String>> mapList =new ArrayList<>();
				list.forEach(p ->{
					Map<String,String> map = new HashMap<>();
					map.put("CompanyId", p.getCompanyId().toString());
					map.put("ProductId", p.getProductId().toString());
					map.put("SizeId", p.getSizeId().toString());
					map.put("ColorId", p.getColorId().toString());
					map.put("MeterialId", p.getMeterialId().toString());
					map.put("MeasurementName", p.getMeasurementName());
					map.put("MeasurementType", p.getMeasurementType());
					map.put("MeasurementValue", p.getMeasurementValue().toString());
					map.put("MeasurementPieces", p.getMeasurementPieces().toString());
					map.put("MeasurementDisplayName", p.getMeasurementDisplayName());
					map.put("MeasurementDisplayOrder", p.getMeasurementDisplayOrder().toString());
					map.put("Status", p.getStatus());
					map.put("DBColumnName", StringUtils.isBlank(p.getDbColumnName())?"":p.getDbColumnName());
					map.put("EntryDate", DD_MM_YYYY.format(p.getEntryDate()));
					
					mapList.add(map);
					
					response.setMessage("Success");
					response.setResponse(mapList);
					response.setError(null);
				});
			}else{
				response.setMessage("Failed");
				response.setResponse("No Record found");
				response.setError(null);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse productDropdown(CompanyMeterialDropdownReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,String>> list = new ArrayList<>();
			List<ProductMaster> proList =productRepo.findByIdCompanyIdAndStatusIgnoreCase(Integer.valueOf(req.getCompanyId()), "Y");
			if(!proList.isEmpty()) {
				proList.forEach(p ->{
					Map<String,String> map = new HashMap<>();
					map.put("Code", p.getId().getProductId().toString());
					map.put("CodeDesc", p.getProductName());
					list.add(map);
				});
				
				Map<String,String> all = new HashMap<>();
				all.put("Code","99999");
				all.put("CodeDesc", "ALL");
				list.add(all);
				
				response.setMessage("Success");
				response.setResponse(list);
				response.setError(null);
			}else {
				
				response.setMessage("Failed");
				response.setResponse("No Record found");
				response.setError(null);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse sizeDropdown(CompanyMeterialDropdownReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,String>> list = new ArrayList<>();
			List<ProductSizeMaster> proList =productSizeMasterRepo.findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(Integer.valueOf(req.getCompanyId()), Integer.valueOf(req.getProductId()), "Y");
			if(!proList.isEmpty()) {
				proList.forEach(p ->{
					Map<String,String> map = new HashMap<>();
					map.put("Code", p.getId().getSizeId().toString());
					map.put("CodeDesc", p.getSize().toString());
					list.add(map);
				});
				
				Map<String,String> all = new HashMap<>();
				all.put("Code","99999");
				all.put("CodeDesc", "ALL");
				list.add(all);
				
				response.setMessage("Success");
				response.setResponse(list);
				response.setError(null);
			}else {
				
				response.setMessage("Failed");
				response.setResponse("No Record found");
				response.setError(null);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public CommonResponse colorDropdown(CompanyMeterialDropdownReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,String>> list = new ArrayList<>();
			List<ProductColorMaster> proList =productSizeColorMasterRepo.findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(Integer.valueOf(req.getCompanyId()), Integer.valueOf(req.getProductId()), "Y");
			if(!proList.isEmpty()) {
				proList.forEach(p ->{
					Map<String,String> map = new HashMap<>();
					map.put("Code", p.getId().getColourCode().toString());
					map.put("CodeDesc", p.getColourName());
					list.add(map);
				});
				
				Map<String,String> all = new HashMap<>();
				all.put("Code","99999");
				all.put("CodeDesc", "ALL");
				list.add(all);
				
				response.setMessage("Success");
				response.setResponse(list);
				response.setError(null);
			}else {
				
				response.setMessage("Failed");
				response.setResponse("No Record found");
				response.setError(null);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return response;
	}

}
