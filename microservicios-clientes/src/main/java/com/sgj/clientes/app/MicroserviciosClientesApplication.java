package com.sgj.clientes.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.sgj.clientes.service","com.sgj.clientes.controller"})
@EntityScan(basePackages = {"com.sgj.clientes.model"})
@EnableJpaRepositories(basePackages = {"com.sgj.clientes.dao"})
@SpringBootApplication
public class MicroserviciosClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosClientesApplication.class, args);
	}

}
