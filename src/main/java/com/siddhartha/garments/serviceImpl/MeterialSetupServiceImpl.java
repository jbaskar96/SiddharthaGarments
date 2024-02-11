package com.siddhartha.garments.serviceImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.MeterialSetupMaster;
import com.siddhartha.garments.entity.MeterialSetupMasterId;
import com.siddhartha.garments.repository.MeterialDetailsRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.MeterialSetupRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.MeterialSetupService;

@Service
public class MeterialSetupServiceImpl implements MeterialSetupService {
	
	
	@Autowired
	private InputValidationServiceImpl validation;
	
	@Autowired
	private MeterialDetailsRepository meterialSetupRepo;

	@Override
	public CommonResponse saveMeterialSetup(MeterialSetupRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ErrorList> errorLists =validation.validateMeterialSetup(req);
			if(!errorLists.isEmpty()) {
				
				MeterialSetupMasterId meterialSetupMasterId = MeterialSetupMasterId.builder()
						.productId(Integer.valueOf(req.getProductId()))
						.setupId(StringUtils.isBlank(req.getSetupId())?getSetupId(req.getProductId()):Integer.valueOf(req.getSetupId()))
						.build();
				
				MeterialSetupMaster master =MeterialSetupMaster.builder()
						.metId(meterialSetupMasterId)
						.tableColumnName(req.getTableColumn())
						.displayColumn(req.getDisplayColumn())
						.displayOrder(Integer.valueOf(req.getDisplayOrder()))
						.quantityType(req.getQuantityType())
						.calcType(StringUtils.isBlank(req.getCalcType())?null:req.getCalcType())
					//	.percentageValue(StringUtils.isBlank(req.getCalcPercentage()))
						.build();
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("Data saved successfully");
			}else{
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("Data saved failed");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Integer getSetupId(String productId) {
		return null;
		//Long setupId =meterialSetupRepo.countByProductId(Integer.valueOf(productId));
		//return setupId.intValue();
	}

}
