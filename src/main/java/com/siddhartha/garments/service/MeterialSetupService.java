package com.siddhartha.garments.service;

import com.siddhartha.garments.request.MeterialSetupRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface MeterialSetupService {

	CommonResponse saveMeterialSetup(MeterialSetupRequest req);

}
