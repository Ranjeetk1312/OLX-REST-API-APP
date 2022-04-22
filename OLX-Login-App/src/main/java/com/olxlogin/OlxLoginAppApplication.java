package com.olxlogin;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class OlxLoginAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxLoginAppApplication.class, args);
		Integer obj=120;
		System.out.println(obj);
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean
	public Docket getCustomizeDocker() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.olxlogin"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	private ApiInfo getApiInfo() {
		ApiInfo apiInfo =new ApiInfo(
				"Login Rest API Documentation",
				"This page Gives REST API Documentation for the Login App",
				"2.5",
				"My Terms Of Service",
				new Contact("Ranjeet Kumar","http://ranjeet.com","ranjeetk1312@gmail.com"),
				"GPL",
				"http://gpl.org",
				new ArrayList<VendorExtension>());
		return apiInfo;
	}
}
