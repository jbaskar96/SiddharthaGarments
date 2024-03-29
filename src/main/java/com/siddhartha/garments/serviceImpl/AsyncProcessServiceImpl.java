package com.siddhartha.garments.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.dto.SizeFoldingDetailsReq;
import com.siddhartha.garments.request.ErrorList;

@Component
public class AsyncProcessServiceImpl {
	
	Logger log = LogManager.getLogger(AsyncProcessServiceImpl.class);
	
	
	
	@Async
	public CompletableFuture<List<ErrorList>> validateSizeFolding(SizeFoldingDetailsReq data){
		List<ErrorList> errorLists = new ArrayList<>();
		try {
			if(data.getReceived().isEmpty() || data.getReceived()==null) {
				errorLists.add(new ErrorList("Received","101","Please enter received to challan number & size: "+data.getChallanNumber()+" :: "+data.getSize()+""));
			}else { 
				List<String> require = data.getRequired();
				List<String> received = data.getReceived();
				List<String> metalType = data.getMetalName();
				
				if(require.size()!=received.size()) {
					errorLists.add(new ErrorList("Received","101","Received value should not be empty for this challan number & size: "+data.getChallanNumber()+" :: "+data.getSize()+""));
				}else {
					
					Boolean status =received.stream().allMatch(p ->NumberUtils.isCreatable(p));
					
					if(!status) {
						errorLists.add(new ErrorList("Received","101","Received value should be allow only numeric values for this challan number & size: "+data.getChallanNumber()+" :: "+data.getSize()+""));

					}else {
					
						Double sum_required =require.stream().map( p->Double.valueOf(p))
								.collect(Collectors.summingDouble(p ->p));
						
						Double sum_received =received.stream().map( p->Double.valueOf(p))
								.collect(Collectors.summingDouble(p ->p));
						
						if(sum_received>sum_required) {
							errorLists.add(new ErrorList("Received","101","Received total value(total = "+sum_received+") should not be greater than Required total value(total = "+sum_required+") for this challan number & size: "+data.getChallanNumber()+" :: "+data.getSize()+""));
						}else {
						
							for (int i=0;i<require.size();i++) {
								Double requ =Double.valueOf(require.get(i));
								Double rece =Double.valueOf(received.get(i));
								String metal_name =metalType.get(i);
								
								if(rece>requ) {
									errorLists.add(new ErrorList("Received","200"," "+metal_name+ " Recevied value should be not greater than required for this challan number & size: "+data.getChallanNumber()+" :: "+data.getSize()+""));
								}
								
								if(rece.compareTo(0D)==0) {
									errorLists.add(new ErrorList("Received","200"," "+metal_name+ " Recevied value should be not greater than 0 challan number & size: "+data.getChallanNumber()+" :: "+data.getSize()+""));

								}
								
							}
						}
						
					}
				}
				
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return CompletableFuture.completedFuture(errorLists);
	}

}
