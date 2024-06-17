package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.dto.InsertSizeCalcRequest;
import com.siddhartha.garments.dto.OrderChallanColorReq;
import com.siddhartha.garments.dto.OrderChallanInfoReq;
import com.siddhartha.garments.dto.OrderDetailsRequest;
import com.siddhartha.garments.dto.ProductSizeColorRequest;
import com.siddhartha.garments.dto.ProductSizeMasterReq;
import com.siddhartha.garments.dto.ProductSizeMetalReq;
import com.siddhartha.garments.dto.SaveProductSizeColorMetalReq;
import com.siddhartha.garments.dto.SizeFoldingDetailsReq;
import com.siddhartha.garments.entity.CompanyMeterialMaster;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.entity.WorkerEntryDetails;
import com.siddhartha.garments.repository.CompanyMeterialMasterRepository;
import com.siddhartha.garments.repository.LoginMasterRepository;
import com.siddhartha.garments.repository.WorkerEntryRepository;
import com.siddhartha.garments.request.ChallanDetailsInfo;
import com.siddhartha.garments.request.ChallanInfoRequest;
import com.siddhartha.garments.request.ColorDetailsRequest;
import com.siddhartha.garments.request.ColorFoldingDetailsReq;
import com.siddhartha.garments.request.ColorInfoReq;
import com.siddhartha.garments.request.ColorSaveRequest;
import com.siddhartha.garments.request.CompanyMasterRequest;
import com.siddhartha.garments.request.CompanyMeterialMasterReq;
import com.siddhartha.garments.request.CompanyProductRequest;
import com.siddhartha.garments.request.DisrictRequest;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.ExpensiveRequest;
import com.siddhartha.garments.request.InserSizeColorRequest;
import com.siddhartha.garments.request.LoginRequest;
import com.siddhartha.garments.request.LotDetailsRequest;
import com.siddhartha.garments.request.MeterialDetailsRequest;
import com.siddhartha.garments.request.MeterialInfoReq;
import com.siddhartha.garments.request.MeterialSetupRequest;
import com.siddhartha.garments.request.OperatorSaveRequest;
import com.siddhartha.garments.request.ProductSaveRequest;
import com.siddhartha.garments.request.ProductStyleMasterRequest;
import com.siddhartha.garments.request.PurchaseRequest;
import com.siddhartha.garments.request.SectionSaveRequest;
import com.siddhartha.garments.request.SizeMasterRequest;
import com.siddhartha.garments.request.StateSaveRequest;
import com.siddhartha.garments.request.UserDetailsRequest;
import com.siddhartha.garments.request.UserTypeRequest;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.OrderBillingRequest;
import com.siddhartha.garments.response.ProductMetalReq;

@Component
public class InputValidationServiceImpl {
	
	
	@Autowired
	private LoginMasterRepository loginMasterRepository;
	
	@Autowired
	private CompanyMeterialMasterRepository cmmRepository;
	
	@Autowired
	private WorkerEntryRepository workerEntryRepo;
	
	@Autowired
	private AsyncProcessServiceImpl asyncProcess;
	
	
	public List<ErrorList> validateLoginRequest(LoginRequest req){
		List<ErrorList> errorList = new ArrayList<ErrorList>();
		try {
			if(StringUtils.isBlank(req.getUserName())) {
				errorList.add(new ErrorList("UserName","500","Please enter username"));
			}if(StringUtils.isBlank(req.getPassword())) {
				errorList.add(new ErrorList("Password","500","Please enter password"));

			}
			if(StringUtils.isNotBlank(req.getUserName()) && StringUtils.isNotBlank(req.getPassword())) {
				
				Encoder encoder =Base64.getEncoder();
				
				String paswd =encoder.encodeToString(req.getPassword().getBytes());
				
				LoginMaster loginMaster =loginMasterRepository.findByLoginIdIgnoreCaseAndPassword(req.getUserName(), paswd);
				
				if(loginMaster==null) {
					errorList.add(new ErrorList("User","401","Please enter valid username/password"));
				}else {
					
					String status =loginMaster.getStatus();
					if("N".equalsIgnoreCase(status)) {
						errorList.add(new ErrorList("User","401","You were blocked by admin..Contact Admin...!"));
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return errorList;
		
	}
	
	public List<ErrorList> validateLotInfo(LotDetailsRequest req){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(StringUtils.isBlank(req.getAddress())) {
				errorLists.add(new ErrorList("Address","101","Please enter Address"));
			}
			if(StringUtils.isBlank(req.getCity())) {
				errorLists.add(new ErrorList("City","102","Please enter City"));
			}
			if(StringUtils.isBlank(req.getCompanyName())) {
				errorLists.add(new ErrorList("CompanyName","103","Please enter CompanyName"));
			}
			if(StringUtils.isBlank(req.getDistrictCode())) {
				errorLists.add(new ErrorList("District","104","Please choose your District"));
			}
			if(StringUtils.isBlank(req.getGstNo())) {
				errorLists.add(new ErrorList("GstNo","105","Please enter Gst Number"));
			}
			if(StringUtils.isBlank(req.getInwardDate())) {
				errorLists.add(new ErrorList("InwardDate","106","Please enter InwardDate"));
			}
			if(StringUtils.isBlank(req.getLotNo())) {
				errorLists.add(new ErrorList("LotNo","107","Please enter Lot Number"));
			}
			if(StringUtils.isBlank(req.getPhoneNo())) {
				errorLists.add(new ErrorList("PhoneNo","108","Please enter Phone Number"));
			}
			if(StringUtils.isBlank(req.getCompanyId())) {
				errorLists.add(new ErrorList("CompanyId","109","Please choose your CompanyId"));
			}
			if(StringUtils.isBlank(req.getItemId())) {
				errorLists.add(new ErrorList("ItemId","109","Please choose your ItemId"));
			}
			if(StringUtils.isBlank(req.getCategoryId())) {
				errorLists.add(new ErrorList("CategoryId","109","Please choose your CategoryId"));
			}
			if(StringUtils.isBlank(req.getStateCode())) {
				errorLists.add(new ErrorList("StateCode","110","Please choose your State"));
			}
			if(StringUtils.isBlank(req.getUpdatedBy())) {
				errorLists.add(new ErrorList("UpdatedBy","111","Please enter UpdatedBy"));
			}
			
			if(req.getChallanInfo().isEmpty()) {
				errorLists.add(new ErrorList("Challan","112","Please enter your challan details"));
			}else {
				
				int row =1;
				for(ChallanInfoRequest cha : req.getChallanInfo()) {
					
					if(StringUtils.isBlank(cha.getChallanDate())) {
						errorLists.add(new ErrorList("ChallanDate",""+row+"","Please enter ChallanDate"));
					}
					if(StringUtils.isBlank(cha.getChallanNo())) {
						errorLists.add(new ErrorList("ChallanNo",""+row+"","Please enter ChallanNo"));
					}
					if(StringUtils.isBlank(cha.getPieceYn())) {
						errorLists.add(new ErrorList("PieceYn",""+row+"","Please select PieceYn"));
					}
					if(StringUtils.isBlank(cha.getQuality())) {
						errorLists.add(new ErrorList("Quality",""+row+"","Please enter Quality"));
					}
					if(StringUtils.isBlank(cha.getSize())) {
						errorLists.add(new ErrorList("Size",""+row+"","Please enter Size"));
					}
					if(StringUtils.isBlank(cha.getTotalPieces())) {
						errorLists.add(new ErrorList("TotalPieces",""+row+"","Please enter TotalPieces"));
					}
					
					row++;
					
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return errorLists;
	}
	
	public List<ErrorList> validateColorInfo(ColorDetailsRequest req){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(req.getColorInfo().isEmpty()) {
				errorLists.add(new ErrorList("Color","112","Please enter your color details"));
			}else {
				
				int row =1;
				for(ColorInfoReq col : req.getColorInfo()) {
					
					if(StringUtils.isBlank(col.getColorCode())) {
						errorLists.add(new ErrorList("ColorCode",""+row+"","Please choose your color"));
					}
					if(StringUtils.isBlank(col.getNoOfBundles())) {
						errorLists.add(new ErrorList("NoOfBundles",""+row+"","Please enter NoOfBundles"));
					}
					if(StringUtils.isBlank(col.getPiecesCount())) {
						errorLists.add(new ErrorList("PiecesCount",""+row+"","Please enter PiecesCount"));
					}
					if(StringUtils.isBlank(col.getReceivedElastic())) {
						errorLists.add(new ErrorList("ReceivedElastic",""+row+"","Please enter ReceivedElastic"));
					}
					if(StringUtils.isBlank(col.getRequiredElastic())) {
						errorLists.add(new ErrorList("RequiredElastic",""+row+"","Please enter RequiredElastic"));
					}
					if(StringUtils.isBlank(col.getRequiredFoldingWeight())) {
						errorLists.add(new ErrorList("RequiredFoldingWeight",""+row+"","Please enter RequiredFoldingWeight"));
					}
					if(StringUtils.isBlank(col.getTotalDozen())) {
						errorLists.add(new ErrorList("TotalDozen",""+row+"","Please enter TotalDozen"));
					}
					if(StringUtils.isBlank(col.getReceivedFoldingWeigth())) {
						errorLists.add(new ErrorList("ReceivedFoldingWeigth",""+row+"","Please enter ReceivedFoldingWeigth"));
					}
					
					row++;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return errorLists;
	}
	
	public List<ErrorList> validateMeterialInfo(MeterialDetailsRequest req){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(req.getMeterialInfo().isEmpty()) {
				errorLists.add(new ErrorList("Meterial", "500", "Please enter Material Details"));
			}else {
				int row =1;
				for(MeterialInfoReq met :req.getMeterialInfo()) {
					
					if(StringUtils.isBlank(met.getBodySticker())) {
						errorLists.add(new ErrorList("BodySticker",""+row+"","Please enter BodySticker"));
					}
					if(StringUtils.isBlank(met.getHoloGramSticker())) {
						errorLists.add(new ErrorList("HoloGramSticker",""+row+"","Please enter HoloGramSticker"));
					}
					if(StringUtils.isBlank(met.getInnercard())) {
						errorLists.add(new ErrorList("Innercard",""+row+"","Please enter Innercard"));
					}
					if(StringUtils.isBlank(met.getLable())) {
						errorLists.add(new ErrorList("Lable",""+row+"","Please enter Lable"));
					}
					if(StringUtils.isBlank(met.getMasterBox())) {
						errorLists.add(new ErrorList("MasterBox",""+row+"","Please enter MasterBox"));
					}
					if(StringUtils.isBlank(met.getMetalType())) {
						errorLists.add(new ErrorList("MetalType",""+row+"","Please enter MetalType"));
					}
					if(StringUtils.isBlank(met.getMrpSticker())) {
						errorLists.add(new ErrorList("MrpSticker",""+row+"","Please enter MrpSticker"));
					}
					if(StringUtils.isBlank(met.getPolyBag())) {
						errorLists.add(new ErrorList("PolyBag",""+row+"","Please enter PolyBag"));
					}
					if(StringUtils.isBlank(met.getSingleBox())) {
						errorLists.add(new ErrorList("SingleBox",""+row+"","Please enter SingleBox"));
					}
					if(StringUtils.isBlank(met.getSizeSticker())) {
						errorLists.add(new ErrorList("SizeSticker",""+row+"","Please enter SizeSticker"));
					}
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return errorLists;
	}
	
	
	public List<ErrorList> validateUserRequest(UserDetailsRequest req){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(StringUtils.isBlank(req.getAadharNo())) {
				errorLists.add(new ErrorList("AadharNo","450","Please enter AadharNo"));
			}
			if(StringUtils.isBlank(req.getAddress())) {
				errorLists.add(new ErrorList("Address","450","Please enter Address"));
			}
			if(StringUtils.isBlank(req.getCity())) {
				errorLists.add(new ErrorList("City","450","Please enter City"));
			}
			if(StringUtils.isBlank(req.getCreatedBy())) {
				errorLists.add(new ErrorList("CreatedBy","450","Please enter CreatedBy"));
			}
			if(StringUtils.isBlank(req.getDateOfBirth())) {
				errorLists.add(new ErrorList("DateOfBirth","450","Please enter DateOfBirth"));
			}
			if(StringUtils.isBlank(req.getDistrictCode())) {
				errorLists.add(new ErrorList("District","450","Please choose your District"));
			}
			if(StringUtils.isBlank(req.getEmail())) {
				errorLists.add(new ErrorList("Email","450","Please enter Email"));
			}
			if(StringUtils.isBlank(req.getFirstname())) {
				errorLists.add(new ErrorList("Firstname","450","Please enter Firstname"));
			}
			if(StringUtils.isBlank(req.getLastName())) {
				errorLists.add(new ErrorList("LastName","450","Please enter LastName"));
			}
			if(StringUtils.isBlank(req.getMobileNo())) {
				errorLists.add(new ErrorList("MobileNo","450","Please enter MobileNo"));
			}
			
			if(StringUtils.isBlank(req.getUserType())) {
				errorLists.add(new ErrorList("UserType","450","Please enter UserType"));
			}
			if(StringUtils.isBlank(req.getStateCode())) {
				errorLists.add(new ErrorList("State","450","Please choose your State"));
			}
			
			
			if(!"Operator".equalsIgnoreCase(req.getUserType())) {
				if(StringUtils.isBlank(req.getPassword())) {
					errorLists.add(new ErrorList("Password","450","Please enter Password"));
				}
				if(StringUtils.isBlank(req.getUserName())) {
					errorLists.add(new ErrorList("UserName","450","Please enter UserName"));
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return errorLists;
	}
	
	public List<ErrorList> validateOperator(OperatorSaveRequest req){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(StringUtils.isBlank(req.getAadharNo())) {
				errorLists.add(new ErrorList("AadharNo","450","Please enter AadharNo"));
			}
			if(StringUtils.isBlank(req.getAdress())) {
				errorLists.add(new ErrorList("Address","450","Please enter Address"));
			}
			if(StringUtils.isBlank(req.getCity())) {
				errorLists.add(new ErrorList("City","450","Please enter City"));
			}
			if(StringUtils.isBlank(req.getCreatedBy())) {
				errorLists.add(new ErrorList("CreatedBy","450","Please enter CreatedBy"));
			}
			if(StringUtils.isBlank(req.getDateOfBirth())) {
				errorLists.add(new ErrorList("DateOfBirth","450","Please enter DateOfBirth"));
			}
			if(StringUtils.isBlank(req.getDistrictCode())) {
				errorLists.add(new ErrorList("District","450","Please choose your District"));
			}
			if(StringUtils.isBlank(req.getEmail())) {
				errorLists.add(new ErrorList("Email","450","Please enter Email"));
			}
			if(StringUtils.isBlank(req.getFirstname())) {
				errorLists.add(new ErrorList("Firstname","450","Please enter Firstname"));
			}
			if(StringUtils.isBlank(req.getLastName())) {
				errorLists.add(new ErrorList("LastName","450","Please enter LastName"));
			}
			if(StringUtils.isBlank(req.getMobileNo())) {
				errorLists.add(new ErrorList("MobileNo","450","Please enter MobileNo"));
			}
			if(StringUtils.isBlank(req.getStateCode())) {
				errorLists.add(new ErrorList("State","450","Please enter State"));
			}
			if(StringUtils.isBlank(req.getDistrictCode())) {
				errorLists.add(new ErrorList("District","450","Please enter District"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return errorLists;
	}
	
	public List<ErrorList> validateMasterInfo(Object req,String type){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if("DISTRICT".equalsIgnoreCase(type)) {
				
				DisrictRequest d =(DisrictRequest)req;
				
				if(StringUtils.isBlank(d.getStateCode())) {
					errorLists.add(new ErrorList("State","450","Please enter State"));
				}
				if(StringUtils.isBlank(d.getDistrictName())) {
					errorLists.add(new ErrorList("DistrictName","450","Please enter DistrictName"));
				}
				if(StringUtils.isBlank(d.getStatus())) {
					errorLists.add(new ErrorList("Status","450","Please enter Status"));
				}
					
			}else if("COLOR".equalsIgnoreCase(type)) {
				
				ColorSaveRequest d =(ColorSaveRequest)req;
				
				if(StringUtils.isBlank(d.getStatus())) {
					errorLists.add(new ErrorList("Status","450","Please enter Status"));
				}
				if(StringUtils.isBlank(d.getColorName())) {
					errorLists.add(new ErrorList("ColorName","450","Please enter ColorName"));
				}
					
					
			}else if("PRODUCT".equalsIgnoreCase(type)) {
				
				ProductSaveRequest d =(ProductSaveRequest)req;
				
				if(StringUtils.isBlank(d.getStatus())) {
					errorLists.add(new ErrorList("Status","450","Please enter Status"));
				}
				if(StringUtils.isBlank(d.getProductName())) {
					errorLists.add(new ErrorList("ProductName","450","Please enter ProductName"));
				}
					
				
			}else if("SIZE".equalsIgnoreCase(type)) {
				
				SizeMasterRequest d =(SizeMasterRequest)req;
				
				if(StringUtils.isBlank(d.getStatus())) {
					errorLists.add(new ErrorList("Status","450","Please enter Status"));
				}
				if(StringUtils.isBlank(d.getSize())) {
					errorLists.add(new ErrorList("Size","450","Please enter Size"));
				}
					
				
			}else if("SECTION".equalsIgnoreCase(type)) {
				
				SectionSaveRequest d =(SectionSaveRequest)req;
				
				if(StringUtils.isBlank(d.getStatus())) {
					errorLists.add(new ErrorList("Status","450","Please enter Status"));
				}
				if(StringUtils.isBlank(d.getSectionName())) {
					errorLists.add(new ErrorList("SectionName","450","Please enter SectionName"));
				}
				
				
			}else if("STATE".equalsIgnoreCase(type)) {
				
				StateSaveRequest d =(StateSaveRequest)req;
				
				if(StringUtils.isBlank(d.getStatus())) {
					errorLists.add(new ErrorList("Status","450","Please enter Status"));
				}
				if(StringUtils.isBlank(d.getStateName())) {
					errorLists.add(new ErrorList("StateName","450","Please enter StateName"));
				}
				
			}else if("USERTYPE".equalsIgnoreCase(type)) {
				
				UserTypeRequest d =(UserTypeRequest)req;
				
				if(StringUtils.isBlank(d.getStatus())) {
					errorLists.add(new ErrorList("Status","450","Please enter Status"));
				}
				if(StringUtils.isBlank(d.getUserTypeName())) {
					errorLists.add(new ErrorList("UserTypeName","450","Please enter UserTypeName"));
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return errorLists;
	}

	public List<ErrorList> validateWorkerEntry(WorkerEntryDetailsReq d) {
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(StringUtils.isBlank(d.getChallanId())) {
				errorLists.add(new ErrorList("Size","450","Please choose size"));
			}
			
			if(StringUtils.isBlank(d.getSize())) {
				errorLists.add(new ErrorList("Size","450","Please enter size"));
			}
			
			
			if(StringUtils.isBlank(d.getColorId())) {
				errorLists.add(new ErrorList("Color","450","Please choose color"));
			}
			
			if(StringUtils.isBlank(d.getColorName())) {
				errorLists.add(new ErrorList("ColorName","450","Please choose ColorName"));
			}
			
			if(StringUtils.isBlank(d.getSectionId())) {
				errorLists.add(new ErrorList("Section","450","Please choose section"));
			}
			
			if(StringUtils.isBlank(d.getSectionName())) {
				errorLists.add(new ErrorList("SectionName","450","Please enter SectionName"));
			}
			
			if(StringUtils.isBlank(d.getEmployeeWorkedPieces())) {
				errorLists.add(new ErrorList("EmployeeWorkedPieces","450","Please enter EmployeeWorkedPieces"));
			}else if(!NumberUtils.isCreatable(d.getEmployeeWorkedPieces())) {
				errorLists.add(new ErrorList("EmployeeWorkedPieces","450","EmployeeWorkedPieces allows only digits"));
			}
			
			if(StringUtils.isBlank(d.getOperatorId())) {
				errorLists.add(new ErrorList("Operator","450","Please choose operator"));
			}
			if(StringUtils.isBlank(d.getOperatorName())) {
				errorLists.add(new ErrorList("OperatorName","450","Please choose OperatorName"));
			}
			
			if(StringUtils.isBlank(d.getUpdatedBy())) {
				errorLists.add(new ErrorList("UpdatedBy","450","Please enter UpdatedBy"));
			}
			
			if(StringUtils.isBlank(d.getTotalPieces())) {
				errorLists.add(new ErrorList("TotalPieces","450","Please enter TotalPieces"));
			}else if(!NumberUtils.isCreatable(d.getTotalPieces())) {
				errorLists.add(new ErrorList("TotalPieces","450","TotalPieces allows only digits"));
			}
			
			if(StringUtils.isBlank(d.getOrderId())) {
				errorLists.add(new ErrorList("Order","450","Please enter OrderId"));
			}else {
				Long count =workerEntryRepo.checkDuplicateEntry(d.getOrderId(),d.getChallanId(),d.getColorId(),d.getSectionId(),d.getOperatorId());
				if(count>0) {
					errorLists.add(new ErrorList("Worker","450","You have already put entry for today.You cannot make another entry for today. if you want update ans existing data for today .. Contact Admin....!"));
				}
			}
			
			if(StringUtils.isBlank(d.getLotNumber())) {
				errorLists.add(new ErrorList("LotNumber","450","Please choose LotNumber"));
			}
			
			
			if(StringUtils.isNotEmpty(d.getEmployeeWorkedPieces()) && StringUtils.isNotEmpty(d.getTotalPieces())
					&& NumberUtils.isCreatable(d.getEmployeeWorkedPieces()) &&  NumberUtils.isCreatable(d.getTotalPieces())
					) {
							
					  Long employeeWorkedPieces =Long.valueOf(d.getEmployeeWorkedPieces());
					  Long totalPieces =Long.valueOf(d.getTotalPieces());
					  
					  if(employeeWorkedPieces>totalPieces) {
							errorLists.add(new ErrorList("Error","450","EmployeeWorkedPieces should not greater than total pieces"));

					  }
					  List<WorkerEntryDetails> workerList =workerEntryRepo.findByOrderIdAndChallanIdAndColorId(d.getOrderId(),d.getChallanId(),d.getColorId());
					  
					  Long existingPieces =workerList.stream()
							  .map(p ->Long.valueOf(p.getEmployeeWorkedPieces())
							  ).collect(Collectors.summingLong(k ->k)) ;
					  
					  Long total_existing_pieces = existingPieces + employeeWorkedPieces;
					  
					  if(total_existing_pieces > totalPieces) {
						  
							errorLists.add(new ErrorList("Error","450","This slot limit has exceed .. Already "+existingPieces+""
									+ " pieces are there and you are trying to enter "+employeeWorkedPieces+" this slot total color pieces are "+totalPieces+""));

					  }
					  
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return errorLists;
	}

	public List<ErrorList>  validateMeterialSetup(MeterialSetupRequest req) {
		List<ErrorList> errorLists = new ArrayList<>();
		
		if(StringUtils.isBlank(req.getProductId())) {
			errorLists.add(new ErrorList("Product","450","Please choose product"));
		}
		if(StringUtils.isBlank(req.getTableColumn())) {
			errorLists.add(new ErrorList("TableColumn","450","Please choose TableColumn"));
		}
		if(StringUtils.isBlank(req.getQuantityType())) {
			errorLists.add(new ErrorList("QuantityType","450","Please choose QuantityType"));
		}
		if(StringUtils.isBlank(req.getDisplayOrder())) {
			errorLists.add(new ErrorList("DisplayOrder","450","Please choose DisplayOrder"));
		}
		if(StringUtils.isBlank(req.getDisplayColumn())) {
			errorLists.add(new ErrorList("DisplayColumn","450","Please choose DisplayColumn"));
		}
		if(req.getQuantityType().equals("P")) {
			
			if("P".equals(req.getCalcType())) {
				
				if(StringUtils.isBlank(req.getCalcPercentage())) {
					errorLists.add(new ErrorList("CalcPercentage","450","Please enter CalcPercentage"));
				}
	
			}else if("A".equals(req.getCalcAmount())) {
				
				if(StringUtils.isBlank(req.getCalcAmount())) {
					errorLists.add(new ErrorList("No Of Pieces","450","Please enter Number Of Pieces"));
				}
			}
		}else if("B".equalsIgnoreCase(req.getQuantityType())){
			
			if(StringUtils.isBlank(req.getBoxPieceCount())) {
				errorLists.add(new ErrorList("BoxPiecesCount","450","Please choose BoxPiecesCount"));
			}
		}
		
		return errorLists;
	}
	
	public List<ErrorList> validateExpensive(ExpensiveRequest req){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(StringUtils.isBlank(req.getExpensiveDate())) {
				errorLists.add(new ErrorList("ExpensiveDate","101","Please enter ExpensiveDate"));
			}
			if(StringUtils.isBlank(req.getAccountType())) {
				errorLists.add(new ErrorList("AccountType","102","Please enter AccountType"));
			}
			if(StringUtils.isBlank(req.getAmount())) {
				errorLists.add(new ErrorList("Amount","103","Please enter Amount"));
			}
			if(StringUtils.isBlank(req.getNotes())) {
				errorLists.add(new ErrorList("Notes","104","Please choose your Notes"));
			}
			if(StringUtils.isBlank(req.getStatus())) {
				errorLists.add(new ErrorList("Status","105","Please enter Gst Status"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return errorLists;
	}
	
	public List<ErrorList> validatePurchase(PurchaseRequest req){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(StringUtils.isBlank(req.getAmountBeforeTax())) {
				errorLists.add(new ErrorList("AmountBeforeTax","101","Please enter AmountBeforeTax"));
			}
			if(StringUtils.isBlank(req.getBillDate())) {
				errorLists.add(new ErrorList("BillDate","102","Please enter BillDate"));
			}
			if(StringUtils.isBlank(req.getBillRefNo())) {
				errorLists.add(new ErrorList("BillRefNo","103","Please enter BillRefNo"));
			}
			if(StringUtils.isBlank(req.getCategoryId())) {
				errorLists.add(new ErrorList("CategoryId","104","Please choose your CategoryId"));
			}
			if(StringUtils.isBlank(req.getCGSTTax())) {
				errorLists.add(new ErrorList("CGSTTax","105","Please enter Gst CGSTTax"));
			}
			if(StringUtils.isBlank(req.getProductDesc())) {
				errorLists.add(new ErrorList("ProductDesc","106","Please enter ProductDesc"));
			}
			if(StringUtils.isBlank(req.getSgstTax())) {
				errorLists.add(new ErrorList("SgstTax","107","Please enter Lot SgstTax"));
			}
			if(StringUtils.isBlank(req.getSupplierName())) {
				errorLists.add(new ErrorList("SupplierName","108","Please enterSupplierNamer"));
			}
			if(StringUtils.isBlank(req.getStatus())) {
				errorLists.add(new ErrorList("Status","108","Please Status"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return errorLists;
	}

	public List<ErrorList> company(CompanyMasterRequest req) {
		List<ErrorList> errorLists = new ArrayList<>();
		
		if(StringUtils.isBlank(req.getAddress())) {
			errorLists.add(new ErrorList("Address","101","Please enter Address"));
		}
		if(StringUtils.isBlank(req.getCompanyName())) {
			errorLists.add(new ErrorList("CompanyName","103","Please enter CompanyName"));
		}
		if(StringUtils.isBlank(req.getDistrict())) {
			errorLists.add(new ErrorList("District","104","Please choose your District"));
		}
		if(StringUtils.isBlank(req.getGstNumber())) {
			errorLists.add(new ErrorList("GstNo","105","Please enter Gst Number"));
		}
		if(StringUtils.isBlank(req.getMobileNo())) {
			errorLists.add(new ErrorList("MobileNo","108","Please enter Mobile Number"));
		}else if(!StringUtils.isNumeric(req.getMobileNo())) {
			errorLists.add(new ErrorList("MobileNo","108","MobileNumber allows only digits"));
		}
		if(StringUtils.isBlank(req.getState())) {
			errorLists.add(new ErrorList("StateCode","110","Please choose your State"));
		}
		if(StringUtils.isBlank(req.getStatus())) {
			errorLists.add(new ErrorList("Status","110","Please choose your Status"));
		}
		
		return errorLists;
	}

	public List<ErrorList> product(CompanyProductRequest req) {
		List<ErrorList> errorLists = new ArrayList<>();
		
		if(StringUtils.isBlank(req.getCompanyId())) {
			errorLists.add(new ErrorList("CompanyId","101","Please enter CompanyId"));
		}
		if(StringUtils.isBlank(req.getCreatedBy())) {
			errorLists.add(new ErrorList("CreatedBy","103","Please enter CreatedBy"));
		}
		if(StringUtils.isBlank(req.getProductName())) {
			errorLists.add(new ErrorList("ProductName","110","Please enter your ProductName"));
		}
		if(StringUtils.isBlank(req.getStatus())) {
			errorLists.add(new ErrorList("Status","110","Please choose your Status"));
		}
		
		
		
		return errorLists;
	}

	public List<ErrorList> style(ProductStyleMasterRequest req) {
		List<ErrorList> errorLists = new ArrayList<>();
		
		if(StringUtils.isBlank(req.getCompanyId())) {
			errorLists.add(new ErrorList("CompanyId","101","Please enter CompanyId"));
		}
		if(StringUtils.isBlank(req.getCreatedBy())) {
			errorLists.add(new ErrorList("CreatedBy","103","Please enter CreatedBy"));
		}
		if(StringUtils.isBlank(req.getProductId())) {
			errorLists.add(new ErrorList("ProductId","110","Please enter your ProductId"));
		}
		if(StringUtils.isBlank(req.getStatus())) {
			errorLists.add(new ErrorList("Status","110","Please choose your Status"));
		}
		if(StringUtils.isBlank(req.getStyleName())) {
			errorLists.add(new ErrorList("StyleName","110","Please enter your StyleName"));
		}
		
		return errorLists;
	}

	public List<ErrorList> validateOrder(OrderDetailsRequest req) {
		List<ErrorList> errorLists = new ArrayList<>();
		
		if(StringUtils.isBlank(req.getCompanyId())) {
			errorLists.add(new ErrorList("CompanyId","101","Please enter CompanyId"));
		}
		
		if(StringUtils.isBlank(req.getProductId())) {
			errorLists.add(new ErrorList("ProductId","101","Please enter ProductId"));
		}
		
		
		if(StringUtils.isBlank(req.getInwardDate())) {
			errorLists.add(new ErrorList("InwardDate","101","Please enter InwardDate"));
		}
		
		if(StringUtils.isBlank(req.getLotNumber())) {
			errorLists.add(new ErrorList("LotNumber","101","Please enter LotNumber"));
		}
		
		if(StringUtils.isBlank(req.getStatus())) {
			errorLists.add(new ErrorList("Status","101","Please enter Status"));
		}
		
		if(req.getOrderChallanInfo().isEmpty()) {
			errorLists.add(new ErrorList("ChallanInfo","101","Please enter ChallanInfo"));
		}else {
			
			int rowno =1;
			for(OrderChallanInfoReq r :req.getOrderChallanInfo()) {
				
				if(StringUtils.isBlank(r.getChallanDate())) {
					errorLists.add(new ErrorList("ChallanDate","Row : "+rowno+"","Please enter ChallanDate"));
				}
				if(StringUtils.isBlank(r.getChallanNumber())) {
					errorLists.add(new ErrorList("ChallanNumber","Row : "+rowno+"","Please enter ChallanNumber"));
				}else if(!StringUtils.isNumeric(r.getChallanNumber())) {
					errorLists.add(new ErrorList("ChallanNumber","Row : "+rowno+"","ChallanNumber allows only digits"));

				}
				if(StringUtils.isBlank(r.getSize())) {
					errorLists.add(new ErrorList("Size","Row : "+rowno+"","Please enter Size"));
				}
				if(StringUtils.isBlank(r.getTotalPieces())) {
					errorLists.add(new ErrorList("TotalPieces","Row : "+rowno+"","Please enter TotalPieces"));
				}else if(!StringUtils.isNumeric(r.getTotalPieces())) {
					errorLists.add(new ErrorList("TotalPieces","Row : "+rowno+"","TotalPieces allows only digits"));

				}
				rowno++;
			}
			
		}
		
		
		
		return errorLists;
	}

	public List<ErrorList> orderColor(List<OrderChallanColorReq> req) {
		List<ErrorList> errorLists = new ArrayList<>();
		if(req.isEmpty()) {
			errorLists.add(new ErrorList("ColorInfo","101","Please enter ColorInfo"));
		}else {
			int rowno =1;
			for(OrderChallanColorReq r : req) {
				
				if(StringUtils.isBlank(r.getColorCode())) {
					errorLists.add(new ErrorList("ColorCode","Row : "+rowno+"","Please enter ColorCode"));
				}
				if(StringUtils.isBlank(r.getColorName())) {
					errorLists.add(new ErrorList("ColorName","Row : "+rowno+"","Please enter ColorName"));
				}
				if(StringUtils.isBlank(r.getTotalPieces())) {
					errorLists.add(new ErrorList("TotalPieces","Row : "+rowno+"","Please enter TotalPieces"));
				}
				else if(!StringUtils.isNumeric(r.getTotalPieces())) {
					errorLists.add(new ErrorList("TotalPieces","Row : "+rowno+"","TotalPieces allows only digits"));
				}
				
				rowno++;
			}
		}
		return errorLists;
	}

	public List<ErrorList> size(List<ProductSizeMasterReq> req) {
		List<ErrorList> errorLists = new ArrayList<>();
		if(req.isEmpty()) {
			errorLists.add(new ErrorList("ColorInfo","101","Please enter SizeInfo"));
		}else {
			int rowno =1;
			for(ProductSizeMasterReq r : req) {
				
				if(StringUtils.isBlank(r.getSize())) {
					errorLists.add(new ErrorList("Size","Row : "+rowno+"","Please enter Size"));
				}else if(!StringUtils.isNumeric(r.getSize())) {
					errorLists.add(new ErrorList("Size","Row : "+rowno+"","Please enter digits only"));
				}
				if(StringUtils.isBlank(r.getSizeId())) {
					//errorLists.add(new ErrorList("SizeId","Row : "+rowno+"","Please enter SizeId"));
				}
				
				if(StringUtils.isBlank(r.getStatus())) {
					errorLists.add(new ErrorList("Status","Row : "+rowno+"","Please enter Status"));
				}
				
				rowno++;
			}
			
			Map<String,List<ProductSizeMasterReq>> duplicateCheck =
					req.stream().collect(Collectors.groupingBy(p ->p.getSize()));
			
			for(Map.Entry<String,List<ProductSizeMasterReq>> entry :  duplicateCheck.entrySet()) {
				
				if(entry.getValue().size()>1) {
					
					errorLists.add(new ErrorList("Size","Row : "+rowno+"","Duplicate size does not allow :"+entry.getKey()+""));
					
					break ;
				}
			}
			
			
		}
		return errorLists;
	}

	public List<ErrorList> sizeMetal(List<ProductSizeMetalReq> req) {
		List<ErrorList> errorLists = new ArrayList<>();
		if(req.isEmpty()) {
			errorLists.add(new ErrorList("ColorInfo","101","Please enter SizeMetalInfo"));
		}else {
			int rowno =1;
			for(ProductSizeMetalReq r : req) {
				
				if(StringUtils.isBlank(r.getDisplayOrder())) {
					errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","Please enter DisplayOrder"));
				}else if(!r.getDisplayOrder().matches("[0-9]*")) {
					errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","DisplayOrder only allows digits"));
				}
				
				if(StringUtils.isBlank(r.getMesurementPieces())) {
					errorLists.add(new ErrorList("MesurementPieces","Row : "+rowno+"","Please enter MesurementPieces"));
				}else if(!r.getMesurementPieces().matches("[0-9]*")) {
					errorLists.add(new ErrorList("MesurementPieces","Row : "+rowno+"","MesurementPieces allows only digits"));
				}
				
				if(StringUtils.isBlank(r.getMesurementType())) {
					errorLists.add(new ErrorList("MesurementType","Row : "+rowno+"","Please enter MesurementType"));
				}
				
				if(StringUtils.isBlank(r.getMesurementValue())) {
					errorLists.add(new ErrorList("MesurementValue","Row : "+rowno+"","Please enter MesurementValue"));
				}else if(NumberUtils.isParsable(r.getMesurementValue())) {
					//errorLists.add(new ErrorList("MesurementValue","Row : "+rowno+"","MesurementValue allows only digits"));
				}
				
				if(StringUtils.isBlank(r.getMetalName())) {
					errorLists.add(new ErrorList("MetalName","Row : "+rowno+"","Please enter MetalName"));
				}
				
				if(StringUtils.isBlank(r.getSizeId())) {
					errorLists.add(new ErrorList("Size","Row : "+rowno+"","Please enter Size"));
				}
				
				if(StringUtils.isBlank(r.getCompanyId())) {
					errorLists.add(new ErrorList("CompanyId","Row : "+rowno+"","Please enter CompanyId"));
				}
				
				if(StringUtils.isBlank(r.getProductId())) {
					errorLists.add(new ErrorList("ProductId","Row : "+rowno+"","Please enter ProductId"));
				}
				
				
				if(StringUtils.isBlank(r.getStatus())) {
					errorLists.add(new ErrorList("Status","Row : "+rowno+"","Please enter Status"));
				}
				
				rowno++;
			}
			
			if(errorLists.isEmpty()) {
				
				Map<String, Long> displayOrder =req.stream()
						.collect(Collectors.groupingBy(p ->p.getDisplayOrder(),Collectors.counting()));
				
				for (Map.Entry<String, Long> entry : displayOrder.entrySet()) {
					
					if(entry.getValue()>1) {
						errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","Duplicate DisplayOrder does not allow"));
						break ;
					}
					
				}
				
			}
		}
		return errorLists;
	}

	public List<ErrorList> color(List<ProductSizeColorRequest> req) {
		List<ErrorList> errorLists = new ArrayList<>();
		if(req.isEmpty()) {
			errorLists.add(new ErrorList("ColorInfo","101","Please enter ColorInfo"));
		}else {
			int rowno =1;
			for(ProductSizeColorRequest r : req) {
				
				if(StringUtils.isBlank(r.getColorName())) {
					errorLists.add(new ErrorList("ColorName","Row : "+rowno+"","Please enter ColorName"));
				}
				
				
				if(StringUtils.isBlank(r.getProductId())) {
					errorLists.add(new ErrorList("ProductId","Row : "+rowno+"","Please enter ProductId"));
				}
				
				if(StringUtils.isBlank(r.getCompanyId())) {
					errorLists.add(new ErrorList("CompanyId","Row : "+rowno+"","Please enter CompanyId"));
				}
				
				if(StringUtils.isBlank(r.getStatus())) {
					errorLists.add(new ErrorList("Status","Row : "+rowno+"","Please enter Status"));
				}
				
				rowno++;
			}
		}
		return errorLists;
	}

	public List<ErrorList> colorMetal(List<SaveProductSizeColorMetalReq> req) {
		List<ErrorList> errorLists = new ArrayList<>();
		if(req.isEmpty()) {
			errorLists.add(new ErrorList("ColorInfo","101","Please enter SizeMetalInfo"));
		}else {
			int rowno =1;
			for(SaveProductSizeColorMetalReq r : req) {
				
				if(StringUtils.isBlank(r.getDisplayOrder())) {
					errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","Please enter DisplayOrder"));
				}else if(!r.getDisplayOrder().matches("[0-9]*")) {
					errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","DisplayOrder only allows digits"));
				}
				
				if(StringUtils.isBlank(r.getMesurementPieces())) {
					errorLists.add(new ErrorList("MesurementPieces","Row : "+rowno+"","Please enter MesurementPieces"));
				}else if(!r.getMesurementPieces().matches("[0-9]*")) {
					errorLists.add(new ErrorList("MesurementPieces","Row : "+rowno+"","MesurementPieces allows only digits"));
				}
				
				if(StringUtils.isBlank(r.getMesurementType())) {
					errorLists.add(new ErrorList("MesurementType","Row : "+rowno+"","Please enter MesurementType"));
				}
				
				if(StringUtils.isBlank(r.getMesurementValue())) {
					errorLists.add(new ErrorList("MesurementValue","Row : "+rowno+"","Please enter MesurementValue"));
				}else if(!NumberUtils.isParsable(r.getMesurementValue())) {
					//errorLists.add(new ErrorList("MesurementValue","Row : "+rowno+"","MesurementValue allows only digits"));
				}
				
				if(StringUtils.isBlank(r.getMetalName())) {
					errorLists.add(new ErrorList("MetalName","Row : "+rowno+"","Please enter MetalName"));
				}
				
				
				if(StringUtils.isBlank(r.getCompanyId())) {
					errorLists.add(new ErrorList("CompanyId","Row : "+rowno+"","Please enter CompanyId"));
				}
				
				if(StringUtils.isBlank(r.getProductId())) {
					errorLists.add(new ErrorList("ProductId","Row : "+rowno+"","Please enter ProductId"));
				}
				
				if(StringUtils.isBlank(r.getColorCode())) {
					errorLists.add(new ErrorList("ColorCode","Row : "+rowno+"","Please enter ColorCode"));
				}
				
				if(StringUtils.isBlank(r.getStatus())) {
					errorLists.add(new ErrorList("Status","Row : "+rowno+"","Please enter Status"));
				}
				
				rowno++;
			}
			
			if(errorLists.isEmpty()) {
				
				Map<String, Long> displayOrder =req.stream()
						.collect(Collectors.groupingBy(p ->p.getDisplayOrder(),Collectors.counting()));
				
				for (Map.Entry<String, Long> entry : displayOrder.entrySet()) {
					
					if(entry.getValue()>1) {
						errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","Duplicate DisplayOrder does not allow"));
						break ;
					}
					
				}
				
			}
			
		}
		return errorLists;
	}

	public List<ErrorList> sizeCalc(InsertSizeCalcRequest req) {
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			
			if(req.getSizeFoldingDetails().isEmpty() || req.getSizeFoldingDetails()==null) {
				errorLists.add(new ErrorList("SizeFoldingDetails","1001","Please enter folding details"));
			}else {
				
				List<CompletableFuture<List<ErrorList>>> futureList =new ArrayList<>();
				
				for(SizeFoldingDetailsReq data : req.getSizeFoldingDetails()) {
					
					CompletableFuture<List<ErrorList>> future= asyncProcess.validateSizeFolding(data);
					
					futureList.add(future);
				}
				
				CompletableFuture<?> [] future_array =new CompletableFuture<?> [futureList.size()];
				
				CompletableFuture.anyOf(futureList.toArray(future_array)).join();
				
				Object obj = CompletableFuture.anyOf(futureList.toArray(future_array)).get();
				
				List<List<ErrorList>> errorList =(List<List<ErrorList>>) obj;
				
				errorLists = errorList.stream()
						.flatMap(p -> p.stream())
						.collect(Collectors.toList());
				

			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return errorLists;
	}

	
	public List<ErrorList> orderBilling(OrderBillingRequest req) {
		List<ErrorList> errorLists = new ArrayList<>();
		
		if(StringUtils.isBlank(req.getDeliveryType())) {
			errorLists.add(new ErrorList("DeliveryType","101","Please enter DeliveryType"));
		}
		
		if(StringUtils.isBlank(req.getNoOfBoxesPerPieces())) {
			errorLists.add(new ErrorList("NoOfBoxOrDozens","101","Please enter NoOfBoxOrDozens"));
		}
		
		if(StringUtils.isBlank(req.getNoOfPieces())) {
			errorLists.add(new ErrorList("NoOfPieces","101","Please enter NoOfPieces"));
		}
		
		if(StringUtils.isBlank(req.getTotalPieces())) {
			errorLists.add(new ErrorList("TotalPieces","101","Please enter TotalPieces"));
		}
		
		if(StringUtils.isBlank(req.getOrderId())) {
			errorLists.add(new ErrorList("OrderId","101","Please enter OrderId"));
		}
		
		return errorLists;
	}
	
	


	public List<ErrorList> colorCalc(InserSizeColorRequest req) {
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			List<ChallanDetailsInfo> challanList =req.getChallanDetails();
			
			if(challanList.isEmpty() || challanList==null) {
				errorLists.add(new ErrorList("ChallanDetails","101","Please enter ChallanDetails"));
			}else {
				
				for(ChallanDetailsInfo cha : challanList) {
					
					List<ColorFoldingDetailsReq> colorFold =cha.getColorFoldingDetails();
					
					
					
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return errorLists;
	}

	public List<ErrorList> validateSection(List<SectionSaveRequest> req) {
		List<ErrorList> errorLists = new ArrayList<>();
		if(req.isEmpty()) {
			errorLists.add(new ErrorList("Section","101","Please enter sectionDetails"));
		}else {
			int rowno =1;
			for(SectionSaveRequest r : req) {
				
				if(StringUtils.isBlank(r.getCompanyId())) {
					errorLists.add(new ErrorList("CompanyId","Row : "+rowno+"","Please enter CompanyId"));
				}
				
				if(StringUtils.isBlank(r.getNoOfPieces())) {
					errorLists.add(new ErrorList("NoOfPieces","Row : "+rowno+"","Please enter NoOfPieces"));
				}else if(!NumberUtils.isDigits(r.getNoOfPieces())){
					errorLists.add(new ErrorList("NoOfPieces","Row : "+rowno+"","NoOfPiecesallows only digits"));
				}
				
				if(StringUtils.isBlank(r.getPieceAmount())) {
					errorLists.add(new ErrorList("PieceAmount","Row : "+rowno+"","Please enter PieceAmount"));
				}else if(!NumberUtils.isDigits(r.getPieceAmount())){
					errorLists.add(new ErrorList("PieceAmount","Row : "+rowno+"","PieceAmount only digits"));
				}
				
				if(StringUtils.isBlank(r.getProductId())) {
					errorLists.add(new ErrorList("ProductId","Row : "+rowno+"","Please enter ProductId"));
				}
				
				if(StringUtils.isBlank(r.getSectionName())) {
					errorLists.add(new ErrorList("SectionName","Row : "+rowno+"","Please enter SectionName"));
				}
				
				if(StringUtils.isBlank(r.getStatus())) {
					errorLists.add(new ErrorList("Status","Row : "+rowno+"","Please enter Status"));
				}
			}
		}
		return errorLists;
	}

	public List<ErrorList> productMetal(List<ProductMetalReq> req) {
		List<ErrorList> errorLists = new ArrayList<>();
		if(req.isEmpty()) {
			errorLists.add(new ErrorList("ColorInfo","101","Please enter SizeMetalInfo"));
		}else {
			int rowno =1;
			for(ProductMetalReq r : req) {
				
				if(StringUtils.isBlank(r.getDisplayOrder())) {
					errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","Please enter DisplayOrder"));
				}else if(!r.getDisplayOrder().matches("[0-9]*")) {
					errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","DisplayOrder only allows digits"));
				}
				
				if(StringUtils.isBlank(r.getMesurementPieces())) {
					errorLists.add(new ErrorList("MesurementPieces","Row : "+rowno+"","Please enter MesurementPieces"));
				}else if(!r.getMesurementPieces().matches("[0-9]*")) {
					errorLists.add(new ErrorList("MesurementPieces","Row : "+rowno+"","MesurementPieces allows only digits"));
				}
				
				if(StringUtils.isBlank(r.getMesurementType())) {
					errorLists.add(new ErrorList("MesurementType","Row : "+rowno+"","Please enter MesurementType"));
				}
				
				if(StringUtils.isBlank(r.getMesurementValue())) {
					errorLists.add(new ErrorList("MesurementValue","Row : "+rowno+"","Please enter MesurementValue"));
				}else if(NumberUtils.isParsable(r.getMesurementValue())) {
					//errorLists.add(new ErrorList("MesurementValue","Row : "+rowno+"","MesurementValue allows only digits"));
				}
				
				if(StringUtils.isBlank(r.getMetalName())) {
					errorLists.add(new ErrorList("MetalName","Row : "+rowno+"","Please enter MetalName"));
				}
				
				
				if(StringUtils.isBlank(r.getCompanyId())) {
					errorLists.add(new ErrorList("CompanyId","Row : "+rowno+"","Please enter CompanyId"));
				}
				
				if(StringUtils.isBlank(r.getProductId())) {
					errorLists.add(new ErrorList("ProductId","Row : "+rowno+"","Please enter ProductId"));
				}
				
				
				if(StringUtils.isBlank(r.getStatus())) {
					errorLists.add(new ErrorList("Status","Row : "+rowno+"","Please enter Status"));
				}
				
				rowno++;
			}
			
			if(errorLists.isEmpty()) {
				
				Map<String, Long> displayOrder =req.stream()
						.collect(Collectors.groupingBy(p ->p.getDisplayOrder(),Collectors.counting()));
				
				for (Map.Entry<String, Long> entry : displayOrder.entrySet()) {
					
					if(entry.getValue()>1) {
						errorLists.add(new ErrorList("DisplayOrder","Row : "+rowno+"","Duplicate DisplayOrder does not allow"));
						break ;
					}
					
				}
				
			}
		}
		return errorLists;
	}

	public List<ErrorList> validateCompanyMeterial(List<CompanyMeterialMasterReq> request) {
		List<ErrorList> errorLists = new ArrayList<>();
		
		int rowNum =1;
		for(CompanyMeterialMasterReq req : request) {
			
			if(StringUtils.isBlank(req.getMeasurementDispalyName())) {
				errorLists.add(new ErrorList("MeasurementDispalyName",""+rowNum+"","Please enter MeasurementDispalyName"));
			}
			
			if(StringUtils.isBlank(req.getMeasurementDisplayOrder())) {
				errorLists.add(new ErrorList("MeasurementDisplayOrder",""+rowNum+"","Please enter MeasurementDisplayOrder"));
			}else if(!req.getMeasurementDisplayOrder().matches("[0-9]+")) {
				errorLists.add(new ErrorList("MeasurementDisplayOrder",""+rowNum+"","digits only allows"));

			}
			
			if(StringUtils.isBlank(req.getMeasurementName())) {
				errorLists.add(new ErrorList("MeasurementName",""+rowNum+"","Please enter MeasurementName"));
			}
			
			if(StringUtils.isBlank(req.getMeasurementPieces())) {
				errorLists.add(new ErrorList("MeasurementPieces",""+rowNum+"","Please enter MeasurementPieces"));
			}else if(!req.getMeasurementPieces().matches("[0-9]+")) {
				errorLists.add(new ErrorList("MeasurementPieces",""+rowNum+"","digits only allows"));
			}
			
			if(StringUtils.isBlank(req.getMeasurementType())) {
				errorLists.add(new ErrorList("MeasurementType",""+rowNum+"","Please enter MeasurementType"));
			}
			
			if(StringUtils.isBlank(req.getMeasurementValue())) {
				errorLists.add(new ErrorList("MeasurementValue",""+rowNum+"","Please enter MeasurementValue"));
			}else if(!NumberUtils.isParsable(req.getMeasurementValue())) {
				errorLists.add(new ErrorList("MeasurementPieces",""+rowNum+"","digits only allows or decimal"));
			}
			
			if(errorLists.isEmpty() && StringUtils.isNotBlank(req.getMeterialId())) {
				
				Integer companyId =Integer.valueOf(req.getCompanyId());
				Integer productId =Integer.valueOf(req.getProductId());
				Integer sizeId =Integer.valueOf(req.getSizeId());
				Integer colorId =Integer.valueOf(req.getColorId());
				
				List<CompanyMeterialMaster> list =cmmRepository.findByCompanyIdAndProductIdAndSizeIdAndColorId(companyId,productId,sizeId,colorId);
				
				if(list.size()>0 ) {
					
					errorLists.add(new ErrorList("Duplicate Setup",""+rowNum+"","Duplicate meterial setup has availble "));

				}if(StringUtils.isNotBlank(req.getDbColumnName())) {
					
					errorLists.add(new ErrorList("DbColumnName",""+rowNum+"","DB column should not be null or blank"));

				}
			}
		}
		
		return errorLists;
	}

		
}
