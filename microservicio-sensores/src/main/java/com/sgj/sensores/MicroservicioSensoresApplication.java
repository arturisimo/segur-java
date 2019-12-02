package com.sgj.sensores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"modelo"})
@EnableJpaRepositories(basePackages = {"dao"})
@ComponentScan(basePackages = {"controller","service"})
@SpringBootApplication
public class MicroservicioSensoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioSensoresApplication.class, args);
	}

}
