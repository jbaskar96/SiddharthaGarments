package com.siddhartha.garments.service;

import com.siddhartha.garments.response.CommonResponse;

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

}
