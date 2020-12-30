package com.sgj.policia.model.dto;

import java.io.Serializable;
import java.util.Date;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 7313401890989607513L;
	
	private String direccion;
	private String zona;
	private Date date;
	
	public Mensaje() {
		super();
	}

	public Mensaje(String direccion, Date date) {
		super();
		this.direccion = direccion;
		this.date = new Date();
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Override
	public String toString() {
		return "Mensaje [direccion=" + direccion + ", zona=" + zona + ", date=" + date + "]";
	}
	
	

}
