package com.siddhartha.garments.service;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.dto.InsertSizeCalcRequest;
import com.siddhartha.garments.request.InserSizeColorRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface MetalCalculationService {

	CommonResponse generateSizeCalc(EditOrderDetailsReq req);

	CommonResponse generateSizeColorCalc(EditOrderDetailsReq req);

	CommonResponse doInsertSizeCalc(InsertSizeCalcRequest req);

	CommonResponse viewSizeCalc(EditOrderDetailsReq req);

	CommonResponse viewSizeColorCalc(EditOrderDetailsReq req);

	CommonResponse insertSizeColorCalc(InserSizeColorRequest req);

}
