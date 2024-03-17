package com.siddhartha.garments.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.dto.InsertSizeCalcRequest;
import com.siddhartha.garments.dto.SizeFoldingDetailsReq;
import com.siddhartha.garments.entity.OrderChallanDetails;
import com.siddhartha.garments.entity.OrderDetails;
import com.siddhartha.garments.entity.OrderSizeColorDetails;
import com.siddhartha.garments.entity.ProductSizeColorMetalMaster;
import com.siddhartha.garments.entity.ProductSizeMetalMaster;
import com.siddhartha.garments.repository.OrderChallanDetailsRepository;
import com.siddhartha.garments.repository.OrderDetailsRepository;
import com.siddhartha.garments.repository.OrderSizeColorDetailsRepository;
import com.siddhartha.garments.repository.ProductSizeColorMetalMasterRepository;
import com.siddhartha.garments.repository.ProductSizeMetalMasterRepository;
import com.siddhartha.garments.request.ChallanDetailsInfo;
import com.siddhartha.garments.request.ColorFoldingDetailsReq;
import com.siddhartha.garments.request.InserSizeColorRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.MetalCalculationService;

@Service
@Transactional
public class MetalCalculationServiceImpl implements MetalCalculationService{
	
	org.apache.logging.log4j.Logger log= LogManager.getLogger(MetalCalculationServiceImpl.class);
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	@Autowired
	private OrderChallanDetailsRepository challanRepo;
	
	@Autowired
	private ProductSizeMetalMasterRepository sizeMetalRepo;
	
	@Autowired
	private OrderSizeColorDetailsRepository orderSizeColorRepo;
	
	@Autowired
	private ProductSizeColorMetalMasterRepository colorMetalRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	private Query query=null;
	
	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Autowired
	private MetalCalculationServiceImpl metalServiceImpl;
	
	@Autowired
	private InputValidationServiceImpl validation;

	@Override
	public CommonResponse generateSizeCalc(EditOrderDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			//List<ErrorList> error = validation.sizeCalc(req);
			String orderId =req.getOrderId();
			OrderDetails od =orderDetailsRepo.findById(orderId).get();
			orderDetailsRepo.deleteSizeMetalByOrderId(orderId);
			Integer companyId =od.getCompanyId();
			Integer productId =od.getProductId();
			List<OrderChallanDetails> challan =challanRepo.findByIdOrderId(orderId);
			Map<Object,Object> calcResponse = new HashMap<Object,Object>();
			List<Map<Object,Object>> list =new ArrayList<>();
			for(OrderChallanDetails ocd : challan) {
				
				Map<Object,Object> map = new HashMap<Object,Object>();
				List<ProductSizeMetalMaster> metal =sizeMetalRepo.findByIdCompanyIdAndIdProductIdAndIdSizeIdOrderByDisplayOrder(companyId,productId,ocd.getSizeId());
				int index=0;
				String [] metalName =new String[metal.size()];
				Object [] required =new Object[metal.size()];
				Object [] received =new Object[metal.size()];
				StringJoiner params =new StringJoiner(",");
				for(ProductSizeMetalMaster met : metal) {
					String calctype =met.getMesurementType();
					Double calcVal =met.getMesurementValue();
					Integer noOfpieces =met.getMesurementPieces();
					Double overAllCalc =0D;
					Integer piecesRate =0;
					String calctypeDesc="";
					if("G".equals(calctype)) {// gram
						piecesRate = ocd.getTotalPieces()/ noOfpieces;
						overAllCalc =calcVal * piecesRate;
					}else if("M".equals(calctype)) {// meter
						piecesRate = ocd.getTotalPieces()/ noOfpieces;
						overAllCalc = calcVal * piecesRate;
					}else if("P".equals(calctype)) {//precentage
						Double result =calcVal * noOfpieces;
						overAllCalc =overAllCalc - result;
					}else if("N".equals(calctype)) {// no calc or count
						overAllCalc=Double.valueOf(ocd.getTotalPieces());
					}
					
					required[index] =overAllCalc;
					received[index] =0;
					metalName[index]=met.getMetalName() + "("+calcVal+"%) "+calctype+"";
					params.add(met.getColumnName());
					
					index++;
				}
				
				map.put("Required", required);
				map.put("Received", received);
				map.put("ChallanId", ocd.getId().getChallanId().toString());
				map.put("ChallanNumber",ocd.getChallanNumber());
				map.put("ChallanDate",ocd.getChallanNumber());
				map.put("SizeId",ocd.getChallanNumber());
				map.put("Size",ocd.getChallanNumber());
				map.put("TotalPieces",ocd.getTotalPieces());
				map.put("MetalName", metalName);
				map.put("Params", params.toString());
				list.add(map);
			}
			
			calcResponse.put("OrderId", od.getOrderId());
			calcResponse.put("CompanyId", companyId);
			calcResponse.put("ProductId", productId);
			calcResponse.put("SizeFoldingDetails", list);
			
			response.setMessage("Success");
			response.setError(null);
			response.setResponse("Folding Calculated Successfully");
			
			
			GenerateCalcThread calcThread =new GenerateCalcThread(calcResponse,metalServiceImpl,"SIZE_FOLDING");
			Thread thread = new Thread(calcThread);
			thread.setName("SIZE_FOLDING"+req.getOrderId());
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
			
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Failed");
			response.setError(null);
			response.setResponse(e.getMessage());
		}
		return response;
	}

	@Override
	public CommonResponse generateSizeColorCalc(EditOrderDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			String orderId =req.getOrderId();
			orderDetailsRepo.deleteColorMetalByOrderId(orderId);
			OrderDetails od =orderDetailsRepo.findById(orderId).get();
			Integer companyId =od.getCompanyId();
			Integer productId =od.getProductId();
			List<OrderChallanDetails> challan =challanRepo.findByIdOrderId(orderId);
			Map<Object,Object> orderMap =new HashMap<Object, Object>();
			List<Map<String,Object>> listCha =new ArrayList<>();
			
			for(OrderChallanDetails c : challan) {
				
				List<OrderSizeColorDetails> color =orderSizeColorRepo.findByIdOrderIdAndIdChallanId(orderId,c.getId().getChallanId());
				Map<String,Object> chaMap =new HashMap<String, Object>();
				List<Map<String,Object>> listMet =new ArrayList<>();
				
				for(OrderSizeColorDetails col : color) {
					
					List<ProductSizeColorMetalMaster> metal =colorMetalRepo.findByIdCompanyIdAndIdProductIdAndIdSizeIdAndIdColourCodeOrderByDisplayOrder(companyId, productId, c.getSizeId(), col.getColorCode());
					int index=0;
					String [] metalName =new String[metal.size()];
					Object [] required =new Object[metal.size()];
					Object [] received =new Object[metal.size()];
					StringJoiner params =new StringJoiner(",");
					Map<String,Object> map =new HashMap<String, Object>();
					for(ProductSizeColorMetalMaster met : metal) {
						String calctype =met.getMesurementType();
						Double calcVal =met.getMesurementValue();
						Integer noOfpieces =met.getMesurementPieces();
						Double overAllCalc =0D;
						Integer piecesRate =0;
						if("G".equals(calctype)) {// gram
							piecesRate = col.getTotalPieces()/ noOfpieces;
							overAllCalc =calcVal * piecesRate;
						}else if("M".equals(calctype)) {// meter
							piecesRate = col.getTotalPieces()/ noOfpieces;
							overAllCalc = calcVal * piecesRate;
						}else if("P".equals(calctype)) {//precentage
							Double result =calcVal * noOfpieces;
							overAllCalc =overAllCalc - result;
						}else if("N".equals(calctype)) {// no calc or count
							overAllCalc=Double.valueOf(col.getTotalPieces());
						}
						
						required[index] =overAllCalc;
						received[index] =0;
						metalName[index]=met.getMetalName() + "("+calcVal+"%) "+calctype+"";
						params.add(met.getColumnName());
						
						index++;
					}
					map.put("Required", required);
					map.put("Received", received);
					map.put("ColorId", col.getId().getColorId());
					map.put("ColorName", col.getColorName());
					map.put("TotalPieces",col.getTotalPieces());
					map.put("MetalName", metalName);
					map.put("Params", params.toString());
					
					listMet.add(map);
				
				}
			
				chaMap.put("ChallanNo", c.getChallanNumber());
				chaMap.put("ChallanId", c.getId().getChallanId());
				chaMap.put("ChallanDate", sdf.format(c.getChallanDate()));
				chaMap.put("SizeId", c.getSizeId());
				chaMap.put("Size", c.getSizeId());
				chaMap.put("TotalPieces",c.getTotalPieces());
				chaMap.put("ColorFoldingDetails",listMet);
				listCha.add(chaMap);
			}
			orderMap.put("OrderId", od.getOrderId());
			orderMap.put("CompanyId", od.getCompanyId());
			orderMap.put("ProductId", od.getProductId());
			orderMap.put("ChallanDetails", listCha);
			
			GenerateCalcThread calcThread =new GenerateCalcThread(orderMap,metalServiceImpl,"COLOR_FOLDING");
			Thread thread = new Thread(calcThread);
			thread.setName("METAL_CALC"+req.getOrderId());
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
			
			response.setMessage("Success");
			response.setError(null);
			response.setResponse("ColorFolding Calculated Successfully");
			
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Failed");
			response.setError(null);
			response.setResponse("ColorFolding Calculated Failed");
		}
		return response;
	}

	@Override
	public CommonResponse doInsertSizeCalc(InsertSizeCalcRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			Integer companyId =Integer.valueOf(req.getCompanyId());
			Integer productId =Integer.valueOf(req.getProductId());
			String orderId =req.getOrderId();
			List<Map<String,Object>> metal =orderDetailsRepo.getMetalDetails(orderId);
			for(SizeFoldingDetailsReq r :req.getSizeFoldingDetails()) {
				
				Map<String,Object> map =metal.stream().
						filter(p->p.get("CHALLAN_ID").toString().equalsIgnoreCase(r.getChallanId()))
						.filter(p->p.get("TYPE_NAME").toString().equalsIgnoreCase("PARAMS"))
								.collect(Collectors.toList()).get(0);
				
				String columnsName=map.get("METAL_COLUMNS")==null?"":map.get("METAL_COLUMNS").toString();
				
				String [] strArray =new String[r.getReceived().size()];
				r.getReceived().toArray(strArray);
				
				List<String> prepare = getDyanamicColumn(strArray);
				
				try {
					String requiredQuery ="insert into METAL_CALC_DEATILS(COMPANY_ID,PRODUCT_ID,ORDER_ID,CHALLAN_ID,SIZE_ID,SIZE,TYPE_NAME,CHALLAN_DATE,CHALLAN_NO,PIECES,"
							+ columnsName+") VALUES (?,?,?,?,?,?,?,?,?,?,"+StringUtils.join(prepare,",")+")";
					
					jdbcTemplate.execute(requiredQuery,new PreparedStatementCallback<Boolean>(){
					    @Override
					    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
					        ps.setString(1,companyId.toString());
					        ps.setString(2,productId.toString());
					        ps.setString(3,orderId);
					        ps.setString(4,r.getChallanId());
					        ps.setString(5,r.getSizeId());
					        ps.setString(6,r.getSizeId());
					        ps.setString(7,"RECEIVED");
					        ps.setString(8,r.getChallanDate());
					        ps.setString(9,r.getChallanNumber());
					        ps.setString(10,r.getTotalPieces());
					        
					        for (int i = 1; i <= strArray.length; i++) {
								ps.setString(i + 10, strArray[i-1] == null ? null
										: strArray[i-1].toString().trim());
								log.info("rowid: "+i+", rowvalue : "+ strArray[i-1] );
							}
			 
					        return ps.execute();
					    }
					});
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					jdbcTemplate.getDataSource().getConnection().close();
				}	
				
				OrderDetails order =orderDetailsRepo.findById(req.getOrderId()).get();
				order.setSizefoldingYn("Y");
				orderDetailsRepo.save(order);
				
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("OrderId", req.getOrderId());
			
			response.setMessage("Success");
			response.setError(null);
			response.setResponse(map);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Failed");
			response.setError(null);
			response.setResponse(null);
		}
		return response;
	}
	
	public void insertCalcdata(Map<Object,Object> data) {
		try {
			
			@SuppressWarnings("unchecked")
			List<Map<Object,Object>> foldingList =(List<Map<Object,Object>>)data.get("SizeFoldingDetails");
			String companyId =data.get("CompanyId").toString();
			String productId =data.get("ProductId").toString();
			String orderId =data.get("OrderId").toString();
			for (Map<Object,Object> map :foldingList) {
				Object[] required =(Object[]) map.get("Required");
				String[] metalName =(String[])map.get("MetalName");
				String columnsName =map.get("Params").toString();
				String challanId =map.get("ChallanId").toString();
				String sizeId =map.get("SizeId").toString();
				String size =map.get("Size").toString();
				String challanDate =map.get("ChallanDate").toString();
				String challanNumber =map.get("ChallanNumber").toString();
				String totalPieces =map.get("TotalPieces").toString();
				List<String> prepare = new ArrayList<String>();
				
				prepare = getDyanamicColumn(required);
				
				
				try {
					String requiredQuery ="insert into METAL_CALC_DEATILS(COMPANY_ID,PRODUCT_ID,ORDER_ID,CHALLAN_ID,SIZE_ID,SIZE,TYPE_NAME,CHALLAN_DATE,CHALLAN_NO,PIECES,"
							+ columnsName+") VALUES (?,?,?,?,?,?,?,?,?,?,"+StringUtils.join(prepare,",")+")";
					
					jdbcTemplate.execute(requiredQuery,new PreparedStatementCallback<Boolean>(){
					    @Override
					    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
					        ps.setString(1,companyId);
					        ps.setString(2,productId);
					        ps.setString(3,orderId);
					        ps.setString(4,challanId);
					        ps.setString(5,sizeId);
					        ps.setString(6,size);
					        ps.setString(7,"REQUIRED");
					        ps.setString(8,challanDate);
					        ps.setString(9,challanNumber);
					        ps.setString(10,totalPieces);
					        
					        for (int i = 1; i <= required.length; i++) {
								ps.setString(i + 10, required[i-1] == null ? null
										: required[i-1].toString().trim());
								log.info("rowid: "+i+", rowvalue : "+ required[i-1] );
							}
			 
					        return ps.execute();
					    }
					});
					
					
					String metalQuery ="insert into METAL_CALC_DEATILS(COMPANY_ID,PRODUCT_ID,ORDER_ID,CHALLAN_ID,SIZE_ID,SIZE,TYPE_NAME,CHALLAN_DATE,CHALLAN_NO,PIECES,METAL_COLUMNS"
							+") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
					
					jdbcTemplate.execute(metalQuery,new PreparedStatementCallback<Boolean>(){
					    @Override
					    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
					        ps.setString(1,companyId);
					        ps.setString(2,productId);
					        ps.setString(3,orderId);
					        ps.setString(4,challanId);
					        ps.setString(5,sizeId);
					        ps.setString(6,size);
					        ps.setString(7,"METAL");
					        ps.setString(8,challanDate);
					        ps.setString(9,challanNumber);
					        ps.setString(10,totalPieces);
					        ps.setString(11,StringUtils.join(metalName,","));
					        
					        return ps.execute();
					    }
					});
					
					
					String paramsQuery ="insert into METAL_CALC_DEATILS(COMPANY_ID,PRODUCT_ID,ORDER_ID,CHALLAN_ID,SIZE_ID,SIZE,TYPE_NAME,CHALLAN_DATE,CHALLAN_NO,PIECES,METAL_COLUMNS"
							+") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
					
					jdbcTemplate.execute(paramsQuery,new PreparedStatementCallback<Boolean>(){
					    @Override
					    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
					        ps.setString(1,companyId);
					        ps.setString(2,productId);
					        ps.setString(3,orderId);
					        ps.setString(4,challanId);
					        ps.setString(5,sizeId);
					        ps.setString(6,size);
					        ps.setString(7,"PARAMS");
					        ps.setString(8,challanDate);
					        ps.setString(9,challanNumber);
					        ps.setString(10,totalPieces);
					        ps.setString(11,columnsName);
					        
					        return ps.execute();
					    }
					});
				
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					jdbcTemplate.getDataSource().getConnection().close();
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<String> getDyanamicColumn(Object[] data){
		List<String> prepare = new ArrayList<String>();
		
		for (int i = 0; i < data.length; i++) {
			
			prepare.add("?");
		}
		return prepare;
		
	}

	@Override
	public CommonResponse viewSizeCalc(EditOrderDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,Object>> list =orderDetailsRepo.getMetalDetails(req.getOrderId());
						
			Map<String,List<Map<String,Object>>> sizeBasedGroup =list.stream()
					.filter(p ->p.get("COLOR_ID")==null)
					.collect(Collectors.groupingBy( p->p.get("CHALLAN_ID").toString()));
			
			HashMap<String, Object> calcResponse = new HashMap<String, Object>();
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			
			for (Map.Entry<String,List<Map<String,Object>>> entry :sizeBasedGroup.entrySet()) {
				
				String challanId =entry.getKey();
				
				List<Map<String,Object>> sizeList =entry.getValue();
					
				String params =sizeList.stream()
					.filter(p ->p.get("TYPE_NAME").toString().equals("PARAMS"))
					.map(P ->P.get("METAL_COLUMNS").toString())
					.collect(Collectors.joining());
				
				String metal =sizeList.stream()
						.filter(p ->p.get("TYPE_NAME").toString().equals("METAL"))
						.map(P ->P.get("METAL_COLUMNS").toString())
						.collect(Collectors.joining());
					
					
				String required ="select "+params+" from METAL_CALC_DEATILS where ORDER_ID =? and CHALLAN_ID=? and color_id is null and TYPE_NAME=?";
				
				String received ="select "+params+" from METAL_CALC_DEATILS where ORDER_ID =? and CHALLAN_ID=? and color_id is null and  TYPE_NAME=?";
				
				query =em.createNativeQuery(required)
						.setParameter(1, req.getOrderId())
						.setParameter(2, challanId)
						.setParameter(3, "REQUIRED");
				
				Object reqArray =query.getSingleResult();
				
				if(reqArray instanceof String) {
					
					reqArray = new Object[] {reqArray};
					
				}
				
				query =em.createNativeQuery(received)
						.setParameter(1, req.getOrderId())
						.setParameter(2, challanId)
						.setParameter(3, "RECEIVED");
				
				Object receivedArray = null;
				try {
					receivedArray = query.getSingleResult();
				} catch (NoResultException e) {
				    log.debug("No result forund for... " +receivedArray);
				}
				
				if(receivedArray instanceof String) {
					
					receivedArray = new Object[] {receivedArray};
					
				}
				
				
				String[] metal_name =metal.split(",");
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("Required", reqArray);
				map.put("Received", receivedArray ==null?new Object[]{} :receivedArray);
				map.put("MetalName", metal_name);
				map.put("ChallanId", challanId);
				map.put("ChallanNumber",sizeList.get(0).get("CHALLAN_NO"));
				map.put("ChallanDate",sizeList.get(0).get("CHALLAN_DATE"));
				map.put("SizeId",sizeList.get(0).get("SIZE_ID"));
				map.put("Size",sizeList.get(0).get("SIZE"));
				map.put("TotalPieces",sizeList.get(0).get("PIECES"));
			
				mapList.add(map);
			}
			
			calcResponse.put("OrderId",list.get(0).get("ORDER_ID"));
			calcResponse.put("CompanyId", list.get(0).get("COMPANY_ID"));
			calcResponse.put("ProductId", list.get(0).get("PRODUCT_ID"));
			calcResponse.put("SizeFoldingDetails", mapList);
			
			response.setMessage("Success");
			response.setError(null);
			response.setResponse(calcResponse);
			
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Failed");
			response.setError(null);
			response.setResponse(null);
		}
		return response;
	}
	
	public void inserColorBasedCalc(Map<Object,Object> req) {
		try {
			String orderId =req.get("OrderId")==null?"":req.get("OrderId").toString();
			String companyId =req.get("CompanyId")==null?"":req.get("CompanyId").toString();
			String productId =req.get("ProductId")==null?"":req.get("ProductId").toString();
			List<Map<String,Object>> challanDet =(List<Map<String,Object>>) req.get("ChallanDetails");
			for(Map<String,Object> cha : challanDet) {
				
				String challanNo =cha.get("ChallanNo")==null?"":cha.get("ChallanNo").toString();
				String challanId =cha.get("ChallanId")==null?"":cha.get("ChallanId").toString();
				String challanDate =cha.get("ChallanDate")==null?"":cha.get("ChallanDate").toString();
				String sizeId =cha.get("SizeId")==null?"":cha.get("SizeId").toString();
				String size =cha.get("Size")==null?"":cha.get("Size").toString();
				String sizeColorPieces =cha.get("TotalPieces")==null?"":cha.get("TotalPieces").toString();
				List<Map<String,Object>>  colorList =cha.get("ColorFoldingDetails")==null?null:(List<Map<String,Object>>)cha.get("ColorFoldingDetails");
				
				for(Map<String,Object> col : colorList) {
					Object[] required =(Object[]) col.get("Required");
					String[] metalName =(String[])col.get("MetalName");
					String columnsName =col.get("Params").toString();
					String colorId =col.get("ColorId")==null?"":col.get("ColorId").toString();
					String colorName =col.get("ColorName")==null?"":col.get("ColorName").toString();
					String totalPieces =col.get("TotalPieces")==null?"":col.get("TotalPieces").toString();
					
					List<String> prepare = getDyanamicColumn(required);
					
					try {
						String requiredQuery ="insert into METAL_CALC_DEATILS(COMPANY_ID,PRODUCT_ID,ORDER_ID,CHALLAN_ID,SIZE_ID,SIZE,TYPE_NAME,CHALLAN_DATE,CHALLAN_NO,PIECES,COLOR_ID,COLOR_NAME,COLOR_PIECES,"
								+ columnsName+") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,"+StringUtils.join(prepare,",")+")";
						
						jdbcTemplate.execute(requiredQuery,new PreparedStatementCallback<Boolean>(){
						    @Override
						    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
						        ps.setString(1,companyId);
						        ps.setString(2,productId);
						        ps.setString(3,orderId);
						        ps.setString(4,challanId);
						        ps.setString(5,sizeId);
						        ps.setString(6,size);
						        ps.setString(7,"REQUIRED");
						        ps.setString(8,challanDate);
						        ps.setString(9,challanNo);
						        ps.setString(10,sizeColorPieces);
						        ps.setString(11,colorId);
						        ps.setString(12,colorName);
						        ps.setString(13,totalPieces);
						        
						        for (int i = 1; i <= required.length; i++) {
									ps.setString(i + 13, required[i-1] == null ? null
											: required[i-1].toString().trim());
									log.info("rowid: "+i+", rowvalue : "+ required[i-1] );
								}
				 
						        return ps.execute();
						    }
						});
						
						
						String metalQuery ="insert into METAL_CALC_DEATILS(COMPANY_ID,PRODUCT_ID,ORDER_ID,CHALLAN_ID,SIZE_ID,SIZE,TYPE_NAME,CHALLAN_DATE,CHALLAN_NO,PIECES,METAL_COLUMNS,COLOR_ID,COLOR_NAME,COLOR_PIECES"
								+") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
						jdbcTemplate.execute(metalQuery,new PreparedStatementCallback<Boolean>(){
						    @Override
						    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
						        ps.setString(1,companyId);
						        ps.setString(2,productId);
						        ps.setString(3,orderId);
						        ps.setString(4,challanId);
						        ps.setString(5,sizeId);
						        ps.setString(6,size);
						        ps.setString(7,"METAL");
						        ps.setString(8,challanDate);
						        ps.setString(9,challanNo);
						        ps.setString(10,sizeColorPieces);
						        ps.setString(11,StringUtils.join(metalName,","));
						        ps.setString(12,colorId);
						        ps.setString(13,colorName);
						        ps.setString(14,totalPieces);
						        return ps.execute();
						    }
						});
						
						
						String paramsQuery ="insert into METAL_CALC_DEATILS(COMPANY_ID,PRODUCT_ID,ORDER_ID,CHALLAN_ID,SIZE_ID,SIZE,TYPE_NAME,CHALLAN_DATE,CHALLAN_NO,PIECES,METAL_COLUMNS,COLOR_ID,COLOR_NAME,COLOR_PIECES"
								+") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
						jdbcTemplate.execute(paramsQuery,new PreparedStatementCallback<Boolean>(){
						    @Override
						    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
						        ps.setString(1,companyId);
						        ps.setString(2,productId);
						        ps.setString(3,orderId);
						        ps.setString(4,challanId);
						        ps.setString(5,sizeId);
						        ps.setString(6,size);
						        ps.setString(7,"PARAMS");
						        ps.setString(8,challanDate);
						        ps.setString(9,challanNo);
						        ps.setString(10,sizeColorPieces);
						        ps.setString(11,columnsName);
						        ps.setString(12,colorId);
						        ps.setString(13,colorName);
						        ps.setString(14,totalPieces);
						        return ps.execute();
						    }
						});
					
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						jdbcTemplate.getDataSource().getConnection().close();
					}
					
				}
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public CommonResponse viewSizeColorCalc(EditOrderDetailsReq req) {
		CommonResponse response = new CommonResponse();
		try {
			List<Map<String,Object>> list =orderDetailsRepo.getMetalDetails(req.getOrderId());
			
			Map<String,Object> res =new HashMap<String,Object>();
			
			Map<String,List<Map<String,Object>>> sizeBasedGroup =list.stream()
					.collect(Collectors.groupingBy( p->p.get("CHALLAN_ID").toString()));
			
			List<Map<String,Object>> challanSize =new ArrayList<Map<String,Object>>();
			
			for(Map.Entry<String,List<Map<String,Object>>> sizeEntry :sizeBasedGroup.entrySet()) {
				
				String challanId =sizeEntry.getKey();
				
				List<Map<String,Object>> colorList =sizeEntry.getValue();
				
				Map<String,List<Map<String,Object>>> colorBasedGroup =colorList.stream()
						.filter(p ->p.get("COLOR_ID")!=null)
						.collect(Collectors.groupingBy(p ->p.get("COLOR_ID").toString()));
				
				List<Map<String,Object>> colorResList =new ArrayList<Map<String,Object>>();
				
				HashMap<String, Object> chaMap = new HashMap<String, Object>();
				
				for(Map.Entry<String, List<Map<String,Object>>> colorEntry :colorBasedGroup.entrySet()) {
					
					String colorId =colorEntry.getKey();
					
					List<Map<String,Object>> colorData =colorEntry.getValue();
					
					String params =colorData.stream()
							.filter(p ->p.get("TYPE_NAME").toString().equals("PARAMS"))
							.map(P ->P.get("METAL_COLUMNS").toString())
							.collect(Collectors.joining());
						
						String metal =colorData.stream()
								.filter(p ->p.get("TYPE_NAME").toString().equals("METAL"))
								.map(P ->P.get("METAL_COLUMNS").toString())
								.collect(Collectors.joining());
							
						
						String required ="select "+params+" from METAL_CALC_DEATILS where ORDER_ID =? and CHALLAN_ID=? and COLOR_ID=? and color_id is not null and TYPE_NAME=?";
						
						String received ="select "+  params+" from METAL_CALC_DEATILS where ORDER_ID =? and CHALLAN_ID=? and COLOR_ID=? and color_id is not null and TYPE_NAME=?";
						
						query =em.createNativeQuery(required)
								.setParameter(1, req.getOrderId())
								.setParameter(2, challanId)
								.setParameter(3, colorId)
								.setParameter(4, "REQUIRED");
						
						Object reqArray =query.getSingleResult();
						
						if(reqArray instanceof String) {
							
							reqArray = new Object[] {reqArray};
							
						}
						
						
						query =em.createNativeQuery(received)
								.setParameter(1, req.getOrderId())
								.setParameter(2, challanId)
								.setParameter(3, colorId)
								.setParameter(4, "RECEIVED");
						
						Object receivedArray = null;
						try {
							receivedArray = query.getSingleResult();
						} catch (NoResultException e) {
						    log.debug("No result forund for... " +receivedArray);
						}
						
						if(receivedArray instanceof String) {
							
							receivedArray = new Object[] {receivedArray};
							
						}
						
						
						
						String[] metal_name =metal.split(",");
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("Required", reqArray);
						map.put("Received", receivedArray ==null?new Object[]{} :receivedArray);
						map.put("MetalName", metal_name);
						map.put("ColorId", colorId);
						map.put("ColorName", "Not Found..");
						map.put("TotalPieces",colorData.get(0).get("COLOR_PIECES"));
						colorResList.add(map);
					
				}
				
				chaMap.put("ChallanNumber", colorList.get(0).get("CHALLAN_NO"));
				chaMap.put("ChallanId", colorList.get(0).get("CHALLAN_ID"));
				chaMap.put("ChallanDate", colorList.get(0).get("CHALLAN_DATE"));
				chaMap.put("SizeId", colorList.get(0).get("SIZE_ID"));
				chaMap.put("Size", colorList.get(0).get("SIZE"));
				chaMap.put("TotalPieces",colorList.get(0).get("PIECES"));
				chaMap.put("ColorFoldingDetails", colorResList);
				challanSize.add(chaMap);
			}
			
			res.put("CompanyId", list.get(0).get("COMPANY_ID"));
			res.put("ProductId", list.get(0).get("PRODUCT_ID"));
			res.put("OrderId", list.get(0).get("ORDER_ID"));
			res.put("ChallanDetails", challanSize);
			
			response.setMessage("Success");
			response.setError(null);
			response.setResponse(res);
	
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Failed");
			response.setError(null);
			response.setResponse(null);
		}
		return response;
	}

	@Override
	public CommonResponse insertSizeColorCalc(InserSizeColorRequest req) {
		CommonResponse response = new CommonResponse();
		try {
			String orderId =req.getOrderId();
			String companyId =req.getProductId();
			String productId =req.getProductId();
			List<Map<String,Object>> metal =orderDetailsRepo.getMetalDetails(orderId);
			
			Map<String,List<Map<String,Object>>> challanGroup =metal.stream()
					.collect(Collectors.groupingBy(p ->p.get("CHALLAN_ID").toString()));
					
			for(ChallanDetailsInfo cha :req.getChallanDetails()) {
				
				String challanDate =cha.getChallanDate();
				String challanNumber =cha.getChallanNumber();
				String challanId =cha.getChallanId();
				String size =cha.getSize();
				String sizeId =cha.getSizeId();
				String totalPieces=cha.getTotalPieces();
				
				List<Map<String,Object>> challanDet =challanGroup.get(challanId);
				
				Map<String,List<Map<String,Object>>>  colorBasedGroup =challanDet.stream()
						.collect(Collectors.groupingBy(p ->p.get("COLOR_ID").toString()));
				
				for(ColorFoldingDetailsReq col :cha.getColorFoldingDetails()) {
					
					List<String> received =col.getReceived();
					String colorId =col.getColorId();
					String colorName =col.getColorName();
					String totalColorPieces =col.getTotalPieces();
					
					List<Map<String,Object>> colorDet =colorBasedGroup.get(colorId);
					
					Map<String,Object> map =colorDet.stream().
							filter(p->p.get("TYPE_NAME").toString().equalsIgnoreCase("PARAMS"))
									.collect(Collectors.toList()).get(0);
					
					String columnsName=map.get("METAL_COLUMNS")==null?"":map.get("METAL_COLUMNS").toString();
					
					String [] strArray =new String[received.size()];
					received.toArray(strArray);
					
					List<String> prepare = getDyanamicColumn(strArray);
					
					try {
						String requiredQuery ="insert into METAL_CALC_DEATILS(COMPANY_ID,PRODUCT_ID,ORDER_ID,CHALLAN_ID,SIZE_ID,SIZE,TYPE_NAME,CHALLAN_DATE,CHALLAN_NO,PIECES,COLOR_PIECES,COLOR_NAME,"
								+ columnsName+") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,"+StringUtils.join(prepare,",")+")";
						
						jdbcTemplate.execute(requiredQuery,new PreparedStatementCallback<Boolean>(){
						    @Override
						    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
						        ps.setString(1,companyId.toString());
						        ps.setString(2,productId.toString());
						        ps.setString(3,orderId);
						        ps.setString(4,challanId);
						        ps.setString(5,sizeId);
						        ps.setString(6,size);
						        ps.setString(7,"RECEIVED");
						        ps.setString(8,challanDate);
						        ps.setString(9,challanNumber);
						        ps.setString(10,totalPieces);
						        ps.setString(11,totalColorPieces);
						        ps.setString(12,colorName);
						        for (int i = 1; i <= strArray.length; i++) {
									ps.setString(i + 12, strArray[i-1] == null ? null
											: strArray[i-1].toString().trim());
									log.info("rowid: "+i+", rowvalue : "+ strArray[i-1] );
								}
				 
						        return ps.execute();
						    }
						});
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						jdbcTemplate.getDataSource().getConnection().close();
					}	
					
					OrderDetails order =orderDetailsRepo.findById(req.getOrderId()).get();
					order.setColorFoldingYn("Y");
					orderDetailsRepo.save(order);
					
				}
				
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("OrderId", req.getOrderId());
			
			response.setMessage("Success");
			response.setError(null);
			response.setResponse(map);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Failed");
			response.setError(null);
			response.setResponse(null);
		}
		return response;
	}

}

