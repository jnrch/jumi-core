package com.jumillano.jumi.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JumiCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumiCoreApplication.class, args);
	}

}
