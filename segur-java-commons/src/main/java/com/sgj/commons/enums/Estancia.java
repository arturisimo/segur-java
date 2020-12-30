package com.sgj.commons.enums;

public enum Estancia {
	
	COCINA("Cocina"),
	GARAJE("Garaje"),
	PASILLO("Pasillo"),
	DORMITORIO("Dormitorio"),
	SALON("Salón");
	
	private String nombre;
	
	private Estancia(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
}
