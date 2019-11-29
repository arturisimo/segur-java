package com.sgj.web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estados database table.
 * 
 */
@Entity
@Table(name="estados")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	//bi-directional many-to-one association to Sensore
	@OneToMany(mappedBy="estadoBean")
	private List<Sensor> sensores;

	public Estado() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Sensor> getSensores() {
		return this.sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public Sensor addSensore(Sensor sensore) {
		getSensores().add(sensore);
		sensore.setEstadoBean(this);

		return sensore;
	}

	public Sensor removeSensore(Sensor sensore) {
		getSensores().remove(sensore);
		sensore.setEstadoBean(null);

		return sensore;
	}

}