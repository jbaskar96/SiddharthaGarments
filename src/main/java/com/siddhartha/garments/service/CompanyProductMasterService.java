package com.siddhartha.garments.service;

import java.util.List;

import com.siddhartha.garments.dto.GetProductColorMetalReq;
import com.siddhartha.garments.dto.GetProductSizeColorReq;
import com.siddhartha.garments.dto.GetProductSizeMetalReq;
import com.siddhartha.garments.dto.ProductSizeColorRequest;
import com.siddhartha.garments.dto.ProductSizeMasterReq;
import com.siddhartha.garments.dto.ProductSizeMetalReq;
import com.siddhartha.garments.dto.SaveProductSizeColorMetalReq;
import com.siddhartha.garments.request.CompanyMasterRequest;
import com.siddhartha.garments.request.CompanyProductRequest;
import com.siddhartha.garments.request.ProductStyleMasterRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.GetProductMetalReq;
import com.siddhartha.garments.response.ProductMetalReq;

public interface CompanyProductMasterService {

	CommonResponse companySave(CompanyMasterRequest req);

	CommonResponse productSave(CompanyProductRequest req);

	CommonResponse styleSave(ProductStyleMasterRequest req);

	CommonResponse getAllCompany();

	CommonResponse editCompany(Integer companyId);

	CommonResponse getAllProductByCompanyId(Integer companyId);

	CommonResponse editProductByCompanyIdAndProductId(Integer companyId, Integer productId);

	CommonResponse getAllStyle(Integer companyId, Integer productId);

	CommonResponse getAllStyle(Integer companyId, Integer productId, Integer styleId);

	CommonResponse saveSize(List<ProductSizeMasterReq> req);

	CommonResponse getSizeDetailsByCompanyIdAndPrductId(Integer companyId, Integer productId);

	CommonResponse getSizeDetailsByCompanyIdAndPrductId(Integer companyId, Integer productId, Integer sizeId);

	CommonResponse saveSizeMetal(List<ProductSizeMetalReq> req);

	CommonResponse getSizeMetalDetails(GetProductSizeMetalReq req);

	CommonResponse editSizeMetalDetails(GetProductSizeMetalReq req);

	CommonResponse saveSizeColor(List<ProductSizeColorRequest> req);

	CommonResponse getSizeColorDetails(GetProductSizeColorReq req);

	CommonResponse editSizeColorDetails(GetProductSizeColorReq req);

	CommonResponse saveColorMetal(List<SaveProductSizeColorMetalReq> req);

	CommonResponse getColorMetalDetails(GetProductColorMetalReq req);

	CommonResponse editColorMetalDetails(GetProductColorMetalReq req);

	CommonResponse saveProductMetal(List<ProductMetalReq> req);

	CommonResponse getProductMetalDetails(GetProductMetalReq req);

}
