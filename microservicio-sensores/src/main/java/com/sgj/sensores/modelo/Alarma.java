package com.sgj.sensores.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
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

	private Timestamp fecha;

	private byte policia;

	//bi-directional many-to-one association to Sensore
	@ManyToOne
	@JoinColumn(name="idSensor")
	private Sensor sensore;

	public Alarma() {
	}

	public Alarma(Timestamp fecha, byte policia, Sensor sensore) {
		super();
		this.fecha = fecha;
		this.policia = policia;
		this.sensore = sensore;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public byte getPolicia() {
		return this.policia;
	}

	public void setPolicia(byte policia) {
		this.policia = policia;
	}

	public Sensor getSensore() {
		return this.sensore;
	}

	public void setSensore(Sensor sensore) {
		this.sensore = sensore;
	}

}