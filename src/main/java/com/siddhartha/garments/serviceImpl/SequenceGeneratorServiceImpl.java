package com.siddhartha.garments.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siddhartha.garments.entity.SequenceGenerator;
import com.siddhartha.garments.repository.SequenceGeneratorRepository;

@Component
public class SequenceGeneratorServiceImpl {
	
	
	@Autowired
	private SequenceGeneratorRepository seqRepo;
	
	
	public synchronized String getSequenceNo() {
		String sequence ="";
		try {
			SequenceGenerator entity;
            entity = seqRepo.save(new SequenceGenerator());          
            return String.format("%05d",entity.getSequenceNo()) ;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sequence;
	}
	

}
