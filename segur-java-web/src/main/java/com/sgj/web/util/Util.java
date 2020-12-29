package com.sgj.web.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Util {
	
	public static enum Roles {
		/**admin rol*/
		ROLE_ADMIN,
		/** cliente*/
		ROLE_USER;
	};
	
	public static boolean isAdmin(Authentication auth) {
		
		boolean admin = false;
		
		if (auth != null) {
			User user = (User)auth.getPrincipal();
			admin = user.getAuthorities().contains(new SimpleGrantedAuthority(Roles.ROLE_ADMIN.name()));
		}
		return admin;
	}
	
	/**
	 * Comprueba si la autenticación corresponde al Manager
	 * @param auth
	 * @return
	 */
	public static boolean isClient(Authentication auth) {
		
		boolean manager = false;
		
		if (auth != null) {
			User user = (User)auth.getPrincipal();
			manager = user.getAuthorities().contains(new SimpleGrantedAuthority(Roles.ROLE_USER.name()));
		}
		return manager;
	}
	
	
	
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
