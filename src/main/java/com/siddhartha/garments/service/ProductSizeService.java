package com.siddhartha.garments.service;

import com.siddhartha.garments.request.SizeMasterRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface ProductSizeService {

	CommonResponse saveSize(SizeMasterRequest req);

	CommonResponse editSize(Integer sizeId);

	CommonResponse deleteSize(Integer sizeId);

	CommonResponse getAllSize();

}
