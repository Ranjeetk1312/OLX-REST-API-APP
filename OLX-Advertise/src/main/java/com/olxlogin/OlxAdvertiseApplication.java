package com.olxlogin;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OlxAdvertiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxAdvertiseApplication.class, args);
		Integer obj=120;
		System.out.println(obj);
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
