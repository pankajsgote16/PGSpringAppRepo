package com.blogapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PgSpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgSpringAppApplication.class, args);
	}

	@Bean
	public ModelMapper getmodelMapper() {
		return new ModelMapper();
	}
}
