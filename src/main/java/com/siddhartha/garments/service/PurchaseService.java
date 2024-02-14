package com.siddhartha.garments.service;

import com.siddhartha.garments.request.PurchaseRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface PurchaseService {

	CommonResponse savePurchase(PurchaseRequest req);

	CommonResponse getAll();

	CommonResponse getAll(Long serialNo);

}
