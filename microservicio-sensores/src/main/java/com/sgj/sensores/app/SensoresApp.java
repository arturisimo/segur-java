package com.sgj.sensores.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@EntityScan(basePackages = {"com.sgj.sensores.model"})
@EnableJpaRepositories(basePackages = {"com.sgj.sensores.repository"})
@ComponentScan(basePackages = {"com.sgj.sensores.controller","com.sgj.sensores.service"})
@SpringBootApplication
public class SensoresApp {

	public static void main(String[] args) {
		SpringApplication.run(SensoresApp.class, args);
	}
	
	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}
	
}
