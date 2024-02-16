package com.siddhartha.garments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossOrginFilter {
	
	@Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
           public void addCorsMappings(final CorsRegistry registry){
               registry.addMapping("/**").allowedHeaders("*").allowedMethods("*");
            }
        };
    }

}
