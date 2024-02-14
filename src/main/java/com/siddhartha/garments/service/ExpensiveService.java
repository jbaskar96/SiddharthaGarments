package com.siddhartha.garments.service;

import com.siddhartha.garments.request.ExpensiveRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface ExpensiveService {

	CommonResponse saveExpensive(ExpensiveRequest req);

	CommonResponse getAllExpensive();

	CommonResponse editExpensive(Integer serialNo);

}
