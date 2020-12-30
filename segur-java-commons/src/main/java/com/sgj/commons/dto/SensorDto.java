package com.sgj.commons.dto;

import java.io.Serializable;
import java.util.List;

import com.sgj.commons.enums.EstadoSensor;
import com.sgj.commons.enums.Estancia;

public class SensorDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;

	private Estancia zona;

	private List<AlarmaDto> alarmas;

	private EstadoSensor estado;
	
	private Integer idCliente;
	
	private String direccion;
	
	public SensorDto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AlarmaDto> getAlarmas() {
		return this.alarmas;
	}

	public void setAlarmas(List<AlarmaDto> alarmas) {
		this.alarmas = alarmas;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Estancia getZona() {
		return zona;
	}

	public void setZona(Estancia zona) {
		this.zona = zona;
	}

	public EstadoSensor getEstado() {
		return estado;
	}

	public void setEstadoBean(EstadoSensor estado) {
		this.estado = estado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
}