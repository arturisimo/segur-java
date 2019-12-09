package com.sgj.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import com.sgj.web.util.Util;

@Controller
@PropertySource("classpath:config.properties")
public class InitController {
	
	@Value("${url.servicio.sensores}")
	private String urlSensores;
	
	@Value("${url.servicio.clientes}")
	private String urlClientes;
	
	
	@GetMapping({"/index", "/"})
	public String login(Model model) {
		return "index";
	}
	
	@GetMapping({"/admin"})
	public String admin(Model model) {
		//model.addAttribute("cuentas", cajeroService.findAllCuentas());
		return "admin";
	}
	
	@GetMapping({"/cliente"})
	public String cliente(Model model) {
		model.addAttribute("estancias", Util.Estancia.values());
		model.addAttribute("urlSensores", urlSensores);
		model.addAttribute("urlClientes", urlClientes);
		return "cliente";
	}
	
	@ExceptionHandler(Exception.class)
	public String exception(Model model, Exception e) {
		model.addAttribute("exception", e.getMessage());
		return "error";
	}
	
	@GetMapping({"/error"})
	public String error() {
		return "error";
	}
	
	
	
}
