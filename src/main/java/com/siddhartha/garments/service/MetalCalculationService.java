package com.siddhartha.garments.service;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.dto.InsertSizeCalcRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface MetalCalculationService {

	CommonResponse doSizeCalc(EditOrderDetailsReq req);

	CommonResponse doSizeColorCalc(EditOrderDetailsReq req);

	CommonResponse doInsertSizeCalc(InsertSizeCalcRequest req);

	CommonResponse viewSizeCalc(EditOrderDetailsReq req);

}
