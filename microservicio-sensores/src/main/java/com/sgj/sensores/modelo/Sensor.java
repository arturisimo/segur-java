package com.sgj.sensores.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


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
	private int id;

	private String zona;
	
	@JsonIgnore
	@OneToMany(mappedBy="sensor", fetch = FetchType.EAGER)
	private List<Alarma> alarmas;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="estado")
	private Estado estadoBean;

	public Sensor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public List<Alarma> getAlarmas() {
		return this.alarmas;
	}

	public void setAlarmas(List<Alarma> alarmas) {
		this.alarmas = alarmas;
	}

	public Alarma addAlarma(Alarma alarma) {
		getAlarmas().add(alarma);
		alarma.setSensor(this);

		return alarma;
	}

	public Alarma removeAlarma(Alarma alarma) {
		getAlarmas().remove(alarma);
		alarma.setSensor(null);

		return alarma;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estado getEstadoBean() {
		return this.estadoBean;
	}

	public void setEstadoBean(Estado estadoBean) {
		this.estadoBean = estadoBean;
	}

}