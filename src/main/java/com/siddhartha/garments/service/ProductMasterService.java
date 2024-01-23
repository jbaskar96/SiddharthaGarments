package com.siddhartha.garments.service;

import com.siddhartha.garments.request.ProductSaveRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface ProductMasterService {

	CommonResponse saveProduct(ProductSaveRequest req);

	CommonResponse getAllProduct();

	CommonResponse editProduct(Integer productId);

	CommonResponse deleteProduct(Integer productId);

}
