package com.siddhartha.garments.service;

import java.util.List;

import com.siddhartha.garments.request.CompanyMeterialDropdownReq;
import com.siddhartha.garments.request.CompanyMeterialMasterReq;
import com.siddhartha.garments.response.CommonResponse;

public interface CompanyMeterialMasterService {

	CommonResponse saveMeterial(List<CompanyMeterialMasterReq> req);

	CommonResponse getAllMeterialByCompanyId(Integer companyId, Integer productId);

	CommonResponse productDropdown(CompanyMeterialDropdownReq req);

	CommonResponse sizeDropdown(CompanyMeterialDropdownReq req);

	CommonResponse colorDropdown(CompanyMeterialDropdownReq req);

}
