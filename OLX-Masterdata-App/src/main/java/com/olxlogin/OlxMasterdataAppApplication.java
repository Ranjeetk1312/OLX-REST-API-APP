package com.olxlogin;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OlxMasterdataAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxMasterdataAppApplication.class, args);
		Integer obj=120;
		System.out.println(obj);
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}


}
