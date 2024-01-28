package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.auth.passwordEnc;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.entity.UserDetailsMaster;
import com.siddhartha.garments.repository.LoginMasterRepository;
import com.siddhartha.garments.repository.UserDetailsMasterRepository;
import com.siddhartha.garments.repository.UserTypeMasterRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.UserDetailsRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.UserDetailsMasterService;

@Service
public class UserDetailsMasterServiceImpl implements UserDetailsMasterService{
	
	@Autowired
	private UserDetailsMasterRepository repository ;
	
	@Autowired
	private LoginMasterRepository loginrepo;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Autowired
	private CriteriaQueryServiceImpl criteriaQueryImpl;
	
	@Autowired
	private InputValidationServiceImpl validation;

	@Override
	public CommonResponse saveUserDetails(UserDetailsRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			
			Encoder encoder = Base64.getEncoder();
			
			List<ErrorList> error =validation.validateUserRequest(req);
			
			if(error.isEmpty()) {
				Long userId =repository.count()+1;
				UserDetailsMaster detailsMaster = UserDetailsMaster.builder()
						.userId(StringUtils.isBlank(req.getUserId())?"SUPER_"+userId:req.getUserId())
						.aadharNo(req.getAadharNo())
						.createdBy(req.getCreatedBy())
						.dateOfBirth(sdf.parse(req.getDateOfBirth()))
						.email(req.getEmail())
						.entryDate(new Date())
						.mobileNo(req.getMobileNo())
						.firstName(req.getFirstname())
						.lastName(req.getLastName())
						.stateCode(Integer.valueOf(req.getStateCode()))
						.districtCode(Integer.valueOf(req.getDistrictCode()))
						.city(req.getCity())
						.address(req.getAddress())
						.status(req.getStatus())
						.userType(Integer.valueOf(req.getUserType()))
						.loginId(req.getUserName())
						.build();
				UserDetailsMaster savedData =repository.save(detailsMaster);
				
				if(savedData!=null && "N".equalsIgnoreCase(req.getEditYn())) {
					LoginMaster loginMaster =LoginMaster.builder().build();
					loginMaster.setLoginId(savedData.getLoginId());
					loginMaster.setPassword(encoder.encodeToString(req.getPassword().getBytes()));
					loginMaster.setUsertype(savedData.getStatus());
					loginMaster.setEntryDate(new Date());
					loginMaster.setCreatedBy(savedData.getCreatedBy());
					loginMaster.setStatus(savedData.getStatus());
					loginrepo.save(loginMaster);
				}else {
					
					LoginMaster loginMaster =loginrepo.findById(req.getUserName()).orElse(null);
					loginMaster.setStatus(savedData.getStatus());
					loginMaster.setUpdateDate(new Date());
					loginrepo.save(loginMaster);
	
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

	@Override
	public CommonResponse getAllUserDetails() {
		CommonResponse response = new CommonResponse();
		try {
			List<UserDetailsMaster> list =repository.findAll();
			if(!list.isEmpty()) {
				List<Map<String,String>> res = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("FirstName", p.getFirstName());
					map.put("MobileNo", p.getMobileNo());
					map.put("AadharNo", p.getAadharNo());
					map.put("UserName", p.getLoginId());
					map.put("CreatedBy", p.getCreatedBy());
					map.put("LastName", p.getLastName());
					map.put("UserId", p.getUserId());
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
	public CommonResponse editUseDetails(String userId) {
		CommonResponse response = new CommonResponse();
		Decoder decoder = Base64.getDecoder();
		try {
			passwordEnc passwordEnc = new passwordEnc();
			List<Tuple> userList =criteriaQueryImpl.getUserList(userId);
			if(!userList.isEmpty()) {
				Tuple tuple =userList.get(0);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("UserId", tuple.get("userId")==null?"":tuple.get("userId").toString());
				map.put("FirstName", tuple.get("firstName")==null?"":tuple.get("firstName").toString());
				map.put("LastName", tuple.get("lastName")==null?"":tuple.get("lastName").toString());
				map.put("Mobile", tuple.get("mobileNo")==null?"":tuple.get("mobileNo").toString());
				map.put("Email", tuple.get("email")==null?"":tuple.get("email").toString());
				map.put("AadharNo", tuple.get("aadharNo")==null?"":tuple.get("aadharNo").toString());
				map.put("StateCode", tuple.get("stateCode")==null?"":tuple.get("stateCode").toString());
				map.put("DistrictCode", tuple.get("districtCode")==null?"":tuple.get("districtCode").toString());
				map.put("City", tuple.get("city")==null?"":tuple.get("city").toString());
				map.put("Address", tuple.get("address")==null?"":tuple.get("address").toString());
				map.put("UserName", tuple.get("loginId")==null?"":tuple.get("loginId").toString());
				map.put("Password", tuple.get("password")==null?"":new String(decoder.decode(tuple.get("password").toString())));
				map.put("DateOfBirth", tuple.get("dateOfBirth")==null?"":sdf.format(tuple.get("dateOfBirth")));
				map.put("Status", tuple.get("status")==null?"":tuple.get("status").toString());
				map.put("CreatedBy", tuple.get("createdBy")==null?"":tuple.get("createdBy").toString());
				map.put("UserType", tuple.get("userType")==null?"":tuple.get("userType").toString());
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(map);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No Data Found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
