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

import com.siddhartha.garments.entity.ProductSectionMaster;
import com.siddhartha.garments.entity.ProductSectionMasterId;
import com.siddhartha.garments.repository.ProductSectionMasterRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.SectionSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.SectionMasterService;

@Service
public class SectionMasterServiceImpl implements SectionMasterService {
	
	@Autowired
	private ProductSectionMasterRepository repository;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Autowired
	private InputValidationServiceImpl validation;

	@Override
	public CommonResponse saveSection(List<SectionSaveRequest> req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> error =validation.validateSection(req);
			
			if(error.isEmpty()) {

				
				for(SectionSaveRequest r :req) {
					
					ProductSectionMasterId sectionId =ProductSectionMasterId.builder()
							.companyId(Integer.valueOf(r.getCompanyId()))
							.productId(Integer.valueOf(r.getProductId()))
							.sectionId(StringUtils.isBlank(r.getSectionId())?getProductSectionId(r.getCompanyId(),r.getProductId())
									:Integer.valueOf(r.getSectionId()))
							.build();
					
					
					ProductSectionMaster sectionMaster =ProductSectionMaster.builder()
							.id(sectionId)
							.sectionName(r.getSectionName())
							.status(StringUtils.isBlank(r.getStatus())?"Y":r.getStatus())
							.noOfPieces(Integer.valueOf(r.getNoOfPieces()))
							.piecesAmount(Double.valueOf(r.getPieceAmount()))
							.entryDate(new Date())
							.build();
					
					repository.save(sectionMaster);
				}
				
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

	private Integer getProductSectionId(String companyId, String productId) {
		Long sectionId =repository.countByIdCompanyIdAndIdProductId(Integer.valueOf(companyId),Integer.valueOf(productId));
		return sectionId.intValue()+1;
	}

	@Override
	public CommonResponse getSectionByCompanyIdAndProductId(Integer companyId, Integer productId) {
		CommonResponse response = new CommonResponse();
		try {

			List<ProductSectionMaster> list =repository.findByIdCompanyIdAndIdProductId(companyId,productId);
			if(!list.isEmpty()) {
				List<Map<String, String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("CompanyId", p.getId().getCompanyId().toString());
					map.put("ProductId", p.getId().getProductId().toString());
					map.put("SectionId", p.getId().getSectionId().toString());
					map.put("SectionName", p.getSectionName());
					map.put("NoOfPieces", p.getNoOfPieces().toString());
					map.put("PieceAmount", p.getPiecesAmount().toString());
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
	public CommonResponse editSection(Integer companyId, Integer sectionId) {
		CommonResponse response = new CommonResponse();

		/*try {
			ProductSectionMaster p =repository.findById(sectionId).orElse(null);
>>>>>>> Stashed changes
			if(p!=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("SectionId", p.getId().getSectionId().toString());
				map.put("CompanyId", p.getId().getCompanyId().toString());
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
		}*/
		return response;
	}
	@Override
	public CommonResponse deleteSection(Integer sectionId) {
		CommonResponse response = new CommonResponse();
		try {
			//repository.deleteById(sectionId);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse("data deleted successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
