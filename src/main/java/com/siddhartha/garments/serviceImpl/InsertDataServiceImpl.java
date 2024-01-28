package com.siddhartha.garments.serviceImpl;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.auth.passwordEnc;
import com.siddhartha.garments.entity.DistrictMaster;
import com.siddhartha.garments.entity.DistrictMasterID;
import com.siddhartha.garments.entity.LoginMaster;
import com.siddhartha.garments.entity.OperatorMaster;
import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.entity.ProductMaster;
import com.siddhartha.garments.entity.ProductSizeMaster;
import com.siddhartha.garments.entity.SectionMaster;
import com.siddhartha.garments.entity.StateMaster;
import com.siddhartha.garments.entity.UserDetailsMaster;
import com.siddhartha.garments.entity.UserTypeMaster;
import com.siddhartha.garments.repository.DistrictMasterRepository;
import com.siddhartha.garments.repository.LoginMasterRepository;
import com.siddhartha.garments.repository.OperatorMasterRepository;
import com.siddhartha.garments.repository.ProductColorMasterRepository;
import com.siddhartha.garments.repository.ProductMasterRepository;
import com.siddhartha.garments.repository.ProductSizeMasterRepository;
import com.siddhartha.garments.repository.SectionMasterRepository;
import com.siddhartha.garments.repository.StateMasterRepository;
import com.siddhartha.garments.repository.UserDetailsMasterRepository;
import com.siddhartha.garments.repository.UserTypeMasterRepository;

@Component
public class InsertDataServiceImpl {
	
	@Autowired
	private UserDetailsMasterRepository userRepo;
	
	@Autowired
	private LoginMasterRepository loginRepo;;
	
	@Autowired
	private StateMasterRepository stateRepo;
	
	@Autowired
	private DistrictMasterRepository distRepo;
	
	@Autowired
	private UserTypeMasterRepository userTyeRepo;
	
	@Autowired
	private SectionMasterRepository sectionRepo;
	
	@Autowired
	private ProductSizeMasterRepository sizeRepo;
	
	@Autowired
	private ProductColorMasterRepository colorRepo;
	
	@Autowired
	private OperatorMasterRepository operatorMasterRepository;
	
	@Autowired
	private ProductMasterRepository productRepo;
		
	@PostConstruct
	public void insertdata() {
		try {
			UserDetailsMaster details = UserDetailsMaster.builder()
					.aadharNo("9777 3093 2515")
					.address("Chennai")
					.city("Thangachiammapatty")
					.createdBy("admin")
					.dateOfBirth(new Date())
					.districtCode(1)
					.email("jbaskar96@gmail.com")
					.entryDate(new Date())
					.firstName("baskar")
					.lastName("Jayavel")
					.loginId("admin")
					.mobileNo("9566362141")
					.stateCode(1)
					.userId("456436")
					.userType(1)
					.status("Y")
					.build();
			
			userRepo.save(details);
			
			passwordEnc pe = new passwordEnc();
			LoginMaster loginMaster = LoginMaster.builder()
					.createdBy("admin")
					.entryDate(new Date())
					.loginId("admin")
					.password(pe.encrypt("Admin@01"))
					.usertype("1")
					.status("Y")
					.build();
			
			loginRepo.save(loginMaster);
			
			UserDetailsMaster details1 = UserDetailsMaster.builder()
					.aadharNo("9777 3093 2515")
					.address("Chennai")
					.city("Thangachiammapatty")
					.createdBy("admin")
					.dateOfBirth(new Date())
					.districtCode(1)
					.email("jbaskar96@gmail.com")
					.entryDate(new Date())
					.firstName("baskar")
					.lastName("Jayavel")
					.loginId("Supervisor")
					.mobileNo("9566362141")
					.stateCode(1)
					.userId("456436")
					.userType(2)
					.status("Y")
					.build();
			
			userRepo.save(details1);
			
			LoginMaster loginMaster1 = LoginMaster.builder()
					.createdBy("admin")
					.entryDate(new Date())
					.loginId("Supervisor")
					.password(pe.encrypt("Admin@01"))
					.usertype("2")
					.status("Y")
					.build();
			
			loginRepo.save(loginMaster1);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void masterEntry() {
		try {
			StateMaster stateMaster =StateMaster.builder()
					.entryDate(new Date())
					.stateCode("1")
					.stateName("TAMIL NADU")
					.status("Y")
					.build();
			
			StateMaster state =stateRepo.save(stateMaster);
			
			DistrictMasterID id1 = DistrictMasterID.builder()
					.districtCode(1)
					.stateCode(Integer.valueOf(state.getStateCode()))
					.build();
			
			DistrictMasterID id2 = DistrictMasterID.builder()
					.districtCode(2)
					.stateCode(Integer.valueOf(state.getStateCode()))
					.build();
			
			DistrictMaster dist1 = DistrictMaster.builder()
					.distId(id1)
					.districtName("DINDUGUL")
					.entryDate(new Date())
					.status("Y")
					.build();
			
			DistrictMaster dist2 = DistrictMaster.builder()
					.distId(id2)
					.districtName("MADURAI")
					.entryDate(new Date())
					.status("Y")
					.build();
			
			distRepo.save(dist1);
			distRepo.save(dist2);
			
			
			UserTypeMaster userTypeMaster1 =UserTypeMaster.builder()
					.entryDate(new Date())
					.status("Y")
					.userType("Admin")
					.userTypeId(1)
					.build();
			
			UserTypeMaster userTypeMaster2 =UserTypeMaster.builder()
					.entryDate(new Date())
					.status("Y")
					.userType("Supervisor")
					.userTypeId(2)
					.build();
			userTyeRepo.save(userTypeMaster1);
			userTyeRepo.save(userTypeMaster2);
			
			
			SectionMaster sectionMaster1 =SectionMaster.builder()
					.entryDate(new Date())
					.sectionId(1)
					.sectionName("FOLDING")
					.status("Y")
					.build();
			
			SectionMaster sectionMaster2 =SectionMaster.builder()
					.entryDate(new Date())
					.sectionId(2)
					.sectionName("SHIPPING")
					.status("Y")
					.build();
			sectionRepo.save(sectionMaster1);
			sectionRepo.save(sectionMaster2);
			
			
			ProductSizeMaster productSizeMaster1 =ProductSizeMaster.builder()
					.entryDate(new Date())
					.productSize(80)
					.productSizeId(1)
					.status("Y")
					.build();
			
			ProductSizeMaster productSizeMaster2 =ProductSizeMaster.builder()
					.entryDate(new Date())
					.productSize(85)
					.productSizeId(2)
					.status("Y")
					.build();
			
			
			ProductSizeMaster productSizeMaster3 =ProductSizeMaster.builder()
					.entryDate(new Date())
					.productSize(90)
					.productSizeId(3)
					.status("Y")
					.build();
			
			sizeRepo.save(productSizeMaster1);
			sizeRepo.save(productSizeMaster2);
			sizeRepo.save(productSizeMaster3);

			ProductColorMaster productColorMaster1 = ProductColorMaster.builder()
					.colorId(1)
					.colorName("RED")
					.entryDate(new Date())
					.status("Y")
					.build();
			
			ProductColorMaster productColorMaster2 = ProductColorMaster.builder()
					.colorId(2)
					.colorName("GREEN")
					.entryDate(new Date())
					.status("Y")
					.build();
			
			ProductColorMaster productColorMaster3 = ProductColorMaster.builder()
					.colorId(3)
					.colorName("BLUE")
					.entryDate(new Date())
					.status("Y")
					.build();
			
			colorRepo.save(productColorMaster1);
			colorRepo.save(productColorMaster2);
			colorRepo.save(productColorMaster3);
			
			
			OperatorMaster operatorMaster1 =OperatorMaster.builder()
					.aadharNo("9777 3093 2515")
					.address("Chennai")
					.city("Thangachiammapatty")
					.createdBy("admin")
					.dateOfBirth(new Date())
					.districtCode(1)
					.email("jbaskar96@gmail.com")
					.entryDate(new Date())
					.firstName("baskar")
					.lastName("Jayavel")
					.mobileNo("9566362141")
					.stateCode(1)
					.status("Y")
					.operatorId("OPT_1001")
					.build();
			
			OperatorMaster operatorMaster2 =OperatorMaster.builder()
					.aadharNo("9777 3093 2515")
					.address("Chennai")
					.city("Thangachiammapatty")
					.createdBy("admin")
					.dateOfBirth(new Date())
					.districtCode(1)
					.email("jbaskar96@gmail.com")
					.entryDate(new Date())
					.firstName("Mani")
					.lastName("Jayavel")
					.mobileNo("9566362141")
					.stateCode(1)
					.status("Y")
					.operatorId("OPT_1002")
					.build();
			
			operatorMasterRepository.save(operatorMaster1);
			operatorMasterRepository.save(operatorMaster2);
			
			ProductMaster product1=ProductMaster.builder()
					.status("Y")
					.entryDate(new Date())
					.productId(1)
					.productName("T-SHIRT")
					.build();
			
			ProductMaster product2=ProductMaster.builder()
					.status("Y")
					.entryDate(new Date())
					.productId(2)
					.productName("SHIRT")
					.build();
			

			ProductMaster product3=ProductMaster.builder()
					.status("Y")
					.entryDate(new Date())
					.productId(3)
					.productName("PANT")
					.build();
			
			productRepo.save(product1);
			productRepo.save(product2);
			productRepo.save(product3);


		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void createLot() {
		try {
			
		}catch (Exception e) {
			
		}
	}

}
