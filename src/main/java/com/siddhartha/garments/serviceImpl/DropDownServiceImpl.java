package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.entity.CompanyMaster;
import com.siddhartha.garments.entity.DistrictMaster;
import com.siddhartha.garments.entity.OperatorMaster;
import com.siddhartha.garments.entity.OrderChallanDetails;
import com.siddhartha.garments.entity.OrderColorDetails;
import com.siddhartha.garments.entity.OrderDetails;
import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.entity.ProductMaster;
import com.siddhartha.garments.entity.ProductSectionMaster;
import com.siddhartha.garments.entity.ProductSizeMaster;
import com.siddhartha.garments.entity.StateMaster;
import com.siddhartha.garments.entity.UserTypeMaster;
import com.siddhartha.garments.repository.CompanyMasterRepository;
import com.siddhartha.garments.repository.DistrictMasterRepository;
import com.siddhartha.garments.repository.OperatorMasterRepository;
import com.siddhartha.garments.repository.OrderChallanDetailsRepository;
import com.siddhartha.garments.repository.OrderColorDetailsRepository;
import com.siddhartha.garments.repository.OrderDetailsRepository;
import com.siddhartha.garments.repository.ProductColorMasterRepository;
import com.siddhartha.garments.repository.ProductMasterRepository;
import com.siddhartha.garments.repository.ProductRepository;
import com.siddhartha.garments.repository.ProductSectionMasterRepository;
import com.siddhartha.garments.repository.ProductSizeMasterRepo;
import com.siddhartha.garments.repository.ProductSizeMasterRepository;
import com.siddhartha.garments.repository.StateMasterRepository;
import com.siddhartha.garments.repository.UserTypeMasterRepository;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.GetSizeDetailsRequest;
import com.siddhartha.garments.service.DropDownService;

@Service
public class DropDownServiceImpl implements DropDownService{
	
	@Autowired
	private ProductSectionMasterRepository sectionRepo;

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

	
	@Autowired
	private OrderColorDetailsRepository  orderColorRepo;
	
	@Autowired
	private CompanyMasterRepository companyRepo;
	
	@Autowired
	private ProductRepository companyProductRepo;
	

	@Autowired
	private ProductSizeMasterRepo productSizeMasterRepo;
	
	@Autowired
	private ProductColorMasterRepository productSizeColorMasterRepo;
	
	@Autowired
	private OrderDetailsRepository orderRepo;
	
	@Autowired
	private ProductColorMasterRepository pcmRepo;

	@Autowired
	private OrderChallanDetailsRepository orderChallanRepo;


	@Override
	public CommonResponse section(EditOrderDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {

			List<ProductSectionMaster> list =sectionRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getId().getSectionId().toString());
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
					map.put("Code", p.getId().getProductId().toString());
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
					map.put("Code", p.getId().getColourCode().toString());
					map.put("CodeDesc", p.getColourName());
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
			List<Map<String,String>> res = new ArrayList<>();
			
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("Code","OPT_1");
			map1.put("CodeDesc","baskar");
			
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("Code","OPT_2");
			map2.put("CodeDesc","Saravanan");
			
			HashMap<String, String> map3 = new HashMap<String, String>();
			map3.put("Code","OPT_3");
			map3.put("CodeDesc","Kalaivanan");
			
			
			
			res.add(map2);
			res.add(map1);
			res.add(map3);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse(res);

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
				//	map.put("Code", p.getProductSizeId().toString());
					//map.put("CodeDesc", p.getProductSize().toString());
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
	public CommonResponse metalType() {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,String>> res = new ArrayList<>();
			
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("Code","K");
			map1.put("CodeDesc","Killo(KG)");
			
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("Code","M");
			map2.put("CodeDesc","Meter");
			
			HashMap<String, String> map3 = new HashMap<String, String>();
			map3.put("Code","P");
			map3.put("CodeDesc","Pieces");
			
			HashMap<String, String> map4 = new HashMap<String, String>();
			map4.put("Code","CM");
			map4.put("CodeDesc","CentiMeter");
			
			HashMap<String, String> map5 = new HashMap<String, String>();
			map5.put("Code","I");
			map5.put("CodeDesc","Inch");
			
			HashMap<String, String> map6 = new HashMap<String, String>();
			map6.put("Code","G");
			map6.put("CodeDesc","Gram");
			
			
			res.add(map2);
			res.add(map1);
			res.add(map3);
			res.add(map4);
			res.add(map5);
			res.add(map6);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse(res);

		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getCompanyBrand(String companyId) {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,String>> res = new ArrayList<>();
			HashMap<String, String> map1 = new HashMap<String, String>();
			HashMap<String, String> map2 = new HashMap<String, String>();

			if("1".equals(companyId)) {
				map1.put("Code","1");
				map1.put("CodeDesc","HondaCity");
				map2.put("Code","2");
				map2.put("CodeDesc","Hyundai");
			}else if("2".equals(companyId)) {
				map1.put("Code","1");
				map1.put("CodeDesc","A1");
				map2.put("Code","2");
				map2.put("CodeDesc","A3");
			}
			
			res.add(map2);
			res.add(map1);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse(res);

		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getBrandCategory(String companyId, String itemId) {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,String>> res = new ArrayList<>();
			HashMap<String, String> map1 = new HashMap<String, String>();
			HashMap<String, String> map2 = new HashMap<String, String>();

			if("1".equals(companyId) && "1".equals(itemId)) {
				map1.put("Code","1");
				map1.put("CodeDesc","Salloon");
				map2.put("Code","2");
				map2.put("CodeDesc","Taxi");
			}else if("1".equals(companyId)&& "2".equals(itemId)) {
				map1.put("Code","1");
				map1.put("CodeDesc","Salloon");
				map2.put("Code","2");
				map2.put("CodeDesc","Taxi");
			}else if("2".equals(companyId)&& "1".equals(itemId)) {
				map1.put("Code","1");
				map1.put("CodeDesc","Salloon");
				map2.put("Code","2");
				map2.put("CodeDesc","Taxi");
			}else if("2".equals(companyId)&& "2".equals(itemId)) {
				map1.put("Code","1");
				map1.put("CodeDesc","Salloon");
				map2.put("Code","2");
				map2.put("CodeDesc","Taxi");
			}
			
			res.add(map2);
			res.add(map1);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse(res);

		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@Override
	public CommonResponse getCompany() {
		CommonResponse response = new CommonResponse();
		try {
			List<CompanyMaster> list =companyRepo.findByStatusIgnoreCase("Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getCompanyId().toString());
					map.put("CodeDesc", p.getCompanyName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getProduct(Integer companyId) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductMaster> list =companyProductRepo.findByIdCompanyIdAndStatusIgnoreCase(companyId,"Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getId().getProductId().toString());
					map.put("CodeDesc", p.getProductName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getOrderDetails() {
		CommonResponse response = new CommonResponse();
		try {
			List<OrderDetails> list =orderRepo.findByStatusIgnoreCase("P", Sort.by("lotNumber").ascending());
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getOrderId());
					map.put("CodeDesc", p.getLotNumber());
					res.add(map);
				});
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getSizeDetails(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse colorDeatilsByOrderId(String orderId, String sizeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse getProductSize(Integer companyId, Integer productId) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductSizeMaster> list =productSizeMasterRepo.findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(companyId,productId,"Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getId().getSizeId().toString());
					map.put("CodeDesc", p.getSize().toString());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getProductSizeColor(Integer companyId, Integer productId, Integer sizeId) {
		CommonResponse response = new CommonResponse();
		try {
			List<ProductColorMaster> list =productSizeColorMasterRepo.findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(companyId,productId,"Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getId().getColourCode().toString());
					map.put("CodeDesc", p.getColourName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse sizeType() {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,String>> res = new ArrayList<>();
			
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("Code","XL");
			map1.put("CodeDesc","XL");
			
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("Code","Large");
			map2.put("CodeDesc","Large");
			
			HashMap<String, String> map3 = new HashMap<String, String>();
			map3.put("Code","XXL");
			map3.put("CodeDesc","XXL");
			
			HashMap<String, String> map4 = new HashMap<String, String>();
			map4.put("Code","Medium");
			map4.put("CodeDesc","Medium");
			
			HashMap<String, String> map5 = new HashMap<String, String>();
			map5.put("Code","Small");
			map5.put("CodeDesc","Small");
			
			res.add(map2);
			res.add(map1);
			res.add(map3);
			res.add(map4);
			res.add(map5);
			response.setError(null);
			response.setMessage("Success");
			response.setResponse(res);

		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse size(GetSizeDetailsRequest req) {
		CommonResponse response =new CommonResponse();
		try {
			List<OrderChallanDetails>  list =orderChallanRepo.findByIdOrderId(req.getOrderId());
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getId().getChallanId());
					map.put("CodeDesc", p.getSize().toString());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse colorDeatilsByOrderId(GetSizeDetailsRequest req) {
		CommonResponse response =new CommonResponse();
		try {
			List<OrderColorDetails>  list =orderColorRepo.findByIdOrderIdAndIdChallanId(req.getOrderId(), req.getChallanId());
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getId().getColorId());
					map.put("CodeDesc", p.getColorName());
					map.put("TotalPieces", p.getTotalPieces().toString());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse orderSizes(EditOrderDetailsReq req) {
		CommonResponse response =new CommonResponse();
		try {
			List<OrderChallanDetails> list = orderChallanRepo.findByIdOrderId(req.getOrderId());
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getId().getChallanId());
					map.put("CodeDesc", p.getSize().toString());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getColorDetails(Integer companyId, Integer productId) {
		CommonResponse response =new CommonResponse();
		try {
			List<ProductColorMaster>  list =pcmRepo.findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(companyId, productId, "Y");
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Code", p.getId().getColourCode().toString());
					map.put("CodeDesc", p.getColourName());
					res.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(res);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
