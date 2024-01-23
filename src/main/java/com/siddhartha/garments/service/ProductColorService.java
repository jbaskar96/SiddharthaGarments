package com.siddhartha.garments.service;

import com.siddhartha.garments.request.ColorSaveRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface ProductColorService {

	CommonResponse saveColor(ColorSaveRequest req);

	CommonResponse getAllColor();

	CommonResponse editColor(Integer colorId);

	CommonResponse deleteColor(Integer colorId);

}
