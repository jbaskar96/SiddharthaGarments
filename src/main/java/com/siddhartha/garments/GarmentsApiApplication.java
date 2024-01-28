package com.siddhartha.garments;

import java.text.SimpleDateFormat;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class GarmentsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarmentsApiApplication.class, args);
	}
	
	
	@Bean
	public SimpleDateFormat getDateFromat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf;
	}
	

}
