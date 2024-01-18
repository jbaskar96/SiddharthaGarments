package com.siddhartha.garments.serviceImpl;



import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.siddhartha.garments.auth.EncryDecryService;
import com.siddhartha.garments.auth.JwtTokenUtil;
import com.siddhartha.garments.auth.passwordEnc;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.entity.SessionDetails;
import com.siddhartha.garments.repository.LoginMasterRepository;
import com.siddhartha.garments.repository.SessionDetailsRepository;
import com.siddhartha.garments.request.LoginRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.LoginService;

public class LoginServiceImpl implements LoginService,UserDetailsService {
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	private LoginMasterRepository loginMasterRepository;
	
	@Autowired
	private SessionDetailsRepository sessionDetailsRepository;
	
	@Autowired
	private EncryDecryService endecryService;


	@Override
	public CommonResponse login(LoginRequest request,HttpServletRequest servletRequest) {
		Map<String,String> response =new HashMap<String,String>();
		CommonResponse commonResponse = new CommonResponse();
		try {
			passwordEnc passEnc = new passwordEnc();
			String epass = passEnc.crypt(request.getPassword().trim());
			LoginMaster login =loginMasterRepository.findByLoginIdAndPassword(request.getUserName(),epass);
			if (login!=null) {
				String token = jwtTokenUtil.doGenerateToken(request.getUserName());
				servletRequest.getSession().removeAttribute(request.getUserName());
				SessionDetails session = new SessionDetails();
				session.setLoginId(request.getUserName());
				session.setTokenId(token);
				session.setStatus("Y");
				session.setUserType(login.getUsertype());
				String temptoken = bCryptPasswordEncoder.encode("CommercialClaim");
				session.setTempTokenid(temptoken);
				session.setEntryDate(new Date());
				session =sessionDetailsRepository.save(session);
				response= setTokenResponse(session,login);
			}
			commonResponse.setResponse(response);
			commonResponse.setMessage("Success");
			commonResponse.setError(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commonResponse;
	}

	private Map<String, String> setTokenResponse(SessionDetails session, LoginMaster login) {
		Map<String, String> response = new HashMap<String,String>();
		try {
			String userType =session.getUserType();
			response.put("Token", session.getTempTokenid());
			response.put("UserName", session.getLoginId());
			response.put("UserType", userType);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@SuppressWarnings("static-access")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginMaster userList = new LoginMaster();
		try {
			 userList =  loginMasterRepository.findByLoginId(username);
			if (userList!=null) {
				String pass =bCryptPasswordEncoder.encode(endecryService.decrypt("zQYgCo7GMZeX1tBQyzAi8Q=="));
				userList.setPassword(pass);
				Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
				grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(userList.getLoginId(), userList.getPassword(),
				getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
