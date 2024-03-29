package com.olxlogin;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
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
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
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
				"This page Gives REST API Documentation for the Advertise App",
				"2.5",
				"My Terms Of Service",
				new Contact("Ranjeet Kumar","http://ranjeet.com","ranjeetk1312@gmail.com"),
				"GPL",
				"http://gpl.org",
				new ArrayList<VendorExtension>());
		return apiInfo;
	}

}
