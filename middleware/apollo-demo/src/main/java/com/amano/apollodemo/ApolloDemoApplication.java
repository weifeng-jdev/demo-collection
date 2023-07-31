package com.amano.apollodemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class ApolloDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApolloDemoApplication.class, args);
	}


}
