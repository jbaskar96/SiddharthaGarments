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

import com.siddhartha.garments.dto.GetProductColorMetalReq;
import com.siddhartha.garments.dto.GetProductSizeColorReq;
import com.siddhartha.garments.dto.GetProductSizeMetalReq;
import com.siddhartha.garments.dto.ProductSizeColorRequest;
import com.siddhartha.garments.dto.ProductSizeMasterReq;
import com.siddhartha.garments.dto.ProductSizeMetalReq;
import com.siddhartha.garments.dto.SaveProductSizeColorMetalReq;
import com.siddhartha.garments.entity.CompanyMaster;
import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.entity.ProductColorMasterId;
import com.siddhartha.garments.entity.ProductColorMetalMaster;
import com.siddhartha.garments.entity.ProductColorMetalMasterId;
import com.siddhartha.garments.entity.ProductMaster;
import com.siddhartha.garments.entity.ProductMasterId;
import com.siddhartha.garments.entity.ProductMetalMaster;
import com.siddhartha.garments.entity.ProductMetalMasterId;
import com.siddhartha.garments.entity.ProductSizeMaster;
import com.siddhartha.garments.entity.ProductSizeMasterId;
import com.siddhartha.garments.entity.ProductSizeMetalMaster;
import com.siddhartha.garments.entity.ProductSizeMetalMasterId;
import com.siddhartha.garments.entity.ProductStyleMaster;
import com.siddhartha.garments.entity.ProductStyleMasterId;
import com.siddhartha.garments.repository.CompanyMasterRepository;
import com.siddhartha.garments.repository.ProductColorMasterRepository;
import com.siddhartha.garments.repository.ProductColorMetalMasterRepository;
import com.siddhartha.garments.repository.ProductMetalRepository;
import com.siddhartha.garments.repository.ProductRepository;
import com.siddhartha.garments.repository.ProductSizeMasterRepo;
import com.siddhartha.garments.repository.ProductSizeMetalMasterRepository;
import com.siddhartha.garments.repository.ProductStyleMasterRepository;
import com.siddhartha.garments.request.CompanyMasterRequest;
import com.siddhartha.garments.request.CompanyProductRequest;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.ProductStyleMasterRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.GetProductMetalReq;
import com.siddhartha.garments.response.ProductMetalReq;
import com.siddhartha.garments.service.CompanyProductMasterService;

@Service
public class CompanyProductMasterServiceImpl implements CompanyProductMasterService {
	
	
	@Autowired
	private CompanyMasterRepository companyRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductStyleMasterRepository styleMasterRepo;
	
	@Autowired
	private InputValidationServiceImpl validation;
	
	@Autowired
	private ProductSizeMasterRepo productSizeMasterRepo;
	
	@Autowired
	private ProductSizeMetalMasterRepository ProductSizeMetalMasterRepo;
	
	@Autowired
	private ProductColorMasterRepository productSizeColorMasterRepo;
	
	@Autowired
	private ProductColorMetalMasterRepository productSizeColorMetalMasterRepo;
	
	@Autowired
	private ProductMetalRepository productMetalRepo;
	
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
					ProductMasterId id = ProductMasterId.builder()
							.companyId(Integer.valueOf(req.getCompanyId()))
							.productId(StringUtils.isBlank(req.getProductId())?getProductIdByCompanyId(req.getCompanyId())
									:Integer.valueOf(req.getProductId()))
							.build();
					
					ProductMaster productMaster = ProductMaster.builder()
							.createdBy(req.getCreatedBy())
							.entryDate(new Date())
							.id(id)
							.productName(req.getProductName())
							.remarks(StringUtils.isBlank(req.getRemarks())?"":req.getRemarks())
							.status(req.getStatus())
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
		List<ProductMaster> list =productRepo.findByIdCompanyId(Integer.valueOf(companyId));
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
			List<ProductMaster> product =productRepo.findByIdCompanyId(companyId);
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
			ProductMasterId productMasterId =ProductMasterId.builder()
					.companyId(companyId)
					.productId(productId)
					.build();
			
			Optional<ProductMaster> data =productRepo.findById(productMasterId);
			
			if(data.isPresent()) {
				
				ProductMaster p =data.get();
				HashMap<String,String> map = new HashMap<>();
				map.put("CompanyId", p.getId().getCompanyId().toString());
				map.put("ProductId", p.getId().getProductId().toString());
				map.put("ProductName",p.getProductName());
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

	@Override
	public CommonResponse saveSize(List<ProductSizeMasterReq> req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.size(req);
			if(error.isEmpty()) {
				for(ProductSizeMasterReq r :req) {
					
					ProductSizeMasterId id = ProductSizeMasterId.builder()
							.companyId(Integer.valueOf(r.getCompanyId()))
							.productId(Integer.valueOf(r.getProductId()))
							.sizeId(StringUtils.isBlank(r.getSizeId())?getSizedId(Integer.valueOf(r.getCompanyId()),Integer.valueOf(r.getProductId()))
									:Integer.valueOf(r.getSizeId()))
							.build();
					
					ProductSizeMaster master =ProductSizeMaster.builder()
							.entryDate(new Date())
							.id(id)
							.remarks(StringUtils.isBlank(r.getRemarks())?null:r.getRemarks())
							.size(Integer.valueOf(r.getSize()))
							.status(r.getStatus())
							.build();
					
					productSizeMasterRepo.save(master);
				}
				
				
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Integer getSizedId(Integer companyId,Integer productId) {
		Integer sizeId =productSizeMasterRepo.findByIdCompanyIdAndIdProductId(companyId, productId).size()+1;
		return sizeId;
	}

	@Override
	public CommonResponse getSizeDetailsByCompanyIdAndPrductId(Integer companyId, Integer productId) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductSizeMaster> list =productSizeMasterRepo.findByIdCompanyIdAndIdProductId(companyId,productId);
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					Map<String,String> map = new HashMap<String, String>();
					map.put("CompanyId", p.getId().getCompanyId().toString());
					map.put("ProductId", p.getId().getProductId().toString());
					map.put("SizeId", p.getId().getSizeId().toString());
					map.put("Size", p.getSize().toString());
					map.put("Status", p.getStatus());
					map.put("Remarks", StringUtils.isBlank(p.getRemarks())?"":p.getRemarks());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					mapList.add(map);
				});
				response.setMessage("Success");
				response.setResponse(mapList);
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
	public CommonResponse getSizeDetailsByCompanyIdAndPrductId(Integer companyId, Integer productId, Integer sizeId) {
		CommonResponse response = new CommonResponse();
		try {
			ProductSizeMasterId id = ProductSizeMasterId.builder()
					.companyId(companyId)
					.productId(productId)
					.sizeId(sizeId)
					.build();
			
			Optional<ProductSizeMaster>  master = productSizeMasterRepo.findById(id);
			if(master.isPresent()) {
				
				ProductSizeMaster p =master.get();
				HashMap<String,String> map = new HashMap<>();
				map.put("CompanyId", p.getId().getCompanyId().toString());
				map.put("ProductId", p.getId().getProductId().toString());
				map.put("SizeId", p.getId().getSizeId().toString());
				map.put("Size", p.getSize().toString());
				map.put("Status", p.getStatus());
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

	@Override
	public CommonResponse saveSizeMetal(List<ProductSizeMetalReq> req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.sizeMetal(req);
			if(error.isEmpty()) {
				
				int sno =1;
				
				for(ProductSizeMetalReq r :req) {
					
					ProductSizeMetalMasterId id = ProductSizeMetalMasterId.builder()
							.companyId(Integer.valueOf(r.getCompanyId()))
							.productId(Integer.valueOf(r.getProductId()))
							.sizeId(Integer.valueOf(r.getSizeId()))
							.metalId(StringUtils.isBlank(r.getMetalId())?getSizeMetalId(Integer.valueOf(r.getCompanyId()),Integer.valueOf(r.getProductId()),Integer.valueOf(r.getSizeId())) 
									:Integer.valueOf(r.getMetalId()))		
							.build();
					
					ProductSizeMetalMaster master =ProductSizeMetalMaster.builder()
							.entryDate(new Date())
							.id(id)
							.displayOrder(Integer.valueOf(r.getDisplayOrder()))
							.mesurementPieces(Integer.valueOf(r.getMesurementPieces()))
							.mesurementType(r.getMesurementType())
							.mesurementValue(Double.valueOf(r.getMesurementValue()))
							.metalName(r.getMetalName())
							.mesurementName(r.getMesurementName())
							.columnName(StringUtils.isBlank(r.getColumnName())?"PARAM_"+sno:r.getColumnName())
							.status(r.getStatus())
							.build();
					
					ProductSizeMetalMasterRepo.save(master);
					
					sno++;
				}
				
				
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Integer getSizeMetalId(Integer companyId, Integer productId, Integer sizeId) {
		Integer metalid =ProductSizeMetalMasterRepo.findByIdCompanyIdAndIdProductIdAndIdSizeId(companyId,productId,sizeId).size()+1;
		return metalid;
	}

	@Override
	public CommonResponse getSizeMetalDetails(GetProductSizeMetalReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductSizeMetalMaster> list =ProductSizeMetalMasterRepo.findByIdCompanyIdAndIdProductIdAndIdSizeId(Integer.valueOf(req.getCompanyId()),Integer.valueOf(req.getProductId()),Integer.valueOf(req.getSizeId()));
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					Map<String,String> map = new HashMap<String, String>();
					map.put("CompanyId", p.getId().getCompanyId().toString());
					map.put("ProductId", p.getId().getProductId().toString());
					map.put("SizeId", p.getId().getSizeId().toString());
					map.put("MetalId", p.getId().getMetalId().toString());
					map.put("MetalName", p.getMetalName());
					map.put("ColumnName", p.getColumnName());
					map.put("MesurementType", p.getMesurementType());
					map.put("MesurementValue", p.getMesurementValue().toString());
					map.put("MesurementPieces", p.getMesurementPieces().toString());
					map.put("MesurementName", p.getMesurementName());
					map.put("Status", p.getStatus());
					map.put("DisplayOrder", p.getDisplayOrder().toString());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					mapList.add(map);
				});
				response.setMessage("Success");
				response.setResponse(mapList);
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
	public CommonResponse editSizeMetalDetails(GetProductSizeMetalReq r) {
		CommonResponse response = new CommonResponse();
		try {

			ProductSizeMetalMasterId id = ProductSizeMetalMasterId.builder()
					.companyId(Integer.valueOf(r.getCompanyId()))
					.productId(Integer.valueOf(r.getProductId()))
					.sizeId(Integer.valueOf(r.getSizeId()))
					.metalId(Integer.valueOf(r.getMetalId()))			
					.build();
			
			Optional<ProductSizeMetalMaster> data =ProductSizeMetalMasterRepo.findById(id);
			
			if(data.isPresent()) {
				
				ProductSizeMetalMaster p =data.get();
				
				Map<String,String> map = new HashMap<String, String>();
				map.put("CompanyId", p.getId().getCompanyId().toString());
				map.put("ProductId", p.getId().getProductId().toString());
				map.put("SizeId", p.getId().getSizeId().toString());
				map.put("MetalId", p.getId().getMetalId().toString());
				map.put("MetalName", p.getMetalName());
				map.put("ColumnName", p.getColumnName());
				map.put("MesurementType", p.getMesurementType());
				map.put("MesurementValue", p.getMesurementValue().toString());
				map.put("MesurementPieces", p.getMesurementPieces().toString());
				map.put("MesurementName", p.getMesurementName());
				map.put("Status", p.getStatus());
				map.put("DisplayOrder", p.getDisplayOrder().toString());
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

	@Override
	public CommonResponse saveSizeColor(List<ProductSizeColorRequest> req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.color(req);
			if(error.isEmpty()) {
			
				for(ProductSizeColorRequest r :req) {
					
					ProductColorMasterId id = ProductColorMasterId.builder()
							.companyId(Integer.valueOf(r.getCompanyId()))
							.productId(Integer.valueOf(r.getProductId()))
							.colourCode(StringUtils.isBlank(r.getColorCode())?getColorCode(Integer.valueOf(r.getCompanyId()),Integer.valueOf(r.getProductId()))
									:Integer.valueOf(r.getColorCode()))	
							.build();
					
					ProductColorMaster master =ProductColorMaster.builder()
							.entryDate(new Date())
							.id(id)
							.colourName(r.getColorName())
							.status(r.getStatus())
							.build();
					
					productSizeColorMasterRepo.save(master);
					
				}
				
				
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Integer getColorCode(Integer companyId, Integer productId) {
		Integer colorId =productSizeColorMasterRepo.findByIdCompanyIdAndIdProductId(companyId, productId).size()+1;
		return colorId;
	}

	@Override
	public CommonResponse getSizeColorDetails(GetProductSizeColorReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductColorMaster> list =productSizeColorMasterRepo.findByIdCompanyIdAndIdProductId(Integer.valueOf(req.getCompanyId()),Integer.valueOf(req.getProductId()));
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					Map<String,String> map = new HashMap<String, String>();
					map.put("CompanyId", p.getId().getCompanyId().toString());
					map.put("ProductId", p.getId().getProductId().toString());
					map.put("ColorCode", p.getId().getColourCode().toString());
					map.put("ColorName", p.getColourName());
					map.put("Status", p.getStatus());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					mapList.add(map);
				});
				response.setMessage("Success");
				response.setResponse(mapList);
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
	public CommonResponse editSizeColorDetails(GetProductSizeColorReq r) {
		CommonResponse response = new CommonResponse();
		try {

			ProductColorMasterId id = ProductColorMasterId.builder()
					.companyId(Integer.valueOf(r.getCompanyId()))
					.productId(Integer.valueOf(r.getProductId()))
					.colourCode(Integer.valueOf(r.getColorCode()))
					.build();
			
			Optional<ProductColorMaster> data =productSizeColorMasterRepo.findById(id);
			
			if(data.isPresent()) {
				
				ProductColorMaster p =data.get();
				Map<String,String> map = new HashMap<String, String>();
				map.put("CompanyId", p.getId().getCompanyId().toString());
				map.put("ProductId", p.getId().getProductId().toString());
				map.put("ColorCode", p.getId().getColourCode().toString());
				map.put("ColorName", p.getColourName());
				map.put("Status", p.getStatus());
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

	@Override
	public CommonResponse saveColorMetal(List<SaveProductSizeColorMetalReq> req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.colorMetal(req);
			if(error.isEmpty()) {
			    int sno =1;
				for(SaveProductSizeColorMetalReq r :req) {
					
					ProductColorMetalMasterId id = ProductColorMetalMasterId.builder()
							.companyId(Integer.valueOf(r.getCompanyId()))
							.productId(Integer.valueOf(r.getProductId()))
							.colourCode(Integer.valueOf(r.getColorCode()))	
							.metalId(StringUtils.isBlank(r.getMetalId())?getColorMetlaId(Integer.valueOf(r.getCompanyId()),Integer.valueOf(r.getProductId()),
									Integer.valueOf(r.getColorCode()))
									:Integer.valueOf(r.getColorCode()))
							.build();
					
					ProductColorMetalMaster master =ProductColorMetalMaster.builder()
							.entryDate(new Date())
							.id(id)
							.displayOrder(Integer.valueOf(r.getDisplayOrder()))
							.mesurementPieces(Integer.valueOf(r.getMesurementPieces()))
							.mesurementType(r.getMesurementType())
							.mesurementValue(Double.valueOf(r.getMesurementValue()))
							.metalName(r.getMetalName())
							.columnName(StringUtils.isBlank(r.getColumnName())?"PARAM_"+sno:r.getColumnName())
							.status(r.getStatus())
							.mesurementName(r.getMesurementName())
							.build();
					
					productSizeColorMetalMasterRepo.save(master);
					
					sno++;
				}
				
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}


	private Integer getColorMetlaId(Integer companyId, Integer productId, Integer colorId) {
		Integer metalId =productSizeColorMetalMasterRepo.findByIdCompanyIdAndIdProductIdAndIdColourCode(companyId,productId,colorId).size()+1;
		return metalId;
	}

	@Override
	public CommonResponse getColorMetalDetails(GetProductColorMetalReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductColorMetalMaster> list =productSizeColorMetalMasterRepo.
					findByIdCompanyIdAndIdProductIdAndIdColourCode(Integer.valueOf(req.getCompanyId()),Integer.valueOf(req.getProductId()),Integer.valueOf(req.getColorCode()));
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					Map<String,String> map = new HashMap<String, String>();
					map.put("CompanyId", p.getId().getCompanyId().toString());
					map.put("ProductId", p.getId().getProductId().toString());
					map.put("ColorCode", p.getId().getColourCode().toString());
					map.put("MetalId", p.getId().getMetalId().toString());
					map.put("MetalName", p.getMetalName());
					map.put("ColumnName", p.getColumnName());
					map.put("MesurementType", p.getMesurementType());
					map.put("MesurementValue", p.getMesurementValue().toString());
					map.put("MesurementPieces", p.getMesurementPieces().toString());
					map.put("Status", p.getStatus());
					map.put("DisplayOrder", p.getDisplayOrder().toString());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					mapList.add(map);
				});
				response.setMessage("Success");
				response.setResponse(mapList);
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
	public CommonResponse editColorMetalDetails(GetProductColorMetalReq r) {
		CommonResponse response = new CommonResponse();
		try {

			ProductColorMetalMasterId id = ProductColorMetalMasterId.builder()
					.companyId(Integer.valueOf(r.getCompanyId()))
					.productId(Integer.valueOf(r.getProductId()))
					.metalId(Integer.valueOf(r.getColorCode()))
					.metalId(Integer.valueOf(r.getMetalId()))			
					.build();
			
			Optional<ProductColorMetalMaster> data =productSizeColorMetalMasterRepo.findById(id);
			
			if(data.isPresent()) {
				
				ProductColorMetalMaster p =data.get();
				
				Map<String,String> map = new HashMap<String, String>();
				map.put("CompanyId", p.getId().getCompanyId().toString());
				map.put("ProductId", p.getId().getProductId().toString());
				map.put("MetalId", p.getId().getMetalId().toString());
				map.put("ColorCode", p.getId().getColourCode().toString());
				map.put("MetalName", p.getMetalName());
				map.put("ColumnName", p.getColumnName());
				map.put("MesurementType", p.getMesurementType());
				map.put("MesurementValue", p.getMesurementValue().toString());
				map.put("MesurementPieces", p.getMesurementPieces().toString());
				map.put("Status", p.getStatus());
				map.put("DisplayOrder", p.getDisplayOrder().toString());
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

	@Override
	public CommonResponse saveProductMetal(List<ProductMetalReq> req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error = validation.productMetal(req);
			if(error.isEmpty()) {
				
				int sno =1;
				
				for(ProductMetalReq r :req) {
					
					ProductMetalMasterId id = ProductMetalMasterId.builder()
							.companyId(Integer.valueOf(r.getCompanyId()))
							.productId(Integer.valueOf(r.getProductId()))
							.metalId(StringUtils.isBlank(r.getMetalId())?getProductMetalId(Integer.valueOf(r.getCompanyId()),Integer.valueOf(r.getProductId())) 
									:Integer.valueOf(r.getMetalId()))		
							.build();
					
					ProductMetalMaster master =ProductMetalMaster.builder()
							.entryDate(new Date())
							.id(id)
							.displayOrder(Integer.valueOf(r.getDisplayOrder()))
							.mesurementPieces(Integer.valueOf(r.getMesurementPieces()))
							.mesurementType(r.getMesurementType())
							.mesurementValue(Double.valueOf(r.getMesurementValue()))
							.metalName(r.getMetalName())
							.mesurementName(r.getMesurementName())
							.columnName(StringUtils.isBlank(r.getColumnName())?"PARAM_"+sno:r.getColumnName())
							.status(r.getStatus())
							.build();
					
					productMetalRepo.save(master);
					
					sno++;
				}
				
				
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
				response.setError(null);
			}else {
				
				response.setMessage("Error");
				response.setResponse(null);
				response.setError(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Integer getProductMetalId(Integer companyId, Integer productId) {
		Long metalId =productMetalRepo.countByIdCompanyIdAndIdProductId(companyId,productId);
		return metalId.intValue() + 1;
	}

	@Override
	public CommonResponse getProductMetalDetails(GetProductMetalReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductMetalMaster> list =productMetalRepo.findByIdCompanyIdAndIdProductId(Integer.valueOf(req.getCompanyId()),Integer.valueOf(req.getProductId()));
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					Map<String,String> map = new HashMap<String, String>();
					map.put("CompanyId", p.getId().getCompanyId().toString());
					map.put("ProductId", p.getId().getProductId().toString());
					map.put("MetalId", p.getId().getMetalId().toString());
					map.put("MetalName", p.getMetalName());
					map.put("ColumnName", p.getColumnName());
					map.put("MesurementType", p.getMesurementType());
					map.put("MesurementValue", p.getMesurementValue().toString());
					map.put("MesurementPieces", p.getMesurementPieces().toString());
					map.put("MesurementName", p.getMesurementName());
					map.put("Status", p.getStatus());
					map.put("DisplayOrder", p.getDisplayOrder().toString());
					map.put("CreatedDate", sdf.format(p.getEntryDate()));
					mapList.add(map);
				});
				response.setMessage("Success");
				response.setResponse(mapList);
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
