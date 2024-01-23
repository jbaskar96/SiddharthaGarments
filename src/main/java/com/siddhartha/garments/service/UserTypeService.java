package com.siddhartha.garments.service;

import com.siddhartha.garments.request.UserTypeRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface UserTypeService {

	CommonResponse saveUserType(UserTypeRequest req);

	CommonResponse getAllUserType();

	CommonResponse editUserType(Integer userTypeId);

	CommonResponse deleteUserType(Integer userTypeId);

}
