package com.siddhartha.garments.service;

import java.util.List;

import com.siddhartha.garments.request.SectionSaveRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface SectionMasterService {

	CommonResponse saveSection(List<SectionSaveRequest> req);

	CommonResponse editSection(Integer companyId, Integer sectionId);

	CommonResponse deleteSection(Integer sectionId);

	CommonResponse getSectionByCompanyIdAndProductId(Integer companyId, Integer productId);

}
