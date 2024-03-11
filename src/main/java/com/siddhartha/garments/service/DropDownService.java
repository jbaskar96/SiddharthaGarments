package com.siddhartha.garments.service;

import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.GetSizeDetailsRequest;

public interface DropDownService {

	CommonResponse section();

	CommonResponse product();

	CommonResponse usertype();

	CommonResponse color();

	CommonResponse state();

	CommonResponse operator();

	CommonResponse size();

	CommonResponse district(String stateCode);

	CommonResponse getOrderDetails();

	CommonResponse getSizeDetails(String orderId);

	CommonResponse colorDeatilsByOrderId(String orderId, String sizeId);

	CommonResponse metalType();

	CommonResponse getCompanyBrand(String companyId);

	CommonResponse getBrandCategory(String companyId, String itemId);

	CommonResponse getCompany();

	CommonResponse getProduct(Integer companyId);

	CommonResponse getProductSize(Integer companyId, Integer productId);

	CommonResponse getProductSizeColor(Integer companyId, Integer productId, Integer sizeId);

	CommonResponse sizeType();

	CommonResponse size(GetSizeDetailsRequest req);

	CommonResponse colorDeatilsByOrderId(GetSizeDetailsRequest req);

}
