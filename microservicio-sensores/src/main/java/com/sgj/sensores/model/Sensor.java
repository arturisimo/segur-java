package com.sgj.sensores.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sgj.commons.enums.EstadoSensor;
import com.sgj.commons.enums.Estancia;


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
	
	@Column(name = "zona", nullable = false)
	private Estancia zona;
	
	@Column(name = "idCliente", nullable = false)
	private Integer idCliente;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="idSensor", nullable = false, insertable = true, updatable = false)
	private List<Alarma> alarmas;
	
	@Column(name = "estado", nullable = false)
	private EstadoSensor estado;
	
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
	
}