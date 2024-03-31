package com.siddhartha.garments.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.siddhartha.garments.entity.ExpensiveDetails;
import com.siddhartha.garments.entity.PurchaseMaster;
import com.siddhartha.garments.repository.WorkerEntryRepository;
import com.siddhartha.garments.request.PurchaseReportReq;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.ExpensiveReportRequest;
import com.siddhartha.garments.response.OrderReportReq;
import com.siddhartha.garments.response.WorkerReportReq;
import com.siddhartha.garments.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private CriteriaQueryServiceImpl query;
	
	@Autowired
	private SimpleDateFormat sdf;

	@Autowired
	private WorkerEntryRepository workerEntryRepository;
	
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/mm/dd");
	
	@Override
	public CommonResponse getWorkerReport(WorkerReportReq req) {
		CommonResponse response = new CommonResponse();
		try {
			Date startDate =sdf2.parse(req.getStartDate());
			Date EndDate =sdf2.parse(req.getEndDate());
			String start_date =sdf2.format(startDate);
			String end_date =sdf2.format(EndDate);
			List<Map<String,Object>> list =null;
			if("ALL".equalsIgnoreCase(req.getOperatorId())) {
			
				 list = workerEntryRepository.getWorkerReport(start_date,end_date);
			}else {
				
				list = workerEntryRepository.getWorkerReport(start_date,end_date,req.getOperatorId());
			}
			
			if(list!=null) {
				list.forEach(p ->{
					Map<String,String> map = new HashMap<String, String>();
					map.put("CompanyName", "");
					map.put("ProductName", "");
					map.put("OrderId", "");
					map.put("LotNumber", "");
					map.put("ChallanNo", "");
					map.put("ColorName", "");
					map.put("TotalPieces", "");
					map.put("EmployeeWorkedPieces", "");
					map.put("EmployeeFees", "");
					map.put("SectionName", "");
					map.put("OperatorName", "");
					map.put("", "");
					map.put("", "");
					map.put("", "");
				});
			}else{
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse exportExcel(WorkerEntryDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			
			Object[][] object =null;
			
			if(StringUtils.isBlank(req.getOperatorId()) && StringUtils.isBlank(req.getSectionId()) && StringUtils.isBlank(req.getOrderId())
					&& StringUtils.isBlank(req.getChallanId()) && StringUtils.isBlank(req.getColorId())) {
				
				object =workerEntryRepository.getReportDeatils();
				
			}else if(StringUtils.isBlank(req.getOperatorId()) && StringUtils.isBlank(req.getSectionId()) && StringUtils.isBlank(req.getOrderId())
					&& StringUtils.isBlank(req.getChallanId())) {
				
				object =workerEntryRepository.getReportDeatils();
				
			}else if(StringUtils.isBlank(req.getOperatorId()) && StringUtils.isBlank(req.getSectionId()) && StringUtils.isBlank(req.getOrderId())) {
				
				object =workerEntryRepository.getReportDeatils();
				
			}else if(StringUtils.isBlank(req.getOperatorId()) && StringUtils.isBlank(req.getSectionId())) {
				
				object =workerEntryRepository.getReportDeatils();
				
			}else if(StringUtils.isBlank(req.getOperatorId())) {
				
				object =workerEntryRepository.getReportDeatils();

			}else {
				
				object =workerEntryRepository.getReportDeatils();
			}
			
			
			if(object.length>0) {
				
				String [] excelHeaders =new String[] {
						"OPERATOR_NAME,DEPARTMENT,LOT_NUMBER,CHALLAN_NO,SIZE,TOTAL_PIECES"
						+ ",OPERATOR_WORKED_PIECES,OPERATOR_DAMAGED_PIECES,UPDATED_BY,UPDATED_DATE"
				};
				
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet =workbook.createSheet("OPERATOR_WORK_REPORT");
					
				XSSFCellStyle cellStyle =workbook.createCellStyle();
				XSSFFont font = workbook.createFont();
					
				font.setBold(true);
				font.setFontHeight(10);
				font.setFontName("Arial");
				cellStyle.setFont(font);
				
				Row row =sheet.getRow(0);
				
				int headerCol =0;
				
				// header
				for(String str : excelHeaders) {
					
					Cell cell =row.createCell(headerCol);
					cell.setCellValue(str);
					cell.setCellStyle(cellStyle);
					
					headerCol++;
				}
				
				 // Auto-size the cells in the first row
		        for (int i = 0; i <excelHeaders.length; i++) {
		            sheet.autoSizeColumn(i);
		            
		        }
		        
		        int rownumber =1;
		        for(Object [] ob :object) {
					row =sheet.createRow(rownumber++);
					int col =0;
					for(Object str : ob) {
						Cell cell =row.createCell(col++);
						cell.setCellValue(str==null?"":str.toString());
					}
				}
		        
		        ByteArrayOutputStream bos = new ByteArrayOutputStream();
				workbook.write(bos);
				workbook.close();
				byte [] byteArry = bos.toByteArray();
				String prefix = "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,";
				String base64 = Base64Utils.encodeToString(byteArry);
			
				HashMap<String, String> map = new HashMap<String,String>();
				map.put("Base64", prefix+base64);
		        
				response.setResponse(map);
				response.setMessage("Success");
				response.setError(null);
			}else {
				
				response.setResponse("No data found");
				response.setMessage("Failed");
				response.setError(null);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public CommonResponse getPurchaseReport(PurchaseReportReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<PurchaseMaster> list =query.getPurchaseReport(req);
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("SerialNo", p.getSerialNo().toString());
					map.put("CategoryId", p.getCategoryId().toString());
					map.put("BillRefNo", p.getBillRefNo());
					map.put("SupplierName", p.getSupplierName());
					map.put("AmountBeforeTax", p.getAmountBeforetax().toString());
					map.put("Sgst", p.getSgst().toString());
					map.put("Cgst", p.getCgst().toString());
					map.put("SgstTax", p.getSgstTax().toString());
					map.put("cgstTax", p.getCgstTax().toString());
					map.put("TotalAmount", p.getTotalAmount().toString());
					map.put("BillReferenceDate", sdf.format(p.getBillDate()));
					
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

	@Override
	public CommonResponse getOrderReport(OrderReportReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<Tuple> list =query.getOrderReport(req);
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("OrderId", p.get("orderId")==null?"":p.get("orderId").toString());
					map.put("TotalChallan",p.get("totalChallan")==null?"":p.get("totalChallan").toString());
					map.put("TotalPieces", p.get("totalPieces")==null?"":p.get("totalPieces").toString());
					map.put("Status", p.get("statusDesc")==null?"":p.get("statusDesc").toString());
					map.put("CompanyId", p.get("companyId")==null?"":p.get("companyId").toString());
					map.put("ProductId", p.get("productId")==null?"":p.get("productId").toString());
					map.put("LotNumber",p.get("lotNumber")==null?"":p.get("lotNumber").toString());
					map.put("OrderDate", p.get("entryDate")==null?"":sdf.format(p.get("entryDate")));
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

	@Override
	public CommonResponse getExpensiveReport(ExpensiveReportRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			List<ExpensiveDetails> list =query.getExpensiveReport(req);
			if(!list.isEmpty()) {
				List<Map<String,String>> mapList = new ArrayList<>();
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("SerialNo", p.getSerialNo().toString());
					map.put("CategoryType", p.getCategoryType());
					map.put("AccountType", p.getAccountType());
					map.put("Amount", p.getAmount().toString());
					map.put("Notes", p.getNotes());
					map.put("ExpensiveDate",sdf.format(p.getExpeniveDate()));		
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
