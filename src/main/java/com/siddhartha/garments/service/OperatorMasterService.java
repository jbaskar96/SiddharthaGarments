package com.siddhartha.garments.service;

import com.siddhartha.garments.request.OperatorSaveRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface OperatorMasterService {

	CommonResponse saveOperator(OperatorSaveRequest req);

	CommonResponse getAllOperator();

	CommonResponse editOperator(String operatorId);

	CommonResponse deleteOperator(String operatorId);

}
