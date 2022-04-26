package com.olxlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class OlxAppConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxAppConfigApplication.class, args);
	}

}
