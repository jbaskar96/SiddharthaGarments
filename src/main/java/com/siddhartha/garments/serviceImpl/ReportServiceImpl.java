package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.WorkerEntryDetails;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private CriteriaQueryServiceImpl query;
	
	@Autowired
	private SimpleDateFormat sdf;

	@Override
	public CommonResponse getWorkerReport(WorkerEntryDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<WorkerEntryDetails> list = query.getWorkerReportDetails(req);
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("SerialNo", p.getSerialNo().toString());
					map.put("OrderId", p.getOrderId());
					map.put("SectionId", p.getSectionId().toString());
					map.put("OrderId", p.getOrderId());
					map.put("ChallanId", p.getChallanId());
					map.put("ColorId", p.getColorId().toString());
					map.put("WorkedPieces", p.getWorkedPieces().toString());
					map.put("GoodPieces", p.getGoodPieces().toString());
					map.put("DamagedPieces", p.getDamagedPieces().toString());
					map.put("UpdatedBy", p.getUpdatedBy());
					map.put("EntryDate", sdf.format(p.getEntryDate()));
					
					mapList.add(map);
				});
				response.setError(null);
				response.setMessage("Success");
				response.setResponse(mapList);
			}else {
				response.setError(null);
				response.setMessage("Failed");
				response.setResponse("No Record Found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
