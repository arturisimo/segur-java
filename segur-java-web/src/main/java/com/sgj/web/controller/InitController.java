package com.sgj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import com.sgj.commons.enums.Estancia;
import com.sgj.web.util.Util;

@Controller
@PropertySource("classpath:config.properties")
public class InitController {
	
	@Value("${url.servicio.sensores}")
	private String urlSensores;
	
	@Value("${url.servicio.clientes}")
	private String urlClientes;
	
	@Value("${sensor.reactive}")
	private boolean sensorReactive;
	
	
	@GetMapping({"/index", "/"})
	public String init(Model model) {
		model.addAttribute("urlClientes", urlClientes);
		return "index";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping({"/admin"})
	public String admin(Model model, Authentication auth) throws Exception {
		
		if (Util.isAdmin(auth)) {
			model.addAttribute("urlSensores", urlSensores);
			model.addAttribute("urlClientes", urlClientes);
			return "admin";
		} 
		
		if (Util.isClient(auth)) {
			model.addAttribute("estancias", Estancia.values());
			model.addAttribute("urlSensores", urlSensores);
			model.addAttribute("urlClientes", urlClientes);
			model.addAttribute("sensorReactive", sensorReactive);
			return "cliente";
		}
		throw new Exception("Fallo en el login");
	}
	
	@ExceptionHandler(Exception.class)
	public String exception(Model model, Exception e) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error";
	}
	
	@GetMapping({"/error"})
	public String error(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
		return "error";
	}
	
	
}
