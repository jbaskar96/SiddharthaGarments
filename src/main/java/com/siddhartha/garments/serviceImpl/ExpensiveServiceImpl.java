package com.siddhartha.garments.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.entity.ExpensiveDetails;
import com.siddhartha.garments.entity.PurchaseMaster;
import com.siddhartha.garments.repository.ExpensiveRepository;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.ExpensiveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ExpensiveService;

@Service
public class ExpensiveServiceImpl implements ExpensiveService{
	
	@Autowired
	private ExpensiveRepository expensiveRepo;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Autowired
	private InputValidationServiceImpl validation;
	

	@Override
	public CommonResponse saveExpensive(ExpensiveRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			
			List<ErrorList> errorLists =validation.validateExpensive(req);
			if(errorLists.isEmpty()) {
				ExpensiveDetails expensiveDetails = ExpensiveDetails.builder()
						.accountType(req.getAccountType())
						.amount(Double.valueOf(req.getAmount()))
						.categoryType(req.getCategoryType())
						.entryDate(new Date())
						.notes(StringUtils.isBlank(req.getNotes())?null:req.getNotes())
						.serialNo(StringUtils.isBlank(req.getSerialNo())?expensiveRepo.count()+1:Long.valueOf(req.getSerialNo()))
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.expeniveDate(sdf.parse(req.getExpensiveDate()))
						.build();
				expensiveRepo.save(expensiveDetails);
				
				response.setError(null);
				response.setMessage("Success");
				response.setResponse("Data Saved Successfully");
			}else {
				response.setError(errorLists);
				response.setMessage("Failed");
				response.setResponse("Data saved failed");
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setError(null);
			response.setMessage("Failed");
			response.setResponse("Data Saved Failed");
		}
		return response;
	}

	@Override
	public CommonResponse getAllExpensive() {
		CommonResponse response = new CommonResponse();
		try {
			//Pageable pageable =PageRequest.of(pageNo, pageSize,Sort.by("serialNo").ascending());
			List<ExpensiveDetails> list =expensiveRepo.findAll();
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList= new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("SerialNo", p.getSerialNo().toString());
					map.put("CategoryId", p.getCategoryType());
					map.put("AccountType", p.getAccountType());
					map.put("Amount",p.getAmount().toString());
					map.put("Notes", p.getNotes());
					map.put("ExpensiveDate", sdf.format(p.getExpeniveDate()));
					map.put("EntryDate", sdf.format(p.getEntryDate()));
					map.put("Status", p.getStatus());
					mapList.add(map);
				});
				
				response.setMessage("Success");
				response.setError(null);
				response.setResponse(mapList);
			}else {
				response.setMessage("Failed");
				response.setError(null);
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public CommonResponse editExpensive(Integer serialNo) {
		CommonResponse response = new CommonResponse();
		try {
			ExpensiveDetails p =expensiveRepo.findById(Long.valueOf(serialNo)).get();
			if(p!=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("SerialNo", p.getSerialNo().toString());
				map.put("CategoryId", p.getCategoryType());
				map.put("AccountId", p.getAccountType());
				map.put("Amount",p.getAmount().toString());
				map.put("Notes", p.getNotes());
				map.put("ExpensiveDate", sdf.format(p.getExpeniveDate()));
				map.put("EntryDate", sdf.format(p.getEntryDate()));
				map.put("Status", p.getStatus());
				
				response.setMessage("Success");
				response.setError(null);
				response.setResponse(map);
			}else {
				response.setMessage("Failed");
				response.setError(null);
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
		
	

}
