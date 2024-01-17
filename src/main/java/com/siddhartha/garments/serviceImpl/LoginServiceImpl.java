package com.siddhartha.garments.serviceImpl;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;

import com.maan.claim.bean.ClaimLoginMaster;
import com.siddhartha.garments.auth.JwtTokenUtil;
import com.siddhartha.garments.auth.passwordEnc;
import com.siddhartha.garments.entity.SessionDetails;
import com.siddhartha.garments.request.LoginRequest;
import com.siddhartha.garments.service.LoginService;

public class LoginServiceImpl implements LoginService,UserDetailsService {
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public Object login(LoginRequest request) {
		CommonLoginResponse res = new CommonLoginResponse();
		ClaimLoginResponse response = new ClaimLoginResponse();
		try {
			passwordEnc passEnc = new passwordEnc();
			String epass = passEnc.crypt(mslogin.getPassword().trim());
			log.info("Encrpted password "+epass);
			List<ClaimLoginMaster> login =loginRepo.findByLoginIdAndPassword(mslogin.getUserId(),epass);
			if (!CollectionUtils.isEmpty(login)) {
				String token = jwtTokenUtil.doGenerateToken(mslogin.getUserId());
				log.info("-----token------" + token);
				http.getSession().removeAttribute(mslogin.getUserId());
				SessionDetails session = new SessionDetails();
				session.setLoginId(mslogin.getUserId());
				session.setTokenId(token);
				session.setStatus("Y");
				//session.setUsertype(login.getUsertype());
				String temptoken = bCryptPasswordEncoder.encode("CommercialClaim");
				session.setTempTokenid(temptoken);
				session.setEntryDate(new Date());
				session =sessionRep.save(session);
				response= setTokenResponse(session,login);
				res.setLoginResponse(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@SuppressWarnings("static-access")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ClaimLoginMaster userList = new ClaimLoginMaster();
		try {
			log.info("loadUserByUsername==>" + username);
			 userList =  loginRepo.findByLoginId(username);
			if (userList!=null) {
				//user = userList.get(0);
				String pass = bCryptPasswordEncoder.encode(endecryService.decrypt("zQYgCo7GMZeX1tBQyzAi8Q=="));
				userList.setPassword(pass);
				Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
				grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
				//log.info("loadUserByUserType==>" + user.getUsertype());
				//log.info("loadUserByPassword==>" + user.getPassword());
				log.info("loadUserByTokenEncrypt==>" + userList.getPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(userList.getLoginId(), userList.getPassword(),
				getAuthority());
	}
}
