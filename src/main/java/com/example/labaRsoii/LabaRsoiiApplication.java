package com.example.labaRsoii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:application-local.properties")})
public class LabaRsoiiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabaRsoiiApplication.class, args);
	}

}
