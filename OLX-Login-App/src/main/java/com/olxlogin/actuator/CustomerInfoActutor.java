package com.olxlogin.actuator;

import java.util.Collections;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomerInfoActutor implements InfoContributor  {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("Stocks",
                Collections.singletonMap("details", "customer information"));
	}

}
