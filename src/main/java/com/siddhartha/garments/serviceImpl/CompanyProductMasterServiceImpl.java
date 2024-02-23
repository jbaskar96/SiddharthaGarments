package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.CompanyMaster;
import com.siddhartha.garments.entity.CompanyProductMaster;
import com.siddhartha.garments.entity.CompanyProductMasterId;
import com.siddhartha.garments.entity.ProductStyleMaster;
import com.siddhartha.garments.entity.ProductStyleMasterId;
import com.siddhartha.garments.repository.CompanyProductMasterRepository;
import com.siddhartha.garments.repository.CompanyProductRepository;
import com.siddhartha.garments.repository.ProductStyleMasterRepository;
import com.siddhartha.garments.request.CompanyMasterRequest;
import com.siddhartha.garments.request.CompanyProductRequest;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.ProductStyleMasterRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.CompanyProductMasterService;

@Service
public class CompanyProductMasterServiceImpl implements CompanyProductMasterService {
	
	
	@Autowired
	private CompanyProductMasterRepository companyRepo;
	
	@Autowired
	private CompanyProductRepository productRepo;
	
	@Autowired
	private ProductStyleMasterRepository styleMasterRepo;
	
	@Autowired
	private InputValidationServiceImpl validation;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	private static Integer companyId =1000;

	@Override
	public CommonResponse companySave(CompanyMasterRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> list =validation.company(req);
			if(list.isEmpty()) {
				CompanyMaster companyMaster =CompanyMaster.builder()
						.address(req.getAddress())
						.companyId(StringUtils.isBlank(req.getCompanyId())?companyId+(int)companyRepo.count()+1:
							Integer.valueOf(req.getCompanyId()))
						.companyName(req.getCompanyName())
						.createdBy(req.getCreatedBy())
						.district(Integer.valueOf(req.getState()))
						.entryDate(new Date())
						.gstNumber(req.getGstNumber())
						.mobileNo(req.getMobileNo())
						.remarks(StringUtils.isBlank(req.getRemarks())?"":req.getRemarks())
						.state(Integer.valueOf(req.getState()))
						.status(req.getStatus())
						.build();
				
				companyRepo.save(companyMaster);
				
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse productSave(CompanyProductRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> list = validation.product(req);
			if(list.isEmpty()) {
					CompanyProductMasterId id = CompanyProductMasterId.builder()
							.companyId(Integer.valueOf(req.getCompanyId()))
							.productId(StringUtils.isBlank(req.getProductId())?getProductIdByCompanyId(req.getCompanyId())
									:Integer.valueOf(req.getProductId()))
							.build();
					
					CompanyProductMaster productMaster = CompanyProductMaster.builder()
							.createdBy(req.getCreatedBy())
							.entryDate(new Date())
							.id(id)
							.productName(req.getProductName())
							.remarks(StringUtils.isBlank(req.getRemarks())?"":req.getRemarks())
							.status(req.getStatus())
							.foldingYn(req.getFoldingYn())
							.foldingMesurementType(StringUtils.isBlank(req.getFoldingMesurementType())?null:req.getFoldingMesurementType())
							.foldingMesurementValue(StringUtils.isBlank(req.getFoldingMesurementValue())?0:Integer.valueOf(req.getFoldingMesurementValue()))
							.elasticYn(req.getElasticYn())
							.elasticMesurementType(StringUtils.isBlank(req.getElasticMesurementType())?null:req.getElasticMesurementType())
							.elastciMesurementValue(StringUtils.isBlank(req.getElasticMesurementValue())?0:Integer.valueOf(req.getElasticMesurementValue()))
							.build();
					
				productRepo.save(productMaster);
					
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	
	private Integer getProductIdByCompanyId(String companyId) {
		List<CompanyProductMaster> list =productRepo.findByIdCompanyId(Integer.valueOf(companyId));
		return list.size()+1;
	}

	@Override
	public CommonResponse styleSave(ProductStyleMasterRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> list = validation.style(req);
			if(list.isEmpty()) {
			
				ProductStyleMasterId styleMasterId = ProductStyleMasterId.builder()
							.companyId(Integer.valueOf(req.getCompanyId()))
							.productId(Integer.valueOf(req.getProductId()))
							.styleId(StringUtils.isBlank(req.getStyleId())?getStyleId(req.getCompanyId(),req.getProductId())
									:Integer.valueOf(req.getStyleId()))
							.build();
					
					ProductStyleMaster styleMaster = ProductStyleMaster.builder()
							.createdBy(req.getCreatedBy())
							.entryDate(new Date())
							.id(styleMasterId)
							.styleName(req.getStyleName())
							.remarks(StringUtils.isBlank(req.getRemarks())?"":req.getRemarks())
							.status(req.getStatus())
							.build();
					
				
				styleMasterRepo.save(styleMaster);
				
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Integer getStyleId(String companyId, String productId) {
		List<ProductStyleMaster> list =styleMasterRepo.findByIdCompanyIdAndIdProductId(Integer.valueOf(companyId),Integer.valueOf(productId));
		return list.size()+1;
	}

	@Override
	public CommonResponse getAllCompany() {
		CommonResponse response = new CommonResponse();
		try {
			List<CompanyMaster> list =companyRepo.findAll(Sort.by("entryDate").descending());
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String,String> map = new HashMap<>();
					map.put("CompanyId", p.getCompanyId().toString());
					map.put("CompanyName", p.getCompanyName());
					map.put("State",p.getState().toString());
					map.put("District", p.getDistrict().toString());
					map.put("Address", p.getAddress());
					map.put("GstNumber", p.getGstNumber());
					map.put("Status", p.getStatus());
					map.put("CreatedBy", p.getCreatedBy());
					map.put("MobileNo", p.getMobileNo());
					map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					mapList.add(map);
					
				});
				
				response.setMessage("Success");
				response.setResponse(mapList);
				response.setError(null);
			}else {
				
				response.setMessage("Failed");
				response.setResponse("No Record Found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse editCompany(Integer companyId) {
		CommonResponse response = new CommonResponse();
		try {
			Optional<CompanyMaster> data =companyRepo.findById(companyId);
			if(data.isPresent()) {
				CompanyMaster p = data.get();
				HashMap<String,String> map = new HashMap<>();
				map.put("CompanyId", p.getCompanyId().toString());
				map.put("CompanyName", p.getCompanyName());
				map.put("State",p.getState().toString());
				map.put("District", p.getDistrict().toString());
				map.put("Address", p.getAddress());
				map.put("GstNumber", p.getGstNumber());
				map.put("Status", p.getStatus());
				map.put("CreatedBy", p.getCreatedBy());
				map.put("MobileNo", p.getMobileNo());
				map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
				map.put("CreatedDate", sdf.format(p.getEntryDate()));
				
				response.setMessage("Success");
				response.setResponse(map);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Record Found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getAllProductByCompanyId(Integer companyId) {
		CommonResponse response = new CommonResponse();
		try {
			List<CompanyProductMaster> product =productRepo.findByIdCompanyId(companyId);
			if(!product.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				product.forEach(p ->{
					HashMap<String,String> map = new HashMap<>();
					map.put("CompanyId", p.getId().getCompanyId().toString());
					map.put("ProductId", p.getId().getProductId().toString());
					map.put("ProductName",p.getProductName());
					map.put("CreatedBy", p.getCreatedBy());
					map.put("Status", p.getStatus());
					map.put("CreatedBy", p.getCreatedBy());
					map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					map.put("FoldingYn", p.getFoldingYn());
					map.put("ElasticYn", p.getElasticYn());
					map.put("FoldingMesurementType",StringUtils.isBlank(p.getFoldingMesurementType())?"":p.getFoldingMesurementType());
					map.put("FoldingMesurementValue",p.getFoldingMesurementValue().toString());
					map.put("ElasticMesurementType",StringUtils.isBlank(p.getElasticMesurementType())?"":p.getElasticMesurementType());
					map.put("ElasticMesurementValue",p.getElastciMesurementValue().toString());
					mapList.add(map);
				});
				
				response.setMessage("Success");
				response.setResponse(mapList);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Record Found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse editProductByCompanyIdAndProductId(Integer companyId, Integer productId) {
		CommonResponse response = new CommonResponse();
		try {
			CompanyProductMasterId productMasterId =CompanyProductMasterId.builder()
					.companyId(companyId)
					.productId(productId)
					.build();
			
			Optional<CompanyProductMaster> data =productRepo.findById(productMasterId);
			
			if(data.isPresent()) {
				
				CompanyProductMaster p =data.get();
				HashMap<String,String> map = new HashMap<>();
				map.put("CompanyId", p.getId().getCompanyId().toString());
				map.put("ProductId", p.getId().getProductId().toString());
				map.put("ProductName",p.getProductName());
				map.put("CreatedBy", p.getCreatedBy());
				map.put("Status", p.getStatus());
				map.put("CreatedBy", p.getCreatedBy());
				map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
				map.put("CreatedDate", sdf.format(p.getEntryDate()));
				map.put("FoldingYn", p.getFoldingYn());
				map.put("ElasticYn", p.getElasticYn());
				map.put("FoldingMesurementType",StringUtils.isBlank(p.getFoldingMesurementType())?"":p.getFoldingMesurementType());
				map.put("FoldingMesurementValue",p.getFoldingMesurementValue().toString());
				map.put("ElasticMesurementType",StringUtils.isBlank(p.getElasticMesurementType())?"":p.getElasticMesurementType());
				map.put("ElasticMesurementValue",p.getElastciMesurementValue().toString());

				response.setMessage("Success");
				response.setResponse(map);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Record found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getAllStyle(Integer companyId, Integer productId) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductStyleMaster> product =styleMasterRepo.findByIdCompanyIdAndIdProductId(companyId,productId);
			if(!product.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				product.forEach(p ->{
					HashMap<String,String> map = new HashMap<>();
					map.put("CompanyId", p.getId().getCompanyId().toString());
					map.put("ProductId", p.getId().getProductId().toString());
					map.put("StyleId",p.getId().getStyleId().toString());
					map.put("StyleName",p.getStyleName());
					map.put("CreatedBy", p.getCreatedBy());
					map.put("Status", p.getStatus());
					map.put("CreatedBy", p.getCreatedBy());
					map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					mapList.add(map);
				});
				
				response.setMessage("Success");
				response.setResponse(mapList);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Record Found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getAllStyle(Integer companyId, Integer productId, Integer styleId) {
		CommonResponse response = new CommonResponse();
		try {
			ProductStyleMasterId styleMasterId =ProductStyleMasterId.builder()
					.companyId(companyId)
					.productId(productId)
					.styleId(styleId)
					.build();
			
			Optional<ProductStyleMaster> data =styleMasterRepo.findById(styleMasterId);
			
			if(data.isPresent()) {
				
				ProductStyleMaster p =data.get();
				HashMap<String,String> map = new HashMap<>();
				map.put("CompanyId", p.getId().getCompanyId().toString());
				map.put("ProductId", p.getId().getProductId().toString());
				map.put("StyleId",p.getId().getStyleId().toString());
				map.put("StyleName",p.getStyleName());
				map.put("CreatedBy", p.getCreatedBy());
				map.put("Status", p.getStatus());
				map.put("CreatedBy", p.getCreatedBy());
				map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
				map.put("CreatedDate", sdf.format(p.getEntryDate()));
				
				response.setMessage("Success");
				response.setResponse(map);
				response.setError(null);
			}else {
				response.setMessage("Failed");
				response.setResponse("No Record found");
				response.setError(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
