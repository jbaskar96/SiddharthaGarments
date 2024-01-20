package com.siddhartha.garments.service;

import com.siddhartha.garments.request.DisrictRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface DistrictMasterService {

	CommonResponse saveDistrict(DisrictRequest req);

	CommonResponse getAllDistrict(Integer statecode);

	CommonResponse getAllDistrict(Integer stateCode, Integer districtCode);

	CommonResponse deleteDistrict(Integer stateCode, Integer districtCode);

}
