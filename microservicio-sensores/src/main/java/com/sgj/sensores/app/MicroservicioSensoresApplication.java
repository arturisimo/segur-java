package com.sgj.sensores.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"com.sgj.sensores.modelo"})
@EnableJpaRepositories(basePackages = {"com.sgj.sensores.dao"})
@ComponentScan(basePackages = {"com.sgj.sensores.controller","com.sgj.sensores.service"})
@SpringBootApplication
public class MicroservicioSensoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioSensoresApplication.class, args);
	}

}
