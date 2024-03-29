package com.siddhartha.garments;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableWebMvc
@EnableAsync
public class GarmentsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarmentsApiApplication.class, args);
	}
	
	
	@Bean
	public SimpleDateFormat getDateFromat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf;
	}
	
	
	@Bean
	public Executor exeuter() {
		ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
		poolTaskExecutor.setCorePoolSize(20);
		poolTaskExecutor.setMaxPoolSize(50);
		poolTaskExecutor.setQueueCapacity(500);
		poolTaskExecutor.setThreadNamePrefix("FoldingExeuter_");
		poolTaskExecutor.initialize();
		return poolTaskExecutor;
		
	}

}
