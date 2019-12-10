package com.sgj.web.model.dto;

import java.io.Serializable;
import java.util.Date;


public class Alarma implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private Date fecha;

	private boolean policia;

	private Integer idSensor;

	public Alarma() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isPolicia() {
		return policia;
	}

	public void setPolicia(boolean policia) {
		this.policia = policia;
	}

	public Integer getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(Integer idSensor) {
		this.idSensor = idSensor;
	}
	
	
	
	
}