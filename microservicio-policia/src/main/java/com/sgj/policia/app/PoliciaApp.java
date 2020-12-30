package com.sgj.policia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.sgj.policia.service"})
@EntityScan(basePackages = {"com.sgj.policia.model"})
@EnableJpaRepositories(basePackages = {"com.sgj.policia.repository"})
@SpringBootApplication
public class PoliciaApp {

	public static void main(String[] args) {
		SpringApplication.run(PoliciaApp.class, args);
	}

}
