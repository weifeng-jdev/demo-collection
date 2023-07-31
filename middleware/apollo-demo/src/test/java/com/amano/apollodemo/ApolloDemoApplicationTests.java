package com.amano.apollodemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@EnableApolloConfig
@Configuration
class ApolloDemoApplicationTests {
	@Value("${key1}")
	private String key1;

	@Test
	void contextLoads() {
	}

	@Test
	public void testApollo() {
		System.out.println("key1: " + key1);
	}
}
