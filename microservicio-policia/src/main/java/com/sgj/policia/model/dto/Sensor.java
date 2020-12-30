package com.sgj.policia.model.dto;

import java.io.Serializable;

public class Sensor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;

	//private Integer zona;
	
	private Integer idCliente;
	
	private String direccion;
	
	public Sensor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
/*
	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona = zona;
	}
*/
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", idCliente=" + idCliente + ", direccion=" + direccion + ", getId()=" + getId()
				+ ", getIdCliente()=" + getIdCliente() + ", getDireccion()=" + getDireccion() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}