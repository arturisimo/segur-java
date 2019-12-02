package com.sgj.sensores.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String cuenta;

	private String direccion;

	private String dni;

	private String email;

	private byte estado;

	private String nombre;

	private byte policia;


	//bi-directional many-to-one association to Sensore
	@OneToMany(mappedBy="cliente")
	private List<Sensor> sensores;

	public Cliente() {
	}

	public Cliente(int id, String cuenta, String direccion, String dni, String email, byte estado, String nombre,
			byte policia, List<Sensor> sensores) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.direccion = direccion;
		this.dni = dni;
		this.email = email;
		this.estado = estado;
		this.nombre = nombre;
		this.policia = policia;
		this.sensores = sensores;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte getPolicia() {
		return this.policia;
	}

	public void setPolicia(byte policia) {
		this.policia = policia;
	}


	public List<Sensor> getSensores() {
		return this.sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public Sensor addSensore(Sensor sensore) {
		getSensores().add(sensore);
		sensore.setCliente(this);

		return sensore;
	}

	public Sensor removeSensore(Sensor sensore) {
		getSensores().remove(sensore);
		sensore.setCliente(null);

		return sensore;
	}

}