package com.siddhartha.garments.service;

import com.siddhartha.garments.request.PurchaseRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface PurchaseService {

	CommonResponse savePurchase(PurchaseRequest req);

	CommonResponse getAll(Integer pageNo, Integer pageSize);

	CommonResponse getAll(Long serialNo);

}
