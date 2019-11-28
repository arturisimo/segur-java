package com.sgj.web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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