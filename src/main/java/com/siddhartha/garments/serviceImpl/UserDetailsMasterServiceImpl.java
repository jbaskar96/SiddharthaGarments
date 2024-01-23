package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.auth.passwordEnc;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.entity.UserDetailsMaster;
import com.siddhartha.garments.entity.UserTypeMaster;
import com.siddhartha.garments.repository.LoginMasterRepository;
import com.siddhartha.garments.repository.UserDetailsMasterRepository;
import com.siddhartha.garments.repository.UserTypeMasterRepository;
import com.siddhartha.garments.request.UserDetailsRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.UserDetailsMasterService;

@Service
public class UserDetailsMasterServiceImpl implements UserDetailsMasterService{
	
	@Autowired
	private UserDetailsMasterRepository repository ;
	
	@Autowired
	private UserTypeMasterRepository userTypeRepo;
	
	@Autowired
	private LoginMasterRepository loginrepo;
	
	@Autowired
	private SimpleDateFormat sdf;

	@Override
	public CommonResponse saveUserDetails(UserDetailsRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			Long userId =repository.count()+1;
			UserTypeMaster userType =userTypeRepo.findById(Integer.valueOf(req.getUserType())).orElse(null);
			UserDetailsMaster detailsMaster = UserDetailsMaster.builder()
					.userId(StringUtils.isBlank(req.getUserId())?"SUPER_"+userId:req.getUserId())
					.aadharNo(req.getAadharNo())
					.createdBy(req.getCreatedBy())
					.dateOfBirth(sdf.parse(req.getDateOfBirth()))
					.email(req.getEmail())
					.entryDate(new Date())
					.mobileNo(req.getMobileNo())
					.name(req.getName())
					.status(req.getStatus())
					.userType(userType.getUserType())
					.loginId(null)
					.build();
			UserDetailsMaster savedData =repository.save(detailsMaster);
			
			if(savedData!=null) {
				passwordEnc passwordEnc = new passwordEnc();
				LoginMaster loginMaster =LoginMaster.builder().build();
				loginMaster.setLoginId(req.getUserName());
				loginMaster.setPassword(passwordEnc.encrypt(req.getPassword()));
				loginMaster.setUsertype(savedData.getStatus());
				loginMaster.setEntryDate(new Date());
				loginMaster.setCreatedBy(req.getCreatedBy());
				loginMaster.setStatus(req.getStatus());
				
				loginrepo.save(loginMaster);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
