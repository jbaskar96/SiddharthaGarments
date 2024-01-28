package com.siddhartha.garments.service;

import com.siddhartha.garments.request.ColorDetailsRequest;
import com.siddhartha.garments.request.DeliveryMoveRequest;
import com.siddhartha.garments.request.EditColorDetailReq;
import com.siddhartha.garments.request.EditMeterialDetailReq;
import com.siddhartha.garments.request.GetColorDetailsReq;
import com.siddhartha.garments.request.LotDetailsRequest;
import com.siddhartha.garments.request.MeterialDetailsReq;
import com.siddhartha.garments.request.MeterialDetailsRequest;
import com.siddhartha.garments.request.ProductionMoveReq;
import com.siddhartha.garments.response.CommonResponse;

public interface LotCreationService {

	CommonResponse lotCreation(LotDetailsRequest req);

	CommonResponse getLotDetails(String orderId);

	CommonResponse challanColorCreation(ColorDetailsRequest req);

	CommonResponse challanMeterialSave(MeterialDetailsRequest req);

	CommonResponse getColorDetails(GetColorDetailsReq req);

	CommonResponse getMeterialDetails(MeterialDetailsReq req);

	CommonResponse editColorDetail(EditColorDetailReq req);

	CommonResponse editMeterialDetail(EditMeterialDetailReq req);

	CommonResponse getOrderDeatils(String status);

	CommonResponse moveProduction(ProductionMoveReq req);

	CommonResponse moveDelivery(DeliveryMoveRequest req);


}
