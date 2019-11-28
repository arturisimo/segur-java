package com.sgj.web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the policias database table.
 * 
 */
@Entity
@Table(name="policias")
@NamedQuery(name="Policia.findAll", query="SELECT p FROM Policia p")
public class Policia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int direccion;

	private Timestamp fecha;

	private String zona;

	public Policia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDireccion() {
		return this.direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}