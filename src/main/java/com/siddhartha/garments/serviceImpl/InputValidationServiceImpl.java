package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.entity.ColorDeatils;
import com.siddhartha.garments.entity.ColorDeatilsId;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.repository.ColorDetailsRepository;
import com.siddhartha.garments.repository.LoginMasterRepository;
import com.siddhartha.garments.request.ChallanInfoRequest;
import com.siddhartha.garments.request.ColorDetailsRequest;
import com.siddhartha.garments.request.ColorInfoReq;
import com.siddhartha.garments.request.ColorSaveRequest;
import com.siddhartha.garments.request.CompanyMasterRequest;
import com.siddhartha.garments.request.CompanyProductRequest;
import com.siddhartha.garments.request.DisrictRequest;
import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.ExpensiveRequest;
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

@Component
public class InputValidationServiceImpl {
	
	
	@Autowired
	private LoginMasterRepository loginMasterRepository;
	
	@Autowired
	private ColorDetailsRepository colorDetailsRepository;
	
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
			if(StringUtils.isBlank(req.getPassword())) {
				errorLists.add(new ErrorList("Password","450","Please enter Password"));
			}
			if(StringUtils.isBlank(req.getStateCode())) {
				errorLists.add(new ErrorList("State","450","Please choose your State"));
			}
			if(StringUtils.isBlank(req.getUserName())) {
				errorLists.add(new ErrorList("UserName","450","Please enter UserName"));
			}
			if(StringUtils.isBlank(req.getUserType())) {
				errorLists.add(new ErrorList("UserType","450","Please enter UserType"));
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
			if(StringUtils.isBlank(d.getColorId())) {
				errorLists.add(new ErrorList("Color","450","Please choose color"));
			}
			if(StringUtils.isBlank(d.getGoodPieces())) {
				errorLists.add(new ErrorList("GoodPieces","450","Please enter GoodPieces"));
			}
			if(StringUtils.isBlank(d.getDamagedPieces())) {
				errorLists.add(new ErrorList("DamagedPieces","450","Please enter DamagedPieces"));
			}
			if(StringUtils.isBlank(d.getOperatorId())) {
				errorLists.add(new ErrorList("Operator","450","Please choose operator"));
			}
			if(StringUtils.isBlank(d.getSectionId())) {
				errorLists.add(new ErrorList("SectionId","450","Please choose section"));
			}
			if(StringUtils.isBlank(d.getUpdatedBy())) {
				errorLists.add(new ErrorList("UpdatedBy","450","Please enter UpdatedBy"));
			}
			if(StringUtils.isBlank(d.getWorkedPieces())) {
				errorLists.add(new ErrorList("WorkedPieces","450","Please enter WorkedPieces"));
			}
			if(StringUtils.isBlank(d.getWorkedPieces())) {
				errorLists.add(new ErrorList("WorkedPieces","450","Please enter WorkedPieces"));
			}
			if(StringUtils.isBlank(d.getOrderId())) {
				errorLists.add(new ErrorList("Order","450","Please choose LotNumber"));
			}
			
			if(StringUtils.isBlank(d.getOrderId()) && StringUtils.isBlank(d.getChallanId()) && StringUtils.isBlank(d.getColorId())) {
				
				ColorDeatilsId  colorDeatilsId =ColorDeatilsId.builder()
						.challanId(d.getChallanId())
						.orderId(d.getOrderId())
						.colorId(d.getColorId())
						.build();
					
				ColorDeatils  cd =colorDetailsRepository.findById(colorDeatilsId).get();
				
				Integer workerData =Integer.valueOf(d.getGoodPieces()) + Integer.valueOf(d.getDamagedPieces());
				
				Integer masterData =cd.getPiecesCount();
				
				if(workerData>masterData) {
					
					errorLists.add(new ErrorList("Error","500","Your have entered data should not greaterthan master data "
							+ "Master data is "+masterData+" || your enter data is "+workerData+""));
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
		if(StringUtils.isBlank(req.getFoldingYn())) {
			errorLists.add(new ErrorList("FoldingYn","104","Please choose your FoldingYn"));
			
		}else if(StringUtils.isNotBlank(req.getFoldingYn()) && "Y".equals(req.getFoldingYn())) {
			
			if(StringUtils.isBlank(req.getFoldingMesurementType())) {
				errorLists.add(new ErrorList("FoldingMesurementType","103","Please enter FoldingMesurementType"));
			}
			if(StringUtils.isBlank(req.getFoldingMesurementValue())) {
				errorLists.add(new ErrorList("FoldingMesurementValue","104","Please enter FoldingMesurementValue"));
			}else if(StringUtils.isNotBlank(req.getFoldingMesurementValue())) {
	
				if(!req.getFoldingMesurementValue().matches("[0-9]*")) {
					
					errorLists.add(new ErrorList("FoldingMesurementValue","104","Number only allows"));
				}
			}
			
		}
		if(StringUtils.isBlank(req.getElasticYn())) {
			errorLists.add(new ErrorList("ElasticYn","105","Please choose your ElasticYn"));
		}else if(StringUtils.isNotBlank(req.getElasticYn()) && "Y".equals(req.getFoldingYn())) {
			
			if(StringUtils.isBlank(req.getElasticMesurementType())) {
				errorLists.add(new ErrorList("ElasticMesurementType","103","Please enter ElasticMesurementType"));
			}
			if(StringUtils.isBlank(req.getElasticMesurementValue())) {
				errorLists.add(new ErrorList("ElasticMesurementValue","104","Please enter ElasticMesurementValue"));
			}else if(StringUtils.isNotBlank(req.getElasticMesurementValue())) {
	
				if(!req.getElasticMesurementValue().matches("[0-9]*")) {
					
					errorLists.add(new ErrorList("ElasticMesurementValue","104","Number only allows"));
				}
			}
			
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

		
}
