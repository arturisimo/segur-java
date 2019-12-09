package com.sgj.web.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Util {
	
	public static enum EstadoSensor {
		DESACTIVADO,
		ACTIVADO,
		ALARMA
	};
	
	public static enum Estancia {
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
		
	};
	
	private static final int logRounds = 10;
	
	public static String bcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
	}
	
	
}
