package com.siddhartha.garments.service;

import java.util.List;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.dto.GetOrderSizeColorReq;
import com.siddhartha.garments.dto.OrderChallanColorReq;
import com.siddhartha.garments.dto.OrderDetailsRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface OrderDetailsService {

	CommonResponse saveOrder(OrderDetailsRequest req);

	CommonResponse editOrder(EditOrderDetailsReq req);

	CommonResponse saveColor(List<OrderChallanColorReq> req);

	CommonResponse getOrderColorDetails(GetOrderSizeColorReq req);

	CommonResponse editOrderColorDetails(GetOrderSizeColorReq req);

	CommonResponse getAllOrderDeatils(String status);

}
