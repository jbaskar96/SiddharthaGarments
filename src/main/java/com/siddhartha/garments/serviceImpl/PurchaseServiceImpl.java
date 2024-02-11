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

import com.siddhartha.garments.entity.PurchaseMaster;
import com.siddhartha.garments.repository.PurchaseRepository;
import com.siddhartha.garments.request.PurchaseRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{
	
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Autowired
	private PurchaseRepository purchaseRepo;

	@Override
	public CommonResponse savePurchase(PurchaseRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			Double cgstAmt=0D;
			Double sgstAmt=0D;
			Double totalAmount=0D;
			Double cgstTax =0D;
			Double scgsttax =0D;
			if(req.getCategoryId().equals("1")) {
				 cgstTax =Double.valueOf(req.getCGSTTax());
				 scgsttax =Double.valueOf(req.getSgstTax());
				 cgstAmt =Double.valueOf(req.getAmountBeforeTax()) * cgstTax/100;
				 sgstAmt =Double.valueOf(req.getAmountBeforeTax()) * scgsttax/100;
				 totalAmount =Double.valueOf(req.getAmountBeforeTax()) + cgstAmt +sgstAmt;
			}else if(req.getCategoryId().equals("2")){
				totalAmount=Double.valueOf(req.getAmountBeforeTax());
			}
			
			

			PurchaseMaster purchaseMaster =PurchaseMaster.builder()
					.amountBeforetax(StringUtils.isBlank(req.getAmountBeforeTax())?null:Double.valueOf(req.getAmountBeforeTax()))
					.billDate(sdf.parse(req.getBillDate()))
					.categoryId(Long.valueOf(req.getCategoryId()))
					.cgstTax(cgstTax)
					.sgstTax(cgstTax)
					.cgst(cgstAmt)
					.sgst(scgsttax)
					.entryDate(new Date())
					.billRefNo(req.getBillRefNo())
					.status(StringUtils.isBlank(req.getStatus())?"Y":"N")
					.supplierName(req.getSupplierName())
					.totalAmount(totalAmount)
					.product(req.getProductDesc())
					.serialNo(StringUtils.isBlank(req.getSerialNo())?purchaseRepo.count()+1:Long.valueOf(req.getSerialNo()))
					.build();
			purchaseRepo.save(purchaseMaster);
			response.setResponse("Data Saved Successfully");
			response.setMessage("Success");
			response.setError(null);
		}catch (Exception e) {
			e.printStackTrace();
			response.setResponse("Data Saved Failed");
			response.setMessage("Failed");
			response.setError(null);
		}
		return response;
	}

	@Override
	public CommonResponse getAll(Integer pageNo, Integer pageSize) {
		CommonResponse response = new CommonResponse();
		try {
			Pageable pageable =PageRequest.of(pageNo, pageSize,Sort.by("serialNo").ascending());
			List<PurchaseMaster> list =purchaseRepo.findAll(pageable).getContent();
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList= new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("SerialNo", p.getSerialNo().toString());
					map.put("CategoryId", p.getCategoryId().toString());
					map.put("BillRefNo", p.getBillRefNo());
					map.put("SupplierName",p.getSupplierName());
					map.put("AmountBeforetax", p.getAmountBeforetax()==null?"N/A":p.getAmountBeforetax().toString());
					map.put("SGST", p.getSgst()==null?"N/A":p.getSgst().toString());
					map.put("CGST", p.getCgst()==null?"N/A":p.getCgst().toString());
					map.put("TotalAmount", p.getTotalAmount().toString());
					map.put("BillDate", sdf.format(p.getBillDate()));
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
	public CommonResponse getAll(Long serialNo) {
		CommonResponse response = new CommonResponse();
		try {
			PurchaseMaster p =purchaseRepo.findById(serialNo).get();
			if(p!=null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("SerialNo", p.getSerialNo().toString());
				map.put("CategoryId", p.getCategoryId().toString());
				map.put("BillRefNo", p.getBillRefNo());
				map.put("SupplierName",p.getSupplierName());
				map.put("AmountBeforetax", p.getAmountBeforetax()==null?"N/A":p.getAmountBeforetax().toString());
				map.put("SGST", p.getSgst()==null?"N/A":p.getSgst().toString());
				map.put("CGST", p.getCgst()==null?"N/A":p.getCgst().toString());
				map.put("TotalAmount", p.getTotalAmount().toString());
				map.put("BillDate", sdf.format(p.getBillDate()));
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
