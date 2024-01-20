package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.auth.EncryDecryService;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.repository.LoginMasterRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.LoginRequest;

@Component
public class InputValidationServiceImpl {
	
	
	@Autowired
	private LoginMasterRepository loginMasterRepository;
	
	@Autowired
	private EncryDecryService decryService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<ErrorList> validateLoginRequest(LoginRequest req){
		List<ErrorList> errorList = new ArrayList<ErrorList>();
		try {
			if(StringUtils.isBlank(req.getUserName())) {
				errorList.add(new ErrorList("UserName","500","Please enter username"));
			}if(StringUtils.isBlank(req.getPassword())) {
				errorList.add(new ErrorList("Password","500","Please enter password"));

			}
			if(StringUtils.isNotBlank(req.getUserName()) && StringUtils.isNotBlank(req.getPassword())) {
				
				String paswd =bCryptPasswordEncoder.encode(req.getPassword());
				
				LoginMaster loginMaster =loginMasterRepository.findByLoginIdAndPassword(req.getUserName(), paswd);
				
				if(loginMaster==null) {
					errorList.add(new ErrorList("User","401","Please enter valid username/password"));
				}else {
					
					String status =loginMaster.getStatus();
					if("N".equalsIgnoreCase(status)) {
						errorList.add(new ErrorList("User","401","You were blocked by admin..Contact Admin...!"));
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return errorList;
		
	}

}
