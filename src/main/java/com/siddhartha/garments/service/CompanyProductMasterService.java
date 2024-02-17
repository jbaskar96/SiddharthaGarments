package com.siddhartha.garments.service;

import com.siddhartha.garments.request.CompanyMasterRequest;
import com.siddhartha.garments.request.CompanyProductRequest;
import com.siddhartha.garments.request.ProductStyleMasterRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface CompanyProductMasterService {

	CommonResponse companySave(CompanyMasterRequest req);

	CommonResponse productSave(CompanyProductRequest req);

	CommonResponse styleSave(ProductStyleMasterRequest req);

	CommonResponse getAllCompany();

	CommonResponse editCompany(Integer companyId);

	CommonResponse getAllProductByCompanyId(Integer companyId);

	CommonResponse editProductByCompanyIdAndProductId(Integer companyId, Integer productId);

}
