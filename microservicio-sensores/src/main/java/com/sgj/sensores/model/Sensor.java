package com.sgj.sensores.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sgj.sensores.service.ServiceSensoresImpl.EstadoSensor;
import com.sgj.sensores.service.ServiceSensoresImpl.Estancia;


/**
 * The persistent class for the sensores database table.
 * 
 */
@Entity
@Table(name="sensores")
@NamedQuery(name="Sensor.findAll", query="SELECT s FROM Sensor s")
public class Sensor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Estancia zona;
	
	private Integer idCliente;
	
	@OneToMany(mappedBy="sensor", fetch = FetchType.EAGER)
	private List<Alarma> alarmas;

	private EstadoSensor estado;
	
	@Transient
	private String direccion;
	
	public Sensor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estancia getZona() {
		return zona;
	}

	public void setZona(Estancia zona) {
		this.zona = zona;
	}

	public List<Alarma> getAlarmas() {
		return this.alarmas;
	}

	public void setAlarmas(List<Alarma> alarmas) {
		this.alarmas = alarmas;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	public EstadoSensor getEstado() {
		return estado;
	}

	public void setEstado(EstadoSensor estado) {
		this.estado = estado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}