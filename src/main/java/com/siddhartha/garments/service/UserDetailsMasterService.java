package com.siddhartha.garments.service;

import com.siddhartha.garments.request.UserDetailsRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface UserDetailsMasterService {

	CommonResponse saveUserDetails(UserDetailsRequest req);

}
