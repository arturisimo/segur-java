package com.sgj.web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sensores database table.
 * 
 */
@Entity
@Table(name="sensores")
@NamedQuery(name="Sensore.findAll", query="SELECT s FROM Sensor s")
public class Sensor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String zona;

	//bi-directional many-to-one association to Alarma
	@OneToMany(mappedBy="sensore")
	private List<Alarma> alarmas;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Estado
	@ManyToOne
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
		alarma.setSensore(this);

		return alarma;
	}

	public Alarma removeAlarma(Alarma alarma) {
		getAlarmas().remove(alarma);
		alarma.setSensore(null);

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