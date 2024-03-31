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
import com.siddhartha.garments.repository.PurchaseRepository;
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
	
	@Autowired
	private PurchaseRepository purchaseRepo;
	
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public CommonResponse getWorkerReport(WorkerReportReq req) {
		CommonResponse response = new CommonResponse();
		try {
			Date startDate =sdf2.parse(req.getStartDate());
			Date EndDate =sdf2.parse(req.getEndDate());
			String start_date =sdf3.format(startDate);
			String end_date =sdf3.format(EndDate);
			List<Map<String,Object>> list =null;
			List<Map<String,String>> mapList =new ArrayList<>();
			if("ALL".equalsIgnoreCase(req.getOperatorId())) {
			
				 list = workerEntryRepository.getWorkerReport(start_date,end_date);
			}else {
				
				list = workerEntryRepository.getWorkerReport(start_date,end_date,req.getOperatorId());
			}
			
			if(list!=null) {
				list.forEach(p ->{
					Map<String,String> map = new HashMap<String, String>();
					map.put("CompanyName", p.get("company_name")==null?"":p.get("company_name").toString());
					map.put("ProductName",p.get("product_name")==null?"":p.get("product_name").toString());
					map.put("OrderId", p.get("order_id")==null?"":p.get("order_id").toString());
					map.put("LotNumber", p.get("lot_number")==null?"":p.get("lot_number").toString());
					map.put("ChallanNo", p.get("challan_no")==null?"":p.get("challan_no").toString());
					map.put("ColorName", p.get("color_name")==null?"":p.get("color_name").toString());
					map.put("TotalPieces", p.get("total_pieces")==null?"":p.get("total_pieces").toString());
					map.put("EmployeeWorkedPieces",p.get("employee_worked_pieces")==null?"":p.get("employee_worked_pieces").toString());
					map.put("EmployeeFees", p.get("total_pieces")==null?"":p.get("total_pieces").toString());
					map.put("SectionName", p.get("operator_name")==null?"":p.get("operator_name").toString());
					map.put("OperatorName", p.get("section_name")==null?"":p.get("section_name").toString());
					mapList.add(map);
				});
				response.setResponse(mapList);
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
			Date startDate =sdf2.parse(req.getStartDate());
			Date EndDate =sdf2.parse(req.getEndDate());
			String start_date =sdf3.format(startDate);
			String end_date =sdf3.format(EndDate);
			List<Map<String,Object>> list =null;
			List<Map<String,String>> mapList =new ArrayList<>();
			if("ALL".equalsIgnoreCase(req.getCategoryType())) {
				list =purchaseRepo.getPurchaseReport(start_date,end_date);
			}else {
				list =purchaseRepo.getPurchaseReport(start_date,end_date,req.getCategoryType());
			}
			
			if(list!=null) {
				list.forEach(p ->{
					HashMap<String, String> map = new HashMap<String, String>();
					//map.put("SerialNo",p.get("")==null?"":p.get("").toString());
					map.put("CategoryId", p.get("bill_type")==null?"":p.get("bill_type").toString());
					map.put("BillRefNo", p.get("bill_ref_no")==null?"":p.get("bill_ref_no").toString());
					map.put("SupplierName", p.get("supplier_name")==null?"":p.get("supplier_name").toString());
					map.put("AmountBeforeTax",p.get("amount_before_tax")==null?"":p.get("amount_before_tax").toString());
					map.put("SgstTax", p.get("sgst_tax")==null?"":p.get("sgst_tax").toString());
					map.put("cgstTax", p.get("cgst_tax")==null?"":p.get("cgst_tax").toString());
					map.put("TotalAmount",p.get("total_amount")==null?"":p.get("total_amount").toString());
					map.put("BillReferenceDate", p.get("bill_date")==null?"":p.get("bill_date").toString());
					mapList.add(map);
				});
				response.setResponse(mapList);
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
