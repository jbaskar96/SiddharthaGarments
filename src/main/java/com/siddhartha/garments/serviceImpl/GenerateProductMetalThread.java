package com.siddhartha.garments.serviceImpl;

import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateProductMetalThread implements Runnable{

	Logger log = LogManager.getLogger(GenerateCalcThread.class);
	
	private Map<Object,Object> map;
	
	private MetalCalculationServiceImpl service;
		
	private String type;
	
	public GenerateProductMetalThread(Map<Object,Object> map,MetalCalculationServiceImpl service,String type) {
		this.map=map;
		this.service=service;
		this.type =type;
	}
	
	
	@Override
	public void run() {
		try {
			log.info("Cal Thread start ...." +new Date());
			
			service.inserProductCalc(map);
			
			log.info("Cal Thread end ...." +new Date());
		}catch (Exception e) {
			log.info(e);
		}
		
	}

}
