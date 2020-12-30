package com.sgj.sensores.model.dto;

import java.io.Serializable;


public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String cuenta;

	private String direccion;

	private String dni;

	private String email;

	private boolean estado;

	private String nombre;

	private boolean policia;

	private Integer idUsuario;


	public Cliente() {
	}
	
	public Cliente(Integer idUsuario, String nombre, String email) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.nombre = nombre;
		this.estado = true;
		this.policia = false;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isPolicia() {
		return policia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setPolicia(boolean policia) {
		this.policia = policia;
	}

	

}