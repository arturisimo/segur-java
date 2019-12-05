package com.sgj.sensores.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	private int id;

	private Date fecha;

	private boolean policia;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="idSensor")
//	private Sensor sensor;

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
	
	
	
}