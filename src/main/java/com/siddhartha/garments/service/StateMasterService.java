package com.siddhartha.garments.service;

import com.siddhartha.garments.request.StateSaveRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface StateMasterService {

	CommonResponse saveState(StateSaveRequest req);

	CommonResponse editState(Integer stateCode);

	CommonResponse getAllState();

	CommonResponse deleteState(Integer stateCode);

}
