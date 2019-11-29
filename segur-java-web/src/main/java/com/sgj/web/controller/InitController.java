package com.sgj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

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
		//model.addAttribute("cuentas", cajeroService.findAllCuentas());
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
