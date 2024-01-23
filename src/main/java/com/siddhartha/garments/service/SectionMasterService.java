package com.siddhartha.garments.service;

import com.siddhartha.garments.request.SectionSaveRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface SectionMasterService {

	CommonResponse saveSection(SectionSaveRequest req);

	CommonResponse getAllSection();

	CommonResponse editSection(Integer sectionId);

	CommonResponse deleteSection(Integer sectionId);

}
