package com.sgj.sensores.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the alarmas database table.
 * 
 */
@Entity
@Table(name="alarmas")
@NamedQuery(name="Alarma.findAll", query="SELECT a FROM Alarma a")
public class Alarma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Column(name = "policia", nullable = false)
	private boolean policia;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idSensor", nullable = false, insertable = false, updatable = false)
	private Sensor sensor;
	

	public Alarma() {
	}

	public Alarma(Date fecha, boolean policia, Sensor sensor) {
		super();
		this.fecha = fecha;
		this.policia = policia;
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

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	
	
}